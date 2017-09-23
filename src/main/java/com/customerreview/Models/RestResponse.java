package com.customerreview.Models;

public class RestResponse {

	private long timestamp = 0L;
    private int status = 0;
    private String error = "";
    private String exception = "";
    private String message = "";
    private String path = "";
    private Object data = null;

    public RestResponse(long timestamp, int status, String error, String exception, String message, String path, Object data) {
        this.timestamp = timestamp;
    	this.status = status;
    	this.error = error;
    	this.exception = exception;
        this.message = message;
        this.path = path;
        this.data = data;
    }
    
    public RestResponse() {
    	
    }

    //getters and setters
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
