package warehouseManagement.dto;

import warehouseManagement.entity.ProductsReciptEntity;
import warehouseManagement.util.DateTime;


public class ReceiptProductDTO {
    private long id;
    private String price;
    private int quantity;
    private String completeDate;
    private String receiptCode;
    private String createdBy;
    private long idReceipt;


    public ReceiptProductDTO(ProductsReciptEntity productsReciptEntity){
        this.id = productsReciptEntity.getId();
        this.price = String.valueOf(productsReciptEntity.getPrice());
        this.quantity = productsReciptEntity.getQuantity();
        this.completeDate = DateTime.getDate(productsReciptEntity.getCreatedDate());
        this.receiptCode = productsReciptEntity.getReceipts().getCode();
        this.createdBy = productsReciptEntity.getCreatedBy();
        this.idReceipt = productsReciptEntity.getReceipts().getId();
    }

    public long getIdReceipt() {
        return idReceipt;
    }

    public void setIdReceipt(long idReceipt) {
        this.idReceipt = idReceipt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public String getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(String receiptCode) {
        this.receiptCode = receiptCode;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
