package warehouseManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import warehouseManagement.entity.ImageEntity;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    List<ImageEntity> getImageEntityByProduct_Id(Long id);

    @Query(value="select i from ImageEntity i where i.product.id = :idProduct and i.isActive = :isActive")
    ImageEntity getImageActive(@Param("idProduct") Long idProduct,@Param("isActive") boolean isActive);

    ImageEntity getImageEntityByProduct_IdAndId(Long idProduct, Long IdImage);
}
