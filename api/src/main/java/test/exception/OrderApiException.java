package test.exception;

import javax.ws.rs.core.Response.Status;
import java.util.Objects;

public class OrderApiException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private Status status;
	
	private String clientMessage;
	
	public OrderApiException(Status status) {
		Objects.requireNonNull(status);
		this.status = status;
	}
	
	public OrderApiException(Status status, String message) {
		super(message);
		Objects.requireNonNull(status);
		this.clientMessage = message;
		this.status = status;
	}
	
	public OrderApiException(Status status, Throwable cause) {
		super(cause);
		Objects.requireNonNull(status);
		this.status = status;
	}
	
	public OrderApiException(Status status, String message, Throwable cause) {
		super(message, cause);
		Objects.requireNonNull(status);
		this.clientMessage = message;
		this.status = status;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public String getClientMessage() {
		return clientMessage;
	}
	
}
