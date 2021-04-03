package com.config.boot.controller;

import org.springframework.hateoas.mediatype.vnderrors.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.config.boot.exception.ErrorMessage;
import com.config.boot.exception.InvalidTradeException;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@ControllerAdvice
@RequestMapping(produces = "application/json")
public class TradeControllerAdvice extends ResponseEntityExceptionHandler{
    @ExceptionHandler(InvalidTradeException.class)
    public ResponseEntity<ErrorMessage> invalidTradeDataException(final InvalidTradeException e) {
        return errorHandler(e, HttpStatus.BAD_REQUEST);
    }
  
    private ResponseEntity<ErrorMessage> errorHandler(
            final Exception exception, final HttpStatus httpStatus) {
    	
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
    	ErrorMessage msg = new ErrorMessage(httpStatus.value(), exception.getMessage() , LocalDate.now(), httpStatus.name());
      return new ResponseEntity<ErrorMessage>(msg, headers, HttpStatus.BAD_REQUEST);
    }

}