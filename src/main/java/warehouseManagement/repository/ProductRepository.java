package warehouseManagement.repository;


import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import warehouseManagement.entity.ProductEntity;
import warehouseManagement.entity.ReceiptEntity;
import warehouseManagement.entity.SupplierEntity;

import javax.validation.constraints.Null;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @NotNull Page<ProductEntity> findAll(@NotNull Pageable paging);

    ProductEntity findDistinctFirstByNameAndColorAndSizeAndMaterial(String name, String color, String size, String material);

    ProductEntity findDistinctTopBySkuCode(String skuCode);

    Page<ProductEntity> findByNameContaining(String name, Pageable paging);

    Page<ProductEntity> findByNameContainingIgnoreCaseAndSkuCodeContainingIgnoreCaseAndStatusAndProducerContainingIgnoreCase(
            String name, String skuCode, Boolean status, String producer, Pageable paging);

    Page<ProductEntity> findByNameContainingIgnoreCaseAndSkuCodeContainingIgnoreCaseAndProducerContainingIgnoreCase(
            String name, String skuCode, String producer, Pageable paging);

    List<ProductEntity> findAllByCategoryId(Long idCategory);

}
