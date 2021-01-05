package test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.core.Response.Status;

@JsonInclude(Include.NON_NULL)
public class ErrorDTO {
	
	private String error;
	
	@JsonProperty("error_description")
	private String errorDescription;
	
	@JsonProperty("error_uri")
	private String errorUri;
	
	public ErrorDTO (Status status) {
		this.error = status.name();
		this.errorDescription = status.getReasonPhrase();
	}
	
	public ErrorDTO (Status status, String message) {
		this.error = status.name();
		this.errorDescription = message;
	}
	
	public String getError() {
		return error;
	}
	
	public String getErrorDescription() {
		return errorDescription;
	}
	
	public String getErrorUri() {
		return errorUri;
	}
	
}
