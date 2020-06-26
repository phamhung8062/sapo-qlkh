package warehouseManagement.service;

import warehouseManagement.dto.CategoryDTO;
import warehouseManagement.entity.CategoryEntity;
import warehouseManagement.entity.ProductEntity;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategory();
    void addCategory(CategoryDTO categoryDTO);
    void addProductToCategory(Long idProduct, Long idCategory);
    void updateCategory(CategoryDTO categoryDTO, Long idcategory);
    void deleteCategory (Long idCategory);


}
