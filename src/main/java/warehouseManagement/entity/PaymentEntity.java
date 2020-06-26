package warehouseManagement.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payments")
public class PaymentEntity extends BaseEntity {
    @Column(name = "paymentmethod")
    private String  paymentMethod;

    @Column(name = "status")
    private int  status;

    @Column(name = "totalmoney")
    private BigDecimal totalMoney;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id",unique=true)
    private ReceiptEntity receipt;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public ReceiptEntity getReceipt() {
        return receipt;
    }

    public void setReceipt(ReceiptEntity receipt) {
        this.receipt = receipt;
    }
}
