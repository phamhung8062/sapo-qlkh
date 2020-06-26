package warehouseManagement.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import warehouseManagement.entity.ReceiptEntity;
import warehouseManagement.entity.SupplierEntity;

import java.util.List;

public interface SupplierRepository extends JpaRepository<SupplierEntity, Long>, JpaSpecificationExecutor<SupplierEntity> {

    List<SupplierEntity> findByNameContaining(String name, Pageable pageable);
    SupplierEntity findOneByName(String name);
    SupplierEntity findOneByCode(String code);
}
