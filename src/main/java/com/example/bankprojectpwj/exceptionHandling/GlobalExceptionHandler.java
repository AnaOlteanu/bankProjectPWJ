package com.example.bankprojectpwj.exceptionHandling;

import com.example.bankprojectpwj.exceptions.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomerNotFoundException.class, AccountNotFoundException.class, TransacationNotFoundException.class,
            NoAccountsFoundForTheCustomer.class, CustomerAlreadyExists.class, BankBranchNotFound.class})
    public ResponseEntity<Map<String, String>> handleException(RuntimeException exception){
        Map<String, String> responseParameters = new HashMap<>();
        responseParameters.put("Reason", exception.getMessage());
        responseParameters.put("DateTime", LocalDateTime.now().toString());

        return ResponseEntity.badRequest().body(responseParameters);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException exception){
        Map<String, Object> responseParam = new HashMap<>();
        List<String> errors = exception.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        responseParam.put("Reason ", errors);
        responseParam.put("Date Time ", LocalDateTime.now().toString());

        return ResponseEntity.badRequest().body(responseParam);
    }


}
