package test.exception.mapper;

import org.jboss.logging.Logger;
import test.dto.ErrorDTO;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
	
	private static final Logger LOGGER = Logger.getLogger(JsonProcessingExceptionMapper.class);

	@Override
	public Response toResponse(WebApplicationException exception) {
		ErrorDTO error = null;
		Status status = Status.fromStatusCode(exception.getResponse().getStatus());
		if (exception.getResponse().getStatusInfo().getFamily() == Status.Family.CLIENT_ERROR) {
			LOGGER.info(null, exception);
			error = new ErrorDTO(status, exception.getMessage());
		}else {
			LOGGER.error(null, exception);
			error = new ErrorDTO(status);
		}
		return Response.status(status).entity(error).build();
	}

}
