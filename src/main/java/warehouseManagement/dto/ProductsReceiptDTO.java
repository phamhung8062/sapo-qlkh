package warehouseManagement.dto;

import warehouseManagement.entity.ProductEntity;

import java.math.BigDecimal;

public class ProductsReceiptDTO extends  AbstractDTO {
    private String name;
    private Integer  quantity;
    private BigDecimal price;
    private ProductDTO productDTO;
    private Long varianId;
    private String skuCode;

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public Long getVarianId() {
        return varianId;
    }

    public void setVarianId(Long varianId) {
        this.varianId = varianId;
    }
}
