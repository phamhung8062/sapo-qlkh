package warehouseManagement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warehouseManagement.dto.ProductDTO;
import warehouseManagement.notification.MessageBox;
import warehouseManagement.service.ProductService;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("products")
@CrossOrigin("http://localhost:2020")
public class ProductAPI {

    @Autowired
    private  ProductService productService;


    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getAll(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String skuCode,
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false, defaultValue = "") String producer,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size ) throws ParseException {
        Pageable paging = PageRequest.of(page-1, size, Sort.by("id").descending());
        Map<String, Object> response;
        if(name.compareTo("") == 0 && skuCode.compareTo("") == 0 && producer.compareTo("") == 0 && status == null){
            response = productService.getAll(paging);
        }
        else{
            response = productService.filterProduct(paging, name, skuCode, status, producer);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO getProductById(@PathVariable("id") Long id) throws ParseException {
        ProductDTO productDTO= productService.getById(id);
        return productDTO;
    }

    @CrossOrigin(value = "http://localhost:2020")
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> postProduct( @Valid @RequestBody ProductDTO productDTO ) throws IOException, ParseException {
        Long idProduct = productService.postProduct(productDTO);
        return new ResponseEntity<>(new MessageBox(true, "Thêm sản phẩm thành công", idProduct), HttpStatus.OK);
    }

    @CrossOrigin(value = "http://localhost:2020")
    @PutMapping(path = "/{idProduct}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateProduct(@PathVariable("idProduct") Long idProduct,
                                                @Valid @RequestBody ProductDTO productDTO) throws ParseException {
        productService.updateById(productDTO, idProduct);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @CrossOrigin(value = "http://localhost:2020")
    @PutMapping(path="/{idProduct}/status/{status}")
    public ResponseEntity<Object> changeStatus(@PathVariable("idProduct") Long idProduct,
                                               @PathVariable("status") boolean status ) throws ParseException {
        productService.changeStatus(idProduct, status);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @CrossOrigin(value = "http://localhost:2020")
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id){
        productService.deleteById(id);
        return new ResponseEntity<>("Delete successful!", HttpStatus.OK);
    }
}
