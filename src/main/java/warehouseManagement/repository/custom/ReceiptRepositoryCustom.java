package warehouseManagement.repository.custom;

import org.springframework.data.domain.Pageable;
import warehouseManagement.dto.ReceiptSeachBuilder;
import warehouseManagement.entity.ReceiptEntity;

import java.util.List;

public interface ReceiptRepositoryCustom {
	List<ReceiptEntity> listReceipt(Pageable page, ReceiptSeachBuilder receipt);
}
