package warehouseManagement.util;

import org.springframework.beans.factory.annotation.Autowired;
import warehouseManagement.repository.ProductRepository;

public class GenCodeAuto {

    @Autowired
    static ProductRepository productRepository;

    public static String genCode(Long idProduct){
        System.out.println("helo: " + idProduct);
        String code =  "SP00" + idProduct.toString();
        return code;
    }
}
