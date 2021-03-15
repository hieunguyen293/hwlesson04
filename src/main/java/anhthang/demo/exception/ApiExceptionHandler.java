package anhthang.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String unknownErr(Exception e, WebRequest request){
        return "Unknown error";
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String invalidInputException(Exception e, WebRequest request){
        return "Thieu input dau vao";
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String Login(Exception e, WebRequest request){
        return "Sai tai khoan hoac mat khau";
    }

    /*
    request duoc thuc hien qua cac tang`:
    interceptor;
    filter;
    servlet;   khong truyen tham so vao param la loi o tang` nay`.
    restController;

    khi do se su dung exception hendler

    */






}
