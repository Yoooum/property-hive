package com.prprv.property.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Yoooum
 */
@Component
public class SysErrorController extends BasicErrorController {

    @Autowired
    public SysErrorController(ErrorAttributes errorAttributes, ServerProperties errorProperties) {
        super(errorAttributes, errorProperties.getError());
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request);
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(body, status);
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request) {
        // 获取异常参数
        ErrorAttributeOptions options = ErrorAttributeOptions.of(
                // 异常 message
                ErrorAttributeOptions.Include.MESSAGE,
                // 异常类型
                ErrorAttributeOptions.Include.EXCEPTION,
                // 异常堆栈
                //ErrorAttributeOptions.Include.STACK_TRACE,
                // 绑定的错误 error
                ErrorAttributeOptions.Include.BINDING_ERRORS
        );
        return getErrorAttributes(request, options);
    }
}
