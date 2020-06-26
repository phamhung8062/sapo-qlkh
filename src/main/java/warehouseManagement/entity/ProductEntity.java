package warehouseManagement.entity;

import warehouseManagement.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

// test git
@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {

	@Column(name = "name")
	private String name;
	@Column(name = "skucode")
	 private String skuCode;
	@Column(name = "description")
	 private String description;
	@Column(name = "producer")
	private String producer;
	@Column(name = "color")
	private String color;
	@Column(name = "size")
	private String size;
	@Column(name = "price")
	private BigDecimal price;
	@Column(name = "material")
	private String material;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "status")
	private boolean status;


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product",
			fetch = FetchType.LAZY)
	private List<ImageEntity> images = new ArrayList<ImageEntity>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private CategoryEntity category;

	@OneToMany(
			mappedBy = "product",
			fetch = FetchType.LAZY
	)
	private List<ProductsReciptEntity> productReceiptEntities = new ArrayList<>();

	public List<ProductsReciptEntity> getProductReceiptEntities() {
		return productReceiptEntities;
	}

	public void setProductReceiptEntities(List<ProductsReciptEntity> productReceiptEntities) {
		this.productReceiptEntities = productReceiptEntities;
	}

	public void addImage(ImageEntity image){
		this.images.add(image);
		image.setProduct(this);
	}

	private void setProduct(ProductDTO productDTO, long dateTimeLong){
		System.out.println("--------------1" + productDTO.isStatus());
		this.size = productDTO.getSize();
		this.color = productDTO.getColor();
		this.material = productDTO.getMaterial();
		this.price = productDTO.getPrice().compareTo("")==0 ? new BigDecimal(0):new BigDecimal(productDTO.getPrice()) ;
		this.quantity = productDTO.getQuantity() == null ? 0 : productDTO.getQuantity();
		this.description = productDTO.getDescription();
		this.producer = productDTO.getProducer();
		this.status = productDTO.isStatus();
		this.setModifiedDate(dateTimeLong);
	}

	private void setName(ProductDTO productDTO){
		//		if(productDTO.getColor().compareTo("") != 0)
//			nameTmp += " - " + productDTO.getColor();
//		if(productDTO.getSize().compareTo("") != 0)
//			nameTmp += " - " + productDTO.getSize();
//		if(productDTO.getMaterial().compareTo("") != 0)
//			nameTmp += " - " + productDTO.getMaterial();
		this.name = productDTO.getName();
	}

	public void setProductUpdate(ProductDTO productDTO, long dateTimeLong){
		this.name = productDTO.getName();
		setProduct(productDTO, dateTimeLong);
	}

	public void setProductEntity(ProductDTO productDTO,  long dateTimeLong){
		setName(productDTO);
		setProduct(productDTO, dateTimeLong);
		this.setCreatedDate(dateTimeLong);
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public boolean isStatus() {
		return status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProducer() {return producer;}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<ImageEntity> getImages() {
		return images;
	}
	public void setImages(List<ImageEntity> images) {
		this.images = images;
	}
}
