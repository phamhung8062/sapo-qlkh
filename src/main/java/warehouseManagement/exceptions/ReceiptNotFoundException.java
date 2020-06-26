package warehouseManagement.exceptions;

import java.math.BigInteger;

public class ReceiptNotFoundException extends RuntimeException {
    public ReceiptNotFoundException(Long long1){
        super("Không tìm thấy đơn hàng có id : " + long1);
    }

    public ReceiptNotFoundException(String name){
        super("Không tìm thấy đơn hàng tên : " + name);
    }




}
