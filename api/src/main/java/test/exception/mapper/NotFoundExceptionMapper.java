package test.exception.mapper;

import org.jboss.logging.Logger;
import test.dto.ErrorDTO;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * mapper pour les 404
 * malgres que NotFoundException est une WebApplicationException, elle ne sera pas traitée pour le type générique
 * on est dont obligé de lui faire son propre mapper
 */
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
	
	private static final Logger LOGGER = Logger.getLogger(JsonProcessingExceptionMapper.class);

	@Override
	public Response toResponse(NotFoundException exception) {
		LOGGER.info(null, exception);
		return Response.status(Status.NOT_FOUND).entity(new ErrorDTO(Status.NOT_FOUND, "No resource exist for the called uri")).build();
	}

}
