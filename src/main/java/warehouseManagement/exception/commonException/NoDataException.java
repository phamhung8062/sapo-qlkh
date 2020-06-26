package warehouseManagement.exception.commonException;

public class NoDataException extends RuntimeException{
    
    public NoDataException()
    {
        super("Không có dữ liệu");
    }
}