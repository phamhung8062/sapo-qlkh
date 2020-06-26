package warehouseManagement.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateTime {

    public static String getDateStringToAddImage(Long epoch) {
        return new SimpleDateFormat("MM-dd-yyyy-HH-mm-ss").format(new Date(epoch * 1000));
    }

    public static String getDate(Long epoch) {
//        long epoch = System.currentTimeMillis()/1000;
        return new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date(epoch * 1000));
    }

    public static Long getDateEpochTime() {
        return System.currentTimeMillis() / 1000;
    }
}
