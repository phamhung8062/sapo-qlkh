package warehouseManagement.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "category")
@Entity
public class CategoryEntity extends BaseEntity {

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "category",
            fetch = FetchType.LAZY)
    private List<ProductEntity> listProducts = new ArrayList<>();


    public void setCategory(String name, long createDate){
        this.name = name;
        this.setCreatedDate(createDate);
        this.setModifiedDate(createDate);
    }

    public List<ProductEntity> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<ProductEntity> listProducts) {
        this.listProducts = listProducts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
