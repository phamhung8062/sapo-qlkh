package warehouseManagement.service.impl;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import warehouseManagement.converter.*;
import warehouseManagement.dto.*;
import warehouseManagement.entity.*;
//import warehouseManagement.exceptions.ApiExceptionHandler;
import warehouseManagement.exceptions.ReceiptNotFoundException;
import warehouseManagement.repository.*;
import warehouseManagement.service.IReceiptsService;
import warehouseManagement.util.DateTime;

@Service
public class ReceiptService implements IReceiptsService {
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    public SupplierRepository supplierRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ProductsReceiptRepository productsReceiptRepository;
    @Autowired
    private ReceiptConverter receiptConverter;
    @Autowired
    private SupplierConverter supplierConverter;
    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private PaymentConverter paymentConverter;
    @Autowired
    private ProductsReceiptConverter varianReceiptConverter;

    @Override
    public List<ReceiptDTO> findAll(Pageable pageable) throws ParseException {
        Page<ReceiptEntity> receiptEntities = receiptRepository.findAll(pageable);
        List<ReceiptDTO> receiptDTOS = new ArrayList<>();
        for (ReceiptEntity item : receiptEntities) {
            ReceiptDTO receiptDTO = new ReceiptDTO();
            String date = DateTime.getDate(item.getCreatedDate());
            SupplierDTO supplierDTO = supplierConverter.toDTO(item.getSupplierEntity());
            receiptDTO = receiptConverter.convertToDTO(item);
            receiptDTO.setSupplier(supplierDTO);
            receiptDTO.setCreatedDate(date);
            receiptDTOS.add(receiptDTO);

        }
        return receiptDTOS;
//        return receiptEntities.stream().map(item -> receiptConverter.convertToDTO(item)).collect(Collectors.toList());
    }

    @Override
    public List<ReceiptDTO> findbyCode(String code, Pageable pageable) {
        Page<ReceiptEntity> receiptEntities = receiptRepository.findAll(pageable);
        List<ReceiptDTO> receiptDTOS = new ArrayList<>();
        for (ReceiptEntity item : receiptEntities) {
            ReceiptDTO receiptDTO = new ReceiptDTO();
            SupplierDTO supplierDTO = supplierConverter.toDTO(item.getSupplierEntity());
            receiptDTO = receiptConverter.convertToDTO(item);
            receiptDTO.setSupplier(supplierDTO);
            receiptDTOS.add(receiptDTO);
        }
        return receiptDTOS;
    }

    @Override
    public List<ReceiptDTO> search(Pageable page, ReceiptSeachBuilder searchReceipt) throws ParseException {
        List<ReceiptEntity> receiptEntities = receiptRepository.listReceipt(page, searchReceipt);
        List<ReceiptDTO> receiptDTOS = new ArrayList<>();
        for (ReceiptEntity item : receiptEntities) {
            ReceiptDTO receiptDTO = new ReceiptDTO();


            SupplierDTO supplierDTO = supplierConverter.toDTO(item.getSupplierEntity());
            receiptDTO = receiptConverter.convertToDTO(item);
            if (item.getCreatedDate() != null) {
                String date = DateTime.getDate(item.getCreatedDate());
                receiptDTO.setCreatedDate(date);
            }
            receiptDTO.setSupplier(supplierDTO);
            receiptDTOS.add(receiptDTO);

        }
        return receiptDTOS;
    }

    @Override
    public ReceiptDTO findOneRe(Long id) throws ParseException {
        ReceiptEntity receiptEntity = receiptRepository.getOne(id);
        SupplierEntity supplierEntity = supplierRepository.getOne(receiptEntity.getSupplierEntity().getId());
        ReceiptDTO reDto = receiptConverter.convertToDTO(receiptEntity);
        Optional<PaymentEntity> paymentEntity = Optional.ofNullable(paymentRepository.findReceipt(id));
        if (paymentEntity.isPresent()) {
            reDto.setPayment(paymentConverter.convertToDTO(paymentEntity.get()));
        }
        List<ProductsReciptEntity> productGoodreceiptEntities = productsReceiptRepository
                .listVarianReceipt(receiptEntity.getId());
        List<ProductsReceiptDTO> productReciptDTOs = new ArrayList<>();
        for (ProductsReciptEntity item : productGoodreceiptEntities) {
            ProductsReceiptDTO prReciptDTO = varianReceiptConverter.convertToDTO(item);
            prReciptDTO.setName(item.getProduct().getName());
            prReciptDTO.setVarianId(item.getProduct().getId());
            prReciptDTO.setSkuCode(item.getProduct().getSkuCode());
            productReciptDTOs.add(prReciptDTO);
        }

        reDto.setSupplier(supplierConverter.toDTO(supplierEntity));
        reDto.setProductsReceiptDTOS(productReciptDTOs);

        return reDto;
    }

