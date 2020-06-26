package warehouseManagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import warehouseManagement.validation.anotation.Phone;
import warehouseManagement.validation.anotation.Status;

public class SupplierDTO extends AbstractDTO{
	@NotBlank(message = "Tên không được trống")
	private String  name;
	private String  code;
	@Email(message = "Định dạng email không hợp lệ")
	private String  email;
	@Phone(message = "Định dạng số điện thoại không hợp lệ")
	private String  phone;
	@Status(message = "Định dạng trạng thái không hợp lệ")
	private String  status;
	@URL(message = "Định dạng link không hợp lệ")
	private String  website;
//	@Tax
	private String  taxCode;
	private String  description;
	@NotBlank(message = "Địa chỉ không được trống")
	private String  address;
	private String  address2;

	private List<ReceiptDTO> receiptDTOS;

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

	public List<ReceiptDTO> getReceiptDTOS() {
		return receiptDTOS;
	}

	public void setReceiptDTOS(List<ReceiptDTO> receiptDTOS) {
		this.receiptDTOS = receiptDTOS;
	}

	public String getObjectName()
	{
		return "nhà cung cấp";
	}
}
