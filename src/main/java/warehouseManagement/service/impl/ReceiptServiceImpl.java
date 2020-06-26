package warehouseManagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import warehouseManagement.converter.ReceiptConverter;
import warehouseManagement.dto.ReceiptDTO;
import warehouseManagement.entity.ReceiptEntity;
import warehouseManagement.exception.commonException.NoDataException;
import warehouseManagement.exception.commonException.NotFoundException;
import warehouseManagement.repository.ReceiptRepository;
import warehouseManagement.service.ICommonService;

/*
@Service
public class ReceiptServiceImpl implements ICommonService<ReceiptDTO> {

    @Autowired 
    ReceiptRepository receiptRepository;
    @Autowired
    ReceiptConverter converter;


    @Override
    public ReceiptDTO save(ReceiptDTO dto) {
        ReceiptEntity entity = converter.toEntity(dto);
        return converter.toDTO(receiptRepository.save(entity));
    }

    @Override
    public List<ReceiptDTO> findAll() throws RuntimeException{
        List<ReceiptEntity> entity = receiptRepository.findAll();
        if (entity.isEmpty()) throw new NoDataException();
        return converter.toDTOs(entity);
    }

    @Override
    public List<ReceiptDTO> findAll(Integer pageNo, Integer pageSize, String sortBy) throws RuntimeException {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<ReceiptEntity> page = receiptRepository.findAll(paging);
        if (page.hasContent())
        {
            return converter.toDTOs(page.getContent());
        }
        else{
            throw new NotFoundException(pageNo);
        }
    }

    @Override
    public ReceiptDTO findById(Long id) throws RuntimeException {
        Optional<ReceiptEntity> entity = receiptRepository.findById(id);
        if (entity.isEmpty()) throw new NotFoundException(id);
        return converter.toDTO(entity.get());
    }

    @Override
    public ReceiptDTO update(ReceiptDTO dto, Long id) throws RuntimeException{
        Optional<ReceiptEntity> entity = receiptRepository.findById(id);
        if (entity.isEmpty()) throw new NotFoundException(id);
        ReceiptEntity receipt = entity.get();
        receipt.mapDTO(dto);
        return converter.toDTO(receiptRepository.save(receipt));
    }

    @Override
    public void delete(Long id) {

    }
}*/
