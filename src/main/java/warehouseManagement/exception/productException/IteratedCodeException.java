package warehouseManagement.exception.productException;

public class IteratedCodeException extends RuntimeException {

    public IteratedCodeException(String code, String entityName){
        super("mã code " + code + " đã tồn tại trong danh sách " + entityName);
    }

}
