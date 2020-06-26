package warehouseManagement.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "productreceipts")
public class ProductsReciptEntity extends BaseEntity{
    @Column(name = "quantity")
    private Integer  quantity;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id")
    private ReceiptEntity receipts;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public ReceiptEntity getReceipts() {
        return receipts;
    }

    public void setReceipts(ReceiptEntity receipts) {
        this.receipts = receipts;
    }
}
