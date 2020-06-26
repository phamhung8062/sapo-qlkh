package warehouseManagement.dto;
import warehouseManagement.entity.ProductsReciptEntity;
import warehouseManagement.validation.anotation.Price;
import warehouseManagement.entity.ProductEntity;
import warehouseManagement.util.DateTime;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDTO extends AbstractDTO {

    @NotNull(message = "tên không được để trống")
    private String name;
    private String skuCode;
    private String description;
    private String producer;
    private String color;
    private String size;
    private String material;
    @Price(message = "giá tiền nhập vào không hợp lệ")
    private String price;
    private Integer quantity;
    private boolean status;
    private List<ImageDTO> images = new ArrayList<>();
    private List<Base64Image> base64Images = new ArrayList<>();
    private CategoryDTO category = new CategoryDTO();
    private List<ReceiptProductDTO> receiptProducts = new ArrayList<>();

    public ProductDTO(){
        super();
    }

	public ProductDTO(ProductEntity product) throws ParseException {
		super(product.getId(),
				product.getCreatedBy(),
				product.getModifiedBy());
		this.setCreatedDate(DateTime.getDate(product.getCreatedDate()));
		this.setModifiedDate(DateTime.getDate(product.getModifiedDate()));
		this.name = product.getName();
		this.skuCode = product.getSkuCode();
		this.description = product.getDescription();
		this.color = product.getColor();
		this.size = product.getSize();
		this.material = product.getMaterial();
		this.price = String.valueOf(product.getPrice());
		this.quantity = product.getQuantity();
		this.producer = product.getProducer();
		this.status = product.getStatus();
		this.images = product.getImages().stream().map(ImageDTO::new).collect(Collectors.toList());
		if(product.getCategory() != null){
            this.category.setId(product.getCategory().getId());
            this.category.setName(product.getCategory().getName());
        }
		if(product.getProductReceiptEntities().size() > 0){
            setReceiptProduct(product.getProductReceiptEntities());
        }
	}

    private void setReceiptProduct(List<ProductsReciptEntity> productsReceiptEntities){
        for (ProductsReciptEntity productsReceipt : productsReceiptEntities){
            ReceiptProductDTO receiptProductDTO = new ReceiptProductDTO(productsReceipt);
            this.addProductReceipt(receiptProductDTO);
        }
    }

	public void setProductDTO(ProductEntity productEntity) throws ParseException {
		this.setCreatingAndModifyingToProduct(productEntity);
		this.images = productEntity.getImages().stream().map(ImageDTO::new).collect(Collectors.toList());
	}

    public List<ReceiptProductDTO> getReceiptProducts() {
        return receiptProducts;
    }

    public void setReceiptProducts(List<ReceiptProductDTO> receiptProductDTOS) {
        this.receiptProducts = receiptProductDTOS;
    }

    public void addProductReceipt(ReceiptProductDTO receiptProductDTO){
        this.receiptProducts.add(receiptProductDTO);
    }
    public void addImage(ImageDTO imageDTO){
        this.images.add(imageDTO);
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSkuCode() {
        return skuCode;
    }
    public void setSkuCode(String code) {
        this.skuCode = code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }

    public List<Base64Image> getBase64Images() {
        return base64Images;
    }

    public void setBase64Images(List<Base64Image> base64Images) {
        this.base64Images = base64Images;
    }

}
