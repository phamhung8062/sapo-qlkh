//package warehouseManagement.exceptions;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.server.ResponseStatusException;
//
//
//@RestControllerAdvice
//public class ApiExceptionHandler {
//
//    /**
//     * Tất cả các Exception không được khai báo sẽ được xử lý tại đây
//
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorMessage handleAllException(Exception ex, WebRequest request) {
//        // quá trình kiểm soat lỗi diễn ra ở đây
//        return new ErrorMessage(500, ex.getLocalizedMessage());
//    }
//
//    /**
//     * IndexOutOfBoundsException sẽ được xử lý riêng tại đây
//     */
//    @ExceptionHandler(IndexOutOfBoundsException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ResponseStatusException TodoException(Exception ex, WebRequest request) {
//        //return new ErrorMessage(404, "Đối tượng không tồn tại");
//        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "haha");
//    }
//
//    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
//    public ErrorMessage Todo(Exception ex, WebRequest request) {
//        return new ErrorMessage(422, "Dữ liệu không hợp lệ");
//    }
//
//
////    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
////    public ErrorMessage hahaException(Exception ex, WebRequest request) {
////        return new ErrorMessage(500, "Đối tượng không tồn tại");
////    }
//}