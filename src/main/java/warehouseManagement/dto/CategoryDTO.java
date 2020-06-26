package warehouseManagement.dto;

import warehouseManagement.entity.CategoryEntity;

public class CategoryDTO extends AbstractDTO {

    private String name;

    public CategoryDTO(){
        super();
    }
    public CategoryDTO(String name) {
        this.name = name;
    }

    public CategoryDTO(CategoryEntity categoryEntity){
        this.name = categoryEntity.getName();
        this.setId(categoryEntity.getId());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
