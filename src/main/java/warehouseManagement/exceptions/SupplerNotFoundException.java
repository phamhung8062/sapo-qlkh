package warehouseManagement.exceptions;

public class SupplerNotFoundException extends RuntimeException {
    public SupplerNotFoundException(Long long1){
        super("Không tìm thấy nhà cung cấp có id : " + long1);
    }

    public SupplerNotFoundException(String name){
        super("Không tìm thấy nhà cung cấp có tên : " + name);
    }




}
