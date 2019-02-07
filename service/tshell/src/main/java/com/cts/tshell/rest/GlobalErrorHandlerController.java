package com.cts.tshell.rest;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.tshell.bean.ErrorResponse;

@RestController
public class GlobalErrorHandlerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalErrorHandlerController.class);
	
	@GetMapping("/test")
	public void errorHandlerTest(){
		LOGGER.debug("Inside errorHandlerTest()");
		//throw new RuntimeException("Bad Request");
	}
	
	@ExceptionHandler(InvalidDataAccessResourceUsageException.class)
	public ResponseEntity<ErrorResponse> handleError(InvalidDataAccessResourceUsageException ex){
		LOGGER.info("start");
		ErrorResponse error = new ErrorResponse();
		error.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
		LOGGER.debug("error : {} ", error);
		error.setReasonCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
}
