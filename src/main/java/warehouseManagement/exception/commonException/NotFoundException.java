package warehouseManagement.exception.commonException;

public class NotFoundException extends RuntimeException{
    
    public NotFoundException(Integer pageNo)
    {
        super("Trang " + pageNo.toString() + " không tồn tại");
    }
    
    public NotFoundException(Long id)
    {
        super("Id  " + id.toString() + " không tồn tại");
    }
}
