package warehouseManagement.entity;

import warehouseManagement.dto.ImageDTO;

import javax.persistence.*;
import java.math.BigInteger;

@Table(name = "images")
@Entity
public class ImageEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "path")
    private String path;
    @Column(name = "isactive")
    private boolean isActive;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    public void setPathAndIsActive(String path, boolean isActive){
        this.path = path;
        this.isActive = isActive;
    }

    public void setImage(ImageDTO image){
        this.id = image.getId();
        this.path = image.getPath();
        this.isActive = image.isActive();
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }
    public void setProduct(ProductEntity product) {
        this.product = product;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

}
