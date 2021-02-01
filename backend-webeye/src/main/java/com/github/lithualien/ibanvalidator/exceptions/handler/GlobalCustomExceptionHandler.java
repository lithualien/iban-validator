package com.github.lithualien.ibanvalidator.exceptions.handler;

import com.github.lithualien.ibanvalidator.exceptions.InvalidIbanException;
import com.github.lithualien.ibanvalidator.vo.ExceptionVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class GlobalCustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler( {InvalidIbanException.class} )
    public final ResponseEntity<ExceptionVO> handleBadRequestExceptions(Exception exception, WebRequest webRequest) {
        return new ResponseEntity<>(
                getExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value(),
                        webRequest.getDescription(false)),
                HttpStatus.BAD_REQUEST
        );
    }

    private ExceptionVO getExceptionResponse(String error, Integer status, String description) {
        return new ExceptionVO(
                LocalDateTime.now(),
                status,
                error,
                description
        );
    }

}
