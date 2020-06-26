package warehouseManagement.exception.commonException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import warehouseManagement.exception.ApiError;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(value = UnknownException.class)
    public ResponseEntity<ApiError> exception(UnknownException exception){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,"Lỗi không xác định","Lỗi");
        ResponseEntity<?> responseEntity = new ResponseEntity<>(apiError,apiError.getStatus());
        return new ResponseEntity<>(apiError,apiError.getStatus());
    }
    @ExceptionHandler(value = NoDataException.class)
    public ResponseEntity<ApiError> exception(NoDataException exception)
    {
        ApiError apiError = new ApiError(HttpStatus.NO_CONTENT,exception.getMessage(),"Lỗi");
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ApiError> exception(NotFoundException ex)
    {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,ex.getMessage(),"Lỗi");
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = DupplicateFieldException.class)
    public ResponseEntity<ApiError> exception(DupplicateFieldException ex)
    {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,ex.getMessage(),"Lỗi");
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
            List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
            String mess = String.join(" ",errors);
            return new ResponseEntity<>(new ApiError(status,mess,errors),status);
    }

//    @ExceptionHandler(value = TransactionSystemException.class)
//    protected ResponseEntity<Object> handle(TransactionSystemException ex){
//        System.out.printf("b");
//        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,ex.getMessage(),"Lỗi");
//        return new ResponseEntity<>(apiError, apiError.getStatus());
//    }

    @ExceptionHandler(value = TransactionSystemException.class )
    public ResponseEntity<Object> handleConstraintViolation(Exception ex, WebRequest request) {
        Throwable cause = ((TransactionSystemException) ex).getRootCause();
        if (cause instanceof ConstraintViolationException) {
            System.out.printf("a");
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
            // do something here
        }
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,ex.getMessage(),"Lỗi");
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
