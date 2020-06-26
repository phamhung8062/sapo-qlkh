package warehouseManagement.service.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import warehouseManagement.converter.SupplierConverter;
import warehouseManagement.dto.ListResult;
import warehouseManagement.dto.ReceiptDTO;
import warehouseManagement.dto.SupplierDTO;
import warehouseManagement.entity.ReceiptEntity;
import warehouseManagement.entity.SupplierEntity;
import warehouseManagement.exception.commonException.DupplicateFieldException;
import warehouseManagement.exception.commonException.NoDataException;
import warehouseManagement.exception.commonException.NotFoundException;

import warehouseManagement.exception.supplierException.SupplierException;
import warehouseManagement.repository.SupplierRepository;
import warehouseManagement.service.ICommonService;
import warehouseManagement.service.SupplierSevice;
import warehouseManagement.util.DateTime;
import warehouseManagement.util.SpecificationExecution.SupplierSpecificationBuilder;
import warehouseManagement.util.Time;
import org.springframework.data.jpa.domain.Specification;

@Service
public class SupplierServiceImpl implements ICommonService<SupplierDTO>, SupplierSevice {

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    SupplierConverter supplierConverter;

    @Override
    @Transactional
    public SupplierDTO save(SupplierDTO dto) throws RuntimeException {
        //validate(tên ko đc trùng trong db)
        //dto.setModifiedDate(Time.newDate());
//        SupplierEntity existedEntity = supplierRepository.findOneByName(dto.getName());
//        if (existedEntity != null) throw new DupplicateFieldException("name", dto.getObjectName());
        //sửa
        SupplierEntity entity = null;
        if (dto.getCode() != null && dto.getCode().length() > 0) {
            SupplierEntity existedEntity = supplierRepository.findOneByCode(dto.getCode());
            if (existedEntity != null) throw new DupplicateFieldException("Mã", dto.getObjectName());
        } else {
            entity = supplierConverter.toEntity(dto);
            entity.setCreatedDate(DateTime.getDateEpochTime());
            entity.setModifiedDate(DateTime.getDateEpochTime());
            entity = supplierRepository.save(entity);
            Long id =  entity.getId();
            entity.setCode("SUPN" + id.toString());
            supplierRepository.save(entity);
        }
        return supplierConverter.toDTO(entity);
    }

    @Override
    public List<SupplierDTO> findAll() throws RuntimeException {
        List<SupplierEntity> suppliers = supplierRepository.findAll();
        if (suppliers.isEmpty()) throw new NoDataException();
        return supplierConverter.toDTOs(suppliers);
    }

    @Override
    public List<SupplierDTO> findAll(Integer pageNo, Integer pageSize) throws RuntimeException {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<SupplierEntity> pagedResult = supplierRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return supplierConverter.toDTOs(pagedResult.getContent());
        } else {
            throw new NotFoundException(pageNo);
        }
    }


    @Override
    public SupplierDTO findById(Long id) throws RuntimeException {
        //Optional<SupplierEntity> entity = supplierRepository.findById(id);
        Optional<SupplierEntity> entity = supplierRepository.findById(id);
        if (entity.isEmpty()) throw new NotFoundException(id);
        SupplierDTO suppplierDTO = supplierConverter.toDTO((entity.get()));
        return suppplierDTO;
    }

    @Override
    @Transactional
    public SupplierDTO update(SupplierDTO dto, Long id) throws RuntimeException {
        //dto.setModifiedDate(Time.newDate());
        Optional<SupplierEntity> entity = supplierRepository.findById(id);
        if (entity.isEmpty()) throw new NotFoundException(id);
        SupplierEntity supplier = entity.get();
        supplier.mapDTO(dto);
        supplier.setModifiedDate(DateTime.getDateEpochTime());
        return supplierConverter.toDTO(supplierRepository.save(supplier));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<SupplierEntity> optional = supplierRepository.findById(id);
        if (optional.get() == null) throw new NotFoundException(id);
        if (optional.get().getGoodsReceiptEntity().size() != 0) {
            throw new SupplierException("Không thể xóa nhà cung cấp đã có đơn hàng");
        }
        supplierRepository.deleteById(id);
    }

    public SupplierDTO findByName(String name) throws RuntimeException {
        SupplierEntity entity = supplierRepository.findOneByName(name);
        if (entity == null) throw new NoDataException();
        return supplierConverter.toDTO(entity);
    }


    public List<SupplierDTO> findByFilter(String search, Integer pageNo, Integer size, ListResult listResult) throws RuntimeException {
        SupplierSpecificationBuilder builder = new SupplierSpecificationBuilder();
        String[] queries = search.split(",");
        for (String query : queries) {
            String[] inQuery = query.split(":");
            builder.with(inQuery[0].strip(), ":", inQuery[1].strip());
        }
        Specification<SupplierEntity> spec = builder.build();
        List<SupplierDTO> result = supplierConverter.toDTOs(supplierRepository.findAll(spec));
        listResult.setCount(result.size());
        Pageable pageable = PageRequest.of(pageNo, size);
        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize() > result.size() ? result.size() : (start + pageable.getPageSize()));
        Page page = new PageImpl<>(result.subList(start, end), pageable, result.size());
        if (page.isEmpty()) {
            throw new NoDataException();
        }
        return page.getContent();
    }


    @Override
    public int getTotalItem() {
        return (int) supplierRepository.count();
    }

    @Override
    public int getTotalItemFind(List<SupplierDTO> receiptDTOList) {
        int dem = 0;
        for (SupplierDTO item : receiptDTOList) {
            dem++;
        }
        return dem;
    }
/*
    public void findByName(String name) throws RuntimeException
    {
        List<SupplierEntity> entities = supplierRepository.findAll();
        for(SupplierEntity ele:entities)
        {
            if (ele.getName().toLowerCase().equals(name.toLowerCase()))
            return supplierConverter.toDTO(ele);
        }
        throw new NoDataException();
    }
*/
}