    @Override
    @Transactional
    public Long save(ReceiptDTO dto) {
        if (dto.getSupplierId() == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, " Vui lòng chọn 1 nhà cung cấp !");
        }
        ReceiptEntity receiptEntity = receiptConverter.convertToEntity(dto);
        long dateTimeLong = DateTime.getDateEpochTime();
        receiptEntity.setCreatedDate(dateTimeLong);
        Optional<SupplierEntity> supplierEntity = supplierRepository.findById(dto.getSupplierId());
        if (!supplierEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " Không tìm thấy nhà cung cấp !");
        }
        receiptEntity.setSupplierEntity(supplierEntity.get());
        receiptEntity=receiptRepository.save(receiptEntity);
        for (ProductsReceiptDTO item : dto.getProductsReceiptDTOS()) {
            ProductsReciptEntity productsReciptEntity = varianReceiptConverter.convertToEntity(item);
            productsReciptEntity.setCreatedDate(dateTimeLong);
            productsReciptEntity.setModifiedDate(dateTimeLong);
            ProductEntity variantEntity = productRepository.getOne(item.getVarianId());
//            productsReceiptRepository.update(variantEntity.getId(), variantEntity.getQuantity(), item.getQuantity());
            productsReciptEntity.setProduct(variantEntity);
            productsReciptEntity.setReceipts(receiptEntity);
            productsReceiptRepository.save(productsReciptEntity);
        }
        if (receiptEntity.getCode() == "") {
            receiptEntity.setCode("PON00" + receiptEntity.getId() + "");
            receiptRepository.save(receiptEntity);
        }
        if (dto.getPayment().getStatus() == 1) {
            PaymentEntity paymentEntity = paymentConverter.convertToEntity(dto.getPayment());
            paymentEntity.setCreatedDate(dateTimeLong);
            paymentEntity.setModifiedDate(dateTimeLong);
            paymentEntity.setReceipt(receiptEntity);
            paymentRepository.save(paymentEntity);
        }

        return receiptEntity.getId();

    }

    @Override
    @Transactional
    public void update(ReceiptDTO dto) {
        long dateTimeLong = DateTime.getDateEpochTime();
        if (dto.getSupplierId() == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, " Vui lòng chọn 1 nhà cung cấp !");
        }
        ReceiptEntity receiptEntity = receiptConverter.convertToEntity(dto);
        Optional<SupplierEntity> supplierEntity = supplierRepository.findById(dto.getSupplierId());
        if (!supplierEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " Không tìm thấy nhà cung cấp !");
        }
        receiptEntity.setSupplierEntity(supplierEntity.get());
        receiptEntity.setModifiedDate(dateTimeLong);
        receiptEntity=receiptRepository.save(receiptEntity);

        List<ProductsReciptEntity> productsReciptEntityList = productsReceiptRepository.listVarianReceipt(dto.getId());
        updateProductsReceipt(productsReciptEntityList, dto.getProductsReceiptDTOS());
        List<ProductsReciptEntity> productsReciptEntityList1 =new ArrayList<>();
        for (ProductsReceiptDTO item : dto.getProductsReceiptDTOS()) {
            ProductsReciptEntity productsReciptEntity = varianReceiptConverter.convertToEntity(item);
            ProductEntity variantEntity = productRepository.getOne(item.getVarianId());
            productsReceiptRepository.update(variantEntity.getId(), variantEntity.getQuantity(), item.getQuantity());
            productsReciptEntity.setCreatedDate(dateTimeLong);
            productsReciptEntity.setModifiedDate(dateTimeLong);
            productsReciptEntity.setProduct(variantEntity);
            productsReciptEntity.setReceipts(receiptEntity);

//             productsReceiptRepository.save(productsReciptEntity);
            productsReciptEntityList1.add(productsReciptEntity);
        }
        productsReceiptRepository.saveAll(productsReciptEntityList1);

    }
    ReceiptEntity updaterec(ReceiptDTO dto){
        if (dto.getSupplierId() == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, " Vui lòng chọn 1 nhà cung cấp !");
        }
        ReceiptEntity receiptEntity = receiptConverter.convertToEntity(dto);
        Optional<SupplierEntity> supplierEntity = supplierRepository.findById(dto.getSupplierId());
        if (!supplierEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " Không tìm thấy nhà cung cấp !");
        }
        receiptEntity.setSupplierEntity(supplierEntity.get());
        receiptEntity=receiptRepository.save(receiptEntity);
        return receiptEntity;
    }
    void updateProductsReceipt(List<ProductsReciptEntity> productsReciptEntities, List<ProductsReceiptDTO> productsReceiptDTOS) {
        List<Long> listOne = new ArrayList<>();
        List<Long> listTwo = new ArrayList<>();
        List<Long> listC = new ArrayList<>();
        for (ProductsReciptEntity item : productsReciptEntities) {
            listOne.add(item.getId());
        }
        for (ProductsReceiptDTO item : productsReceiptDTOS) {
            listTwo.add(item.getId());
        }
        for (int i = 0; i < productsReciptEntities.size(); i++) {
            for (int j = 0; j < productsReceiptDTOS.size(); j++) {
                if (productsReciptEntities.get(i).getId() == (productsReceiptDTOS.get(j).getId())) {
                    listC.add(productsReceiptDTOS.get(j).getId());
                }
            }
        }
        listOne.removeAll(listC);
        for (Long item : listOne) {
            productsReceiptRepository.deleteById(item);
        }
    }

    @Override
    public int getTotalItem() {
        return (int) receiptRepository.count();
    }

    @Override
    public int getTotalItemFind(List<ReceiptDTO> receiptDTOList) {
        int dem = 0;
        for (ReceiptDTO item : receiptDTOList) {
            dem++;
        }
        return dem;
    }

    @Override
    public void savePayment(PaymentDTO paymentDTO) {
        if (paymentDTO.getReceiptId() == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, " Vui lòng chọn đơn hàng !");
        }
        Optional<ReceiptEntity> receipt = receiptRepository.findById(paymentDTO.getReceiptId());
        if (!receipt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " Không tìm thấy đơn hàng !");
        }
        long dateTimeLong = DateTime.getDateEpochTime();
        PaymentEntity paymentEntity = paymentConverter.convertToEntity(paymentDTO);
        paymentEntity.setCreatedDate(dateTimeLong);
        paymentEntity.setReceipt(receipt.get());
        paymentRepository.save(paymentEntity);

