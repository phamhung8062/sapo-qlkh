package warehouseManagement.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.math.BigDecimal;
import java.lang.Long;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



public class ReceiptDTO extends AbstractDTO {
	
	private String note;
	private String code;
	private String tag;
	private BigDecimal totalMoney;
	private Integer status;
	private List<ProductsReceiptDTO> productsReceiptDTOS;
//	@NotNull(message = "Supplier not found")

	private SupplierDTO supplier;
//	@NotNull(message = "Supplier not found")
	private Long supplierId;
//	@NotBlank(message = "Total not found")
	private Integer totalItem;
	@NotNull(message = "Payment not found")
	private PaymentDTO payment;
	private String createdDate1;

	public String getCreatedDate1() {
		return createdDate1;
	}

	public void setCreatedDate1(String createdDate1) {
		this.createdDate1 = createdDate1;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public PaymentDTO getPayment() {
		return payment;
	}
	public void setPayment(PaymentDTO payment) {
		this.payment = payment;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}
	public SupplierDTO getSupplier() {
		return supplier;
	}
	public void setSupplier(SupplierDTO supplier) {
		this.supplier = supplier;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public List<ProductsReceiptDTO> getProductsReceiptDTOS() {
		return productsReceiptDTOS;
	}

	public void setProductsReceiptDTOS(List<ProductsReceiptDTO> productsReceiptDTOS) {
		this.productsReceiptDTOS = productsReceiptDTOS;
	}
}
