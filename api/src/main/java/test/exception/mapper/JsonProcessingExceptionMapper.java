package test.exception.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.jboss.logging.Logger;
import test.dto.ErrorDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
/**
 * Pour les exception dans le parsing de json,
 * Message trop verbeux 
 */
public class JsonProcessingExceptionMapper implements ExceptionMapper<JsonProcessingException> {
	
	private static final Logger LOGGER = Logger.getLogger(JsonProcessingExceptionMapper.class);

	@Override
	public Response toResponse(JsonProcessingException exception) {
		LOGGER.info(null, exception);
		return Response.status(Status.BAD_REQUEST).entity(new ErrorDTO(Status.BAD_REQUEST, "Invalid JSON input")).build();
	}

}
