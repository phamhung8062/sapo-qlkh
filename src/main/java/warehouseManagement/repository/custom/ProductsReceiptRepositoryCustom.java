package warehouseManagement.repository.custom;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import warehouseManagement.entity.ProductsReciptEntity;

public interface ProductsReceiptRepositoryCustom {
	List<ProductsReciptEntity> listVarianReceipt(Long id);
	void update(Long bigInteger, Integer quantityold, Integer quantitynew);
	void updatePrice(Long bigInteger, BigDecimal price);

}
