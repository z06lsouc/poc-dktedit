package test.resource;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import test.dto.OrderItemDTO;
import test.entity.Order;
import test.entity.OrderItem;
import test.repository.OrderItemRepository;
import test.repository.OrderRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Path("/orders/{id}/items")
@Tag(ref = "orders")
public class OrderItemResource {

	@Inject
	OrderRepository orderRepository;

    @Inject
    OrderItemRepository orderItemRepository;

    @PathParam("id")
    Long orderId;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderItemDTO> getOrderItem() {
        List<OrderItem> tmpList = orderItemRepository.list("order_id", orderId);
        if (!tmpList.isEmpty()) {
            tmpList.stream().map(OrderItemDTO::new).collect(Collectors.toList());
        }

        Order order = orderRepository.findById(orderId);
        if (order != null) {
            return order.getItems().stream().map(OrderItemDTO::new).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    @GET
    @Path("/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderItemDTO getOrderItem(@PathParam("itemId") Long itemId) {
        return new OrderItemDTO(orderItemRepository.findById(itemId));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrderItem(@RequestBody OrderItemDTO itemToCreate) {
        OrderItem orderItem = itemToCreate.toOrderItem();
        orderItem.setOrder(orderRepository.findById(orderId));
		orderItemRepository.persist(orderItem);
        return Response.created(URI.create("/orders/" + orderId + "/items/" + orderItem.getId())).build();
    }

    @PATCH
    @Path("/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderItemDTO updateOrderItem(@RequestBody OrderItemDTO orderItemUpdate, @PathParam("itemId") Long itemId) {
        OrderItem orderItem = orderItemRepository.findById(itemId);
        if (orderItemUpdate.getProductId() != null) {
            orderItem.setProductId(orderItemUpdate.getProductId());
        }
        if (orderItemUpdate.getQuantity() != null) {
            orderItem.setQuantity(orderItemUpdate.getQuantity());
        }
        return new OrderItemDTO(orderItem);
    }

    @DELETE
    @Path("/{itemId}")
    public Response updateOrder(@PathParam("itemId") Long itemId) {
		orderItemRepository.delete("id", itemId);
        return Response.noContent().build();
    }
}
