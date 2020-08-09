package com.thoughtworks.rslist.component;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.exception.CommonError;
import com.thoughtworks.rslist.exception.InvalidIndexException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({InvalidIndexException.class, MethodArgumentNotValidException.class})
    public ResponseEntity exceptionHandler (Exception ex) throws JsonProcessingException {
        String errorMessage ;
        CommonError commonError = new CommonError();
        if (ex instanceof  MethodArgumentNotValidException)
            errorMessage  = "invalid param";
        else  errorMessage = ex.getMessage();
        commonError.setError(errorMessage);
        ObjectMapper objectMapper = new ObjectMapper();
        String errorJson = objectMapper.writeValueAsString(commonError);
        logger.error("测试错误日志输出：" + errorJson);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commonError);
    }
}