package warehouseManagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import warehouseManagement.dto.SupplierDTO;
import warehouseManagement.validation.anotation.Phone;
import warehouseManagement.validation.anotation.Status;
import warehouseManagement.validation.anotation.Tax;

@Entity
@Table(name = "suppliers")
public class SupplierEntity extends BaseEntity {
	@Column(name = "name")
	@NotBlank
	private String  name;
	
	@Column(name = "code")
	private String  code;

	@Column(name = "email")
	@Email
	private String  email;/*Validate*/
	
	@Column(name = "phone")
	@Phone
	private String  phone;/*Validate*/
	
	@Column(name = "status")
	@Status
	private String  status;/*Validate*/
	
	@Column(name = "website")/*Validate*/
	@URL
	private String  website;
	
	@Column(name = "taxCode")/*Validate*/
	//@Tax
	private String  taxCode;
	
	@Column(name = "description")
	private String  description;
	
	@Column(name = "address")
	private String  address;
	
	@Column(name = "address2")
	private String  address2;

	@OneToMany(
	        mappedBy = "supplierEntity",
	        fetch = FetchType.LAZY
	    )
	private List<ReceiptEntity> goodsReceiptEntity = new ArrayList<>();
	
	public List<ReceiptEntity> getGoodsReceiptEntity() {
		return goodsReceiptEntity;
	}

	public void setGoodsReceiptEntity(List<ReceiptEntity> goodsReceiptEntity) {
		this.goodsReceiptEntity = goodsReceiptEntity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void mapDTO(SupplierDTO dto)
	{
		this.address = dto.getAddress();
		this.address2 = dto.getAddress2();
		this.code = dto.getCode();
		this.description = dto.getDescription();
		this.email = dto.getEmail();
		this.name = dto.getName();
		this.phone = dto.getPhone();
		this.status = dto.getStatus();
		this.taxCode = dto.getTaxCode();
		this.website = dto.getWebsite();
	}
}

	
