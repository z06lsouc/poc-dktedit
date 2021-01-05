package test.resource;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import test.dto.OrderDTO;
import test.entity.Order;
import test.entity.OrderStatus;
import test.exception.OrderApiException;
import test.repository.OrderRepository;
import test.utils.LogUtils;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Path("/orders")
@Tag(ref = "orders")
public class OrderResource {

    @Inject
    OrderRepository orderRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "get all orders")
    @APIResponse(responseCode = "200", name = "OK")
    @APIResponse(ref = "error")
    @Parameter(name = "status", required = false)
    public List<OrderDTO> getOrders(@QueryParam("status") String status) throws OrderApiException {
        List<Order> list;
        if (status != null && !status.isBlank()) {
            try {
                OrderStatus orderStatus = OrderStatus.valueOf(status);
                LogUtils.logSomething("Listing all orders with status = " +orderStatus);
                list = orderRepository.list("status", orderStatus);
            } catch (IllegalArgumentException e) {
                throw new OrderApiException(Status.BAD_REQUEST, status + " is not a valid status");
            }
        } else {
            LogUtils.logSomething("Listing all orders");
            list = orderRepository.listAll();
        }
        return list.stream().map(OrderDTO::new).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "get order")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200")
    @APIResponse(ref = "error")
    public OrderDTO getOrder(@PathParam("id") Long id) {
        return new OrderDTO(orderRepository.findById(id));
    }

    @POST
    @Operation(summary = "create order")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(ref = "created")
    @APIResponse(ref = "error")
    public Response createOrder(@RequestBody OrderDTO orderToCreate) throws OrderApiException {
        if (orderToCreate == null) {
            throw new OrderApiException(Status.BAD_REQUEST, "Body is mandatory for creating order");
        }
        Order order = orderToCreate.toOrder();
        orderRepository.persist(order);
        return Response.created(URI.create("/orders/" + order.getId())).build();
    }

    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "update order")
    @APIResponse(responseCode = "200")
    @APIResponse(ref = "error")
    public OrderDTO updateOrder(@RequestBody OrderDTO orderUpdate, @PathParam("id") Long id) {
        Order order = orderRepository.findById(id);
        if (orderUpdate.getMoment() != null) {
            order.setMoment(orderUpdate.getMoment());
        }
        if (orderUpdate.getUserName() != null) {
            order.setUserName(orderUpdate.getUserName());
        }
        if (orderUpdate.getStatus() != null) {
            order.setStatus(orderUpdate.getStatus());
        }
        orderRepository.persist(order);
        return new OrderDTO(order);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "delete order")
    @APIResponse(responseCode = "204")
    @APIResponse(ref = "error")
    public Response updateOrder(@PathParam("id") Long id) {
        orderRepository.delete("id", id);
        return Response.noContent().build();
    }
}
