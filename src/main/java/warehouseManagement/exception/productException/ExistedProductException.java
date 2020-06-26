package warehouseManagement.exception.productException;

public class ExistedProductException extends RuntimeException{

    public ExistedProductException(String name, String color, String size, String material){
        super("sản phẩm '" + name + "' với các thuộc tính: " +
                                        "màu sắc: '"+ color
                                    + "', kích thước '" + size +
                                        "', và chất liệu '" + material + "' đã tồn tại");
    }
}
