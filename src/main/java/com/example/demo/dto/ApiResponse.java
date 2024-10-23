package com.example.demo.dto;

public class ApiResponse<T> {
	 private int status;
	    private T result;
	    private String message;

	    // Constructors

	    // Default Constructor
	    public ApiResponse() {
	    }

	    // Parameterized Constructor
	    public ApiResponse(int status, T result, String message) {
	        this.status = status;
	        this.result = result;
	        this.message = message;
	    }
	    
	    public int getStatus() {
	        return status;
	    }

	    public void setStatus(int status) {
	        this.status = status;
	    }

	    public T getResult() {
	        return result;
	    }

	    public void setResult(T result) {
	        this.result = result;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }
}
