package com.oddle.app.weather.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {
	public Response(){}
	public ResponseEntity<String> customResponse(HttpStatus httpStatus, String bodyMessage){
		return ResponseEntity.status(httpStatus).body(bodyMessage);    	
	}
}
