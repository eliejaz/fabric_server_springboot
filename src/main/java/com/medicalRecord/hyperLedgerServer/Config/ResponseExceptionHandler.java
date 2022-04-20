package com.medicalRecord.hyperLedgerServer.Config;

import java.util.concurrent.TimeoutException;

import java.util.NoSuchElementException;

import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric_ca.sdk.exception.IdentityException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import com.fasterxml.jackson.core.exc.StreamReadException;
//import com.fasterxml.jackson.databind.DatabindException;

import lombok.extern.slf4j.Slf4j;

@EnableWebMvc
@Slf4j
@ControllerAdvice
public class ResponseExceptionHandler {

 
	ResponseExceptionHandler() {
 
	}

	@ExceptionHandler(value = { NoSuchElementException.class })
	@ResponseBody
	protected ResponseEntity<?> handleConflict(RuntimeException ex, WebRequest request) {
		log.error(ex.getMessage());
		return ResponseEntity.badRequest().body("RuntimeException :"+ex.getMessage());

	}
 	
	@ExceptionHandler(value = { IdentityException.class  })
	@ResponseBody
	protected ResponseEntity<?> handleIdentityException(IdentityException ex, WebRequest request) {
 
		log.error(ex.getMessage());
		return ResponseEntity.badRequest().body("IdentityException :"+ex.getMessage());
	}
	@ExceptionHandler(value = { ContractException.class  })
	@ResponseBody
	protected ResponseEntity<?> handleContractException(ContractException ex, WebRequest request) {
 
		log.error(ex.getMessage());
		return ResponseEntity.badRequest().body("ContractException :"+ex.getMessage());
	}
	
	@ExceptionHandler(value = { TimeoutException.class   })
	@ResponseBody
	protected ResponseEntity<?> handleTimeOutExeption(TimeoutException ex, WebRequest request) {
 
		log.error(ex.getMessage());
		return ResponseEntity.badRequest().body("TimeoutException :"+ex.getMessage());
	}
	@ExceptionHandler(value = { InterruptedException.class   })
	@ResponseBody
	protected ResponseEntity<?> handleInterruptedException(InterruptedException ex, WebRequest request) {
 
		log.error(ex.getMessage());
		return ResponseEntity.badRequest().body("InterruptedException :"+ex.getMessage());
	}
	
	@ExceptionHandler(value = { Exception.class, IllegalArgumentException.class, IllegalStateException.class })
	@ResponseBody
	protected ResponseEntity<?> handleAllConflict(Exception ex, WebRequest request) {
 
		log.error(ex.getMessage());
		return ResponseEntity.badRequest().body("Exception :"+ex.getMessage());
	}
 
 
}

