package test;

import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import test.dto.ErrorDTO;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@OpenAPIDefinition(
		info= @Info(title = "Orders APIs", version = "1.0"),
		components = @Components(
				responses = {
						@APIResponse(
								name="error",
								responseCode = "4XX/5XX",
								description="Any error occured resulting in 4XX or 5XX",
								content = @Content(
										mediaType = MediaType.APPLICATION_JSON,
										schema = @Schema(implementation=ErrorDTO.class)
								)
						),
						@APIResponse(
								name="created",
								responseCode = "201",
								headers = @Header(name = "Location", description = "uri to access to created resource")
						)
				}
		)
		,tags = {
				@Tag(name="order", description = "orders api")
		}
)
public class OrderApplication extends Application{

}
