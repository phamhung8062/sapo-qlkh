package warehouseManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import warehouseManagement.dto.Base64Image;
import warehouseManagement.dto.CategoryDTO;
import warehouseManagement.dto.ProductDTO;
import warehouseManagement.dto.SupplierDTO;
import warehouseManagement.entity.CategoryEntity;
import warehouseManagement.entity.ImageEntity;
import warehouseManagement.entity.ProductEntity;
import warehouseManagement.exception.productException.CannotDeleteProduct;
import warehouseManagement.exception.productException.EntityNotFoundException;
import warehouseManagement.exception.productException.ExistedProductException;
import warehouseManagement.exception.productException.IteratedCodeException;
import warehouseManagement.repository.CategoryRepository;
import warehouseManagement.repository.ImageRepository;
import warehouseManagement.repository.ProductRepository;
import warehouseManagement.service.ProductService;
import warehouseManagement.util.DateTime;
import warehouseManagement.util.GenCodeAuto;
import warehouseManagement.util.PathResource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired ProductRepository productRepository;
    @Autowired ImageRepository imageRepository;
    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public Map<String, Object> getAll(Pageable paging) throws ParseException {
        Page<ProductEntity> productEntitiesPage = productRepository.findAll(paging);
        return pagingData(productEntitiesPage);
    }

    @Override
    public List<ProductDTO> getAllProductByCategoryId(Long idCategory) throws ParseException {
        List<ProductEntity> productEntities = productRepository.findAllByCategoryId(idCategory);
        List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
        for (ProductEntity productEntity : productEntities){
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductDTO(productEntity);
            productDTOs.add(productDTO);
        }
        return productDTOs;
    }

    private Map<String, Object> pagingData(Page<ProductEntity> productEntitiesPage) throws ParseException {
        List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
        List<ProductEntity> productEntityList = productEntitiesPage.getContent();

        Map<String, Object> response = new HashMap<>();
        for(ProductEntity productEntity: productEntityList){
            ProductDTO productDTO = new ProductDTO(productEntity);
            productDTOs.add(productDTO);
        }
        response.put("products", productDTOs);
        response.put("currentPage", productEntitiesPage.getNumber() + 1);
        response.put("totalItems", productEntitiesPage.getTotalElements());
        response.put("totalPages", productEntitiesPage.getTotalPages());
        return response;
    }

    @Override
    public Map<String, Object> filterProduct(Pageable paging, String name, String skuCode, Boolean status, String producer) throws ParseException {
        Page<ProductEntity> productEntityPage;
        if(status == null)
            productEntityPage =
                    productRepository.findByNameContainingIgnoreCaseAndSkuCodeContainingIgnoreCaseAndProducerContainingIgnoreCase(
                            name, skuCode, producer, paging);
        else
            productEntityPage =
                    productRepository.findByNameContainingIgnoreCaseAndSkuCodeContainingIgnoreCaseAndStatusAndProducerContainingIgnoreCase(
                    name, skuCode, status, producer, paging );
        return pagingData(productEntityPage);
    }

    @Override
    public Map<String, Object> filterProductByName(Pageable paging, String name) throws ParseException {
        Page<ProductEntity> productEntityPage = productRepository.findByNameContaining(name, paging);
        return pagingData(productEntityPage);
    }


    @Override
    @Transactional
    public Long postProduct(ProductDTO productDTO) throws IOException, ParseException {
        if(checkIsExistedProduct(productDTO))
            throw new ExistedProductException(
                    productDTO.getName(), productDTO.getColor(), productDTO.getSize(), productDTO.getMaterial());

        long dateTimeLong = DateTime.getDateEpochTime();
        ProductEntity productEntity = new ProductEntity();
        if(productDTO.getBase64Images().size() != 0){
            handleFileAndPutIntoProduct(productDTO.getBase64Images(), productEntity, dateTimeLong);
        }
        productEntity.setProductEntity(productDTO, dateTimeLong);
        if(productDTO.getCategory() != null){
            setCategoryToProduct(productDTO.getCategory(), productEntity);
        }
        setSkuCodeIfNotNull(productDTO, productEntity);
        productRepository.save(productEntity);

        setSkuCodeIfNull(productDTO, productEntity);
        productDTO.setProductDTO(productEntity);
        return productEntity.getId();
    }

    private void setCategoryToProduct(CategoryDTO categoryDTO, ProductEntity productEntity){
        CategoryEntity categoryEntity;
        if(categoryDTO.getId() != null){
            categoryEntity = getCategoryById(categoryDTO.getId());
        }
        else{
            categoryEntity = new CategoryEntity();
            categoryEntity.setName(categoryDTO.getName());
            categoryRepository.save(categoryEntity);
        }
        productEntity.setCategory(categoryEntity);
    }

    private CategoryEntity getCategoryById(Long idCategory){
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(idCategory);
        if(categoryEntity.isEmpty())
            throw new EntityNotFoundException(idCategory, "category");
        return categoryEntity.get();
    }

    private void setSkuCodeIfNull(ProductDTO productDTO, ProductEntity productEntity){
        if(productDTO.getSkuCode().compareTo("") == 0)   {
            productEntity.setSkuCode(GenCodeAuto.genCode(productEntity.getId()));
            productRepository.save(productEntity);
        }
    }

    private void setSkuCodeIfNotNull(ProductDTO productDTO, ProductEntity productEntity){
        if(productDTO.getSkuCode().compareTo("") != 0){
            if(productRepository.findDistinctTopBySkuCode((productDTO.getSkuCode())) != null)
                throw new IteratedCodeException(productDTO.getSkuCode(), "Product");
            productEntity.setSkuCode(productDTO.getSkuCode());
        }
    }

    private boolean checkIsExistedProduct(ProductDTO productDTO){
        ProductEntity productEntity = productRepository.findDistinctFirstByNameAndColorAndSizeAndMaterial(
                    productDTO.getName(), productDTO.getColor(), productDTO.getSize(), productDTO.getMaterial());
        return productEntity != null;
    }

    private void handleFileAndPutIntoProduct(List<Base64Image> base64Images, ProductEntity productEntity, long dateTimeLong) throws IOException {
        for(Base64Image base64Image : base64Images){
            ImageEntity imageEntity = new ImageEntity();
            String pathImage = decodeBase64Image(base64Image, dateTimeLong);
            imageEntity.setPathAndIsActive(pathImage, false);
            productEntity.addImage(imageEntity);
        }
        productEntity.getImages().get(0).setActive(true); // để mặc dịnh ảnh đầu tiên là ảnh hiển thị chính
    }

    public static String decodeBase64Image(Base64Image base64Image, long dateTimeLong) throws IOException {
        String pathImage = DateTime.getDateStringToAddImage(dateTimeLong) + base64Image.getNameImage();
        byte[] data = Base64.getDecoder().decode(base64Image.getBase64Image());
        FileOutputStream fops = new FileOutputStream(PathResource.root + pathImage);
        fops.write(data);
        fops.close();
        return (pathImage);
    }


    @Override
    public ProductDTO getById(Long idProduct) throws ParseException {
        ProductEntity productEntity = getProductEntity(idProduct);
        return new ProductDTO(productEntity);
    }

    @Override
    @Transactional
    public void updateById(ProductDTO productDTO, Long idProduct) throws ParseException {
        ProductEntity productEntity = getProductEntity(idProduct);
        if(productDTO.getSkuCode().compareTo("") != 0 && productDTO.getSkuCode().compareToIgnoreCase(productEntity.getSkuCode()) != 0)
            productEntity.setSkuCode(productDTO.getSkuCode());
        productEntity.setProductUpdate(productDTO, DateTime.getDateEpochTime());
        productRepository.save(productEntity);
        productDTO.setProductDTO(productEntity);
    }

    @Override
    @Transactional
    public void changeStatus(Long idProduct, boolean status) throws ParseException {
        ProductEntity productEntity = getProductEntity(idProduct);
        productEntity.setStatus(status);
        productEntity.setModifiedDate(DateTime.getDateEpochTime());
        productRepository.save(productEntity);
    }

    private ProductEntity getProductEntity(Long idProduct){
        Optional<ProductEntity> productEntity = productRepository.findById(idProduct);
        if(productEntity.isEmpty())
            throw new EntityNotFoundException(idProduct, "product");
        return productEntity.get();
    }

    @Override
    public void deleteById(Long idProduct) {
        ProductEntity productEntity = getProductEntity(idProduct);
        if(productEntity.getProductReceiptEntities().size() > 0)
            throw new CannotDeleteProduct();
        productRepository.deleteById(idProduct);
    }

    @Override
    public int getTotalItem() {
        return (int) productRepository.count();
    }

    @Override
    public int getTotalItemFind(List<ProductDTO> receiptDTOList) {
        int dem=0;
        for(ProductDTO item:receiptDTOList){
            dem++;
        }
        return dem;
    }
}
