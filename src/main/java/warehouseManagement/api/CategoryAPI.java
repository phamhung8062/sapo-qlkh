package warehouseManagement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warehouseManagement.dto.CategoryDTO;
import warehouseManagement.dto.ProductDTO;
import warehouseManagement.entity.CategoryEntity;
import warehouseManagement.notification.MessageBox;
import warehouseManagement.service.CategoryService;
import warehouseManagement.service.ProductService;

import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("categories")
@CrossOrigin
public class CategoryAPI {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllCategory() {
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategory();
        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }

    @GetMapping(path="/{idCategory}/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllProductByIdCategory(@PathVariable("idCategory") Long idCategory) throws ParseException {
        List<ProductDTO> productDTOS = productService.getAllProductByCategoryId(idCategory);
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @PostMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addCategory(@RequestBody CategoryDTO categoryDTO){
        System.out.println("je;ooo ----------------------");
        categoryService.addCategory(categoryDTO);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @PutMapping(path="/{idCategory}")
    public ResponseEntity<Object> updateCategory(   @RequestBody CategoryDTO categoryDTO,
                                                    @PathVariable("idCategory") Long idCategory){
        categoryService.updateCategory(categoryDTO, idCategory);
        return new ResponseEntity<>(new MessageBox(true, "cập nhật thành công"), HttpStatus.OK);
    }

    @PutMapping(path="/{idCategory}/products/{idProduct}")
    public ResponseEntity<Object> addProductToCategory( @PathVariable("idCategory") Long idCategory,
                                                        @PathVariable("idProduct") Long idProduct){
        categoryService.addProductToCategory(idProduct, idCategory);
        return new ResponseEntity<>(new MessageBox(true, "cập nhật thành công"), HttpStatus.OK);

    }

    @DeleteMapping(path="/{idCategory}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("idCategory") Long idCategory){
        categoryService.deleteCategory(idCategory);
        return new ResponseEntity<>(new MessageBox(true, "xóa thành công"), HttpStatus.OK);
    }

}
