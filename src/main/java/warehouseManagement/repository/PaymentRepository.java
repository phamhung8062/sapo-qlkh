package warehouseManagement.repository;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import warehouseManagement.entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
	
	@Query(value = "select * from payments where payments.receipt_id = ?1" ,nativeQuery=true)
	PaymentEntity findReceipt(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "delete  from payments where payments.receipt_id = ?1" ,nativeQuery=true)
	void deleteReceipt(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "update payments u set u.status = ?,u.totalmoney=?,u.paymentmethod=? where u.id = ?", 
	  nativeQuery = true)
	int updatePayment(Integer status, BigDecimal total, String method, Long bigInteger);
	
}
