package com.hospital.handler;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hospital.entities.Appointment;
import com.hospital.entities.Receptionist;


@RestControllerAdvice
public class ExceptionAndValidationHandler extends ResponseEntityExceptionHandler {
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult br = ex.getBindingResult();
		List<ObjectError> errors = br.getAllErrors();
		List<String> list = new ArrayList<>();
		ResponseError responseError = new ResponseError("Validation failed", list);
		for (ObjectError error : errors) {
			list.add(error.getDefaultMessage());
		}
		System.out.println("Handler called");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
	}
	
	@ExceptionHandler(DoctorNotFound.class)
	ResponseEntity<ResponseError> doctorNotFoundException(DoctorNotFound ex) {
		ResponseError responseError = new ResponseError();
		responseError.setInfo(ex.getMessage());
	    responseError.setMessages(Collections.singletonList("Try again"));
		return ResponseEntity.badRequest().body(responseError);
	}
	
	
	@ExceptionHandler(AppointmentNotFound.class)
	ResponseEntity<ResponseError> appointmentNotFoundException(AppointmentNotFound ex) {
		ResponseError responseError = new ResponseError();
		responseError.setInfo(ex.getMessage());
	    responseError.setMessages(Collections.singletonList("Try again"));
		return ResponseEntity.badRequest().body(responseError);
	}
	
	@ExceptionHandler(PatientNotFound.class)
	ResponseEntity<ResponseError> patientNotFoundException(PatientNotFound ex) {
		ResponseError responseError = new ResponseError();
		responseError.setInfo(ex.getMessage());    
		responseError.setMessages(Collections.singletonList("Try again"));
		return ResponseEntity.badRequest().body(responseError);
	}
	
	@ExceptionHandler(ReceptionistNotFound.class)
	ResponseEntity<ResponseError> receptionistNotFoundException(ReceptionistNotFound ex){
		ResponseError responseError = new ResponseError();
		responseError.setInfo(ex.getMessage());
	    responseError.setMessages(Collections.singletonList("Try again"));
		return ResponseEntity.badRequest().body(responseError);
	}
	
	@ExceptionHandler(AppointmentPendingException.class)
	ResponseEntity<ResponseError> appointmentPendingException(AppointmentPendingException ex){
		ResponseError responseError = new ResponseError();
		responseError.setInfo(ex.getMessage());
		responseError.setMessages(Collections.singletonList("Changed AppointmentStatus to Done"));
		return ResponseEntity.badRequest().body(responseError);
	}
}