//        paymentRepository.updatePayment(paymentDTO.getStatus(),
//                paymentDTO.getTotalMoney(), paymentDTO.getPaymentMethod(), paymentDTO.getId());
    }

    @Override
    public void updateStatus(ReceiptDTO dto) {
        try{
            if (dto.getId() == null) {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, " Không có đơn hàng !");
            }
            Optional<ReceiptEntity> receipt = receiptRepository.findById(dto.getId());
            if (!receipt.isPresent()) {
                throw new ReceiptNotFoundException(dto.getId());
            }
            long dateTimeLong = DateTime.getDateEpochTime();
            ReceiptEntity receiptEntity=receiptRepository.getOne(dto.getId());
            List<ProductsReciptEntity> productsReciptEntities=receiptEntity.getProductReceiptEntities();
            for (ProductsReciptEntity item : productsReciptEntities){
                ProductEntity variantEntity = item.getProduct();
//                productsReciptEntity.setCreatedDate(dateTimeLong);
//                productsReciptEntity.setModifiedDate(dateTimeLong);
                productsReceiptRepository.update(variantEntity.getId(), variantEntity.getQuantity(), item.getQuantity());
                productsReceiptRepository.updatePrice(variantEntity.getId(),item.getPrice());
            }
            receiptRepository.updateStatus(dto.getStatus(), dto.getId());
        }catch (Exception e){


        }


    }

    @Override
    public void updateNoteAndTag(@NotNull ReceiptDTO dto) {
        if (dto.getId() == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, " Không có đơn hàng !");
        }
        Optional<ReceiptEntity> receipt = receiptRepository.findById(dto.getId());
        if (!receipt.isPresent()) {
            throw new ReceiptNotFoundException(dto.getId());
        }
        receiptRepository.updateNoteAndTag(dto.getNote(), dto.getTag(), dto.getId());
    }

    @Override
    public List<SupplierDTO> findNameSupplier(String name, Pageable pageable) {
        Page<SupplierEntity> supplierEntities = supplierRepository.findAll(pageable);
        if (StringUtils.isNotBlank(name)) {
            supplierEntities = new PageImpl<SupplierEntity>(supplierRepository.findByNameContaining(name, pageable));
        }
        supplierEntities = supplierEntities;
        return supplierEntities.stream().map(item -> supplierConverter.convertToDTO(item)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findNameProduct(String name, Pageable pageable) {
        Page<ProductEntity> productEntities = productRepository.findAll(pageable);
        if (StringUtils.isNotBlank(name)) {
            productEntities = productRepository.findByNameContaining(name, pageable);
        }
        productEntities = productEntities;
        return productEntities.stream().map(item -> productConverter.convertToDTO(item)).collect(Collectors.toList());
    }

    @Override
    public SupplierDTO quicksave(SupplierDTO supplier) {
        SupplierEntity supplierEntity = supplierConverter.toEntity(supplier);
        supplierEntity.setStatus("true");
        supplierEntity = supplierRepository.save(supplierEntity);
        SupplierDTO supplierDTO = supplierConverter.convertToDTO(supplierEntity);
        return supplierDTO;
    }


}
