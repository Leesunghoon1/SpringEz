package com.myweb.www.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CommonExceptionAdvice {
   
	
	@ExceptionHandler(Exception.class)
	public String except(Exception ex) {
		log.info("ex>>>>>" + ex.getMessage());
		return "error_page";
	}
	
	
	
	
	
   @ExceptionHandler(NoHandlerFoundException.class)
   @ResponseStatus(HttpStatus.NOT_FOUND)
   public String handler404(NoHandlerFoundException ex) {
      return "custom404"; // view 라인에 custom404.jsp가 있어야함.
   }
}