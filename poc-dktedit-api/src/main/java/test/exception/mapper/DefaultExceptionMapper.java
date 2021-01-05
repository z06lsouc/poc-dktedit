package test.exception.mapper;

import org.jboss.logging.Logger;
import test.dto.ErrorDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
/**
 * Ici on doit faire une classe par exception qu'on veut gérer.
 * Cette classe c'est pour toute les exception non gérer par les autres mapper
 */
public class DefaultExceptionMapper implements ExceptionMapper<Exception> {
	
	private static final Logger LOGGER = Logger.getLogger(DefaultExceptionMapper.class);

	@Override
	public Response toResponse(Exception exception) {
		LOGGER.error(null, exception);
		return Response.serverError().entity(new ErrorDTO(Status.INTERNAL_SERVER_ERROR)).build();
	}

}
