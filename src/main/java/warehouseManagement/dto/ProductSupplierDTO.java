package warehouseManagement.dto;

public class ProductSupplierDTO extends AbstractDTO {

	private Integer  quantity;
	private Float  price;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
}
