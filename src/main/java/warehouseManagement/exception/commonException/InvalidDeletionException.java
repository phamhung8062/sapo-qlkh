package warehouseManagement.exception.commonException;

public class InvalidDeletionException extends RuntimeException{
    public InvalidDeletionException(String cause)
    {
        super("Không thể xóa: " + cause);
    }
}