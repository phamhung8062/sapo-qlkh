package warehouseManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import warehouseManagement.entity.ProductsReciptEntity;
import warehouseManagement.repository.custom.ProductsReceiptRepositoryCustom;

public interface ProductsReceiptRepository  extends JpaRepository<ProductsReciptEntity, Long>, ProductsReceiptRepositoryCustom {

}
