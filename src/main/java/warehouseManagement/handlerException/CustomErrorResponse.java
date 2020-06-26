package warehouseManagement.handlerException;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class CustomErrorResponse {

    private HttpStatus httpStatus;
    private List<String> errors;
    private Boolean status;

    public CustomErrorResponse() {
    }


    public CustomErrorResponse(Boolean status, HttpStatus badRequest, List<String> errors) {
        this.httpStatus = badRequest;
        this.errors = errors;
        this.status = status;
    }

    public CustomErrorResponse(Boolean status,HttpStatus badRequest, String error) {
        this.httpStatus = badRequest;
        this.errors = Arrays.asList(error);
        this.status = status;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }


    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
