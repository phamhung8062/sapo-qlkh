package warehouseManagement.service;

import java.util.List;


import warehouseManagement.dto.SupplierDTO;

public interface SupplierSevice {
    int getTotalItem();
    int getTotalItemFind(List<SupplierDTO> receiptDTOList);
}
