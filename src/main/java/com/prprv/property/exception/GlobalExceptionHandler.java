package com.prprv.property.exception;

import com.prprv.property.common.response.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Yoooum
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    public R<String> userAlreadyExistsException(AppException e) {
        return R.error(e.getE());
    }
}
