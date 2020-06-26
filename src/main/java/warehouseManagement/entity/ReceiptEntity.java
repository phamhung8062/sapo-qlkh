package warehouseManagement.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receipts")
public class ReceiptEntity  extends  BaseEntity{
    @Column(name = "note")
    private String note;

    @Column(name = "tag")
    private String tag;

    @Column(name = "code")
    private String code;

    @Column(name = "totalmoney")
    private BigDecimal totalMoney;

    @Column(name = "status")
    private int status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplierEntity;

    @OneToMany(
            mappedBy = "receipts",
            fetch = FetchType.LAZY
    )
    private List<ProductsReciptEntity> productReceiptEntities = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "receipt")
    private PaymentEntity paymentEntity;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public SupplierEntity getSupplierEntity() {
        return supplierEntity;
    }

    public void setSupplierEntity(SupplierEntity supplierEntity) {
        this.supplierEntity = supplierEntity;
    }

    public List<ProductsReciptEntity> getProductReceiptEntities() {
        return productReceiptEntities;
    }

    public void setProductReceiptEntities(List<ProductsReciptEntity> productReceiptEntities) {
        this.productReceiptEntities = productReceiptEntities;
    }

    public PaymentEntity getPaymentEntity() {
        return paymentEntity;
    }

    public void setPaymentEntity(PaymentEntity paymentEntity) {
        this.paymentEntity = paymentEntity;
    }
}
