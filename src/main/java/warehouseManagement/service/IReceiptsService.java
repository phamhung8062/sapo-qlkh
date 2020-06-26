package warehouseManagement.service;
import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import warehouseManagement.dto.*;

public interface IReceiptsService {
	List<ReceiptDTO> findAll(Pageable pageable) throws ParseException;
	List<ReceiptDTO> findbyCode(String code,Pageable pageable) throws ParseException;
	List<ReceiptDTO> search (Pageable page, ReceiptSeachBuilder searchReceipt) throws ParseException;
	ReceiptDTO findOneRe(Long id) throws ParseException;
    Long save(ReceiptDTO dto);
	void update(ReceiptDTO dto);
	int getTotalItemFind(List<ReceiptDTO> receiptDTOList);
	int getTotalItem();
	void savePayment(PaymentDTO paymentDTO);
	void updateStatus(ReceiptDTO dto);
	void updateNoteAndTag(ReceiptDTO dto);
	List<SupplierDTO> findNameSupplier(String name,Pageable pageable);
	List<ProductDTO> findNameProduct(String name, Pageable pageable);
	SupplierDTO quicksave( SupplierDTO supplier);


}
