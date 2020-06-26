package warehouseManagement.dto;
import warehouseManagement.entity.ProductEntity;
import warehouseManagement.util.DateTime;

import java.text.ParseException;

public class AbstractDTO {

	private Long id;
	private String createdDate;
	private String modifiedDate;
	private String createdBy;
	private String modifiedBy;


	public AbstractDTO() {
	}

	public AbstractDTO(Long id, String createdBy, String modifiedBy) {
		this.id = id;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}

	public void setCreatingAndModifyingToProduct(ProductEntity productEntity) throws ParseException {
		this.setId(productEntity.getId());
		this.setCreatedBy(productEntity.getCreatedBy());
		this.setModifiedBy(productEntity.getModifiedBy());
		this.setCreatedDate(DateTime.getDate(productEntity.getCreatedDate()));
		this.setModifiedDate(DateTime.getDate(productEntity.getModifiedDate()));
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
