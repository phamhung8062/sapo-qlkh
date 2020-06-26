package warehouseManagement.repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import warehouseManagement.dto.ReceiptSeachBuilder;
import warehouseManagement.entity.ReceiptEntity;
import warehouseManagement.repository.custom.ReceiptRepositoryCustom;

@Repository
public class ReceiptRepositoryCustomImpl implements ReceiptRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public List<ReceiptEntity> listReceipt(Pageable page, ReceiptSeachBuilder receipt) {
		StringBuilder sqlSearch = new StringBuilder("Select * FROM receipts A ");
		if(StringUtils.isNotBlank(receipt.getSupplierName())) {
			sqlSearch.append(" INNER JOIN suppliers  B ON B.id=A.supplier_id ");
		}
		if(StringUtils.isNotBlank(receipt.getStatuspayment())) {
			sqlSearch.append(" INNER JOIN payments  C ON A.id=C.receipt_id ");
		}
		sqlSearch.append(" WHERE 1=1 ");
		if(StringUtils.isNotBlank(receipt.getStatus())) {
			sqlSearch.append(" AND A.status ="+receipt.getStatus()+"");
		}
		if(StringUtils.isNotBlank(receipt.getCode())){
			sqlSearch.append(" AND A.code LIKE '%"+receipt.getCode()+"%'");
		}
		if(StringUtils.isNotBlank(receipt.getStatuspayment())) {
			sqlSearch.append(" AND C.status ="+receipt.getStatuspayment()+"");
		}
		if(StringUtils.isNotBlank(receipt.getSupplierName())) {
			sqlSearch.append(" AND B.name LIKE '%"+receipt.getSupplierName()+"%' ");
		}
		sqlSearch.append(" ORDER BY A.id DESC ");
		Query query= entityManager.createNativeQuery(sqlSearch.toString(),ReceiptEntity.class);
		query.setFirstResult((int) page.getOffset());
		query.setMaxResults(page.getPageSize());
		List<ReceiptEntity> list=query.getResultList();
		return list;

	}
}
