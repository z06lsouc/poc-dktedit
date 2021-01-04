package test.exception.mapper;

import org.jboss.logging.Logger;
import test.dto.ErrorDTO;
import test.exception.OrderApiException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class OrderApiExceptionMapper implements ExceptionMapper<OrderApiException> {
	
	private static final Logger LOGGER = Logger.getLogger(JsonProcessingExceptionMapper.class);

	@Override
	public Response toResponse(OrderApiException exception) {
		LOGGER.info(null, exception);
		ErrorDTO errorDTO = null;
		if (exception.getClientMessage() != null && !exception.getClientMessage().isBlank()) {
			errorDTO = new ErrorDTO(exception.getStatus(), exception.getClientMessage());
		}else {
			errorDTO = new ErrorDTO(exception.getStatus());
		}
		return Response.status(exception.getStatus()).entity(errorDTO).build();
	}

}
