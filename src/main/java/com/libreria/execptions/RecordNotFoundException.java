package com.libreria.execptions;

public class RecordNotFoundException extends RuntimeException{
	public RecordNotFoundException(String message) {
		super(message);
	}
}
