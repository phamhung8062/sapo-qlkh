package warehouseManagement.service;

import org.springframework.data.domain.Pageable;
import warehouseManagement.dto.ProductDTO;
import warehouseManagement.dto.SupplierDTO;
import warehouseManagement.entity.ProductEntity;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface ProductService {
    Map<String, Object> getAll(Pageable paging) throws ParseException;
    List<ProductDTO> getAllProductByCategoryId(Long idCategory) throws ParseException;

    Map<String, Object> filterProduct(Pageable paging, String name, String skuCode, Boolean status, String producer) throws ParseException;
    Map<String, Object> filterProductByName(Pageable paging, String name) throws ParseException;
    Long postProduct(ProductDTO productDTO) throws IOException, ParseException;

    ProductDTO getById(Long idProduct) throws ParseException;
    void updateById(ProductDTO productDTO,Long idProduct) throws ParseException;
    void changeStatus(Long idProduct, boolean status) throws ParseException;
    void deleteById(Long idProduct);
    int getTotalItem();
    int getTotalItemFind(List<ProductDTO> receiptDTOList);

}
