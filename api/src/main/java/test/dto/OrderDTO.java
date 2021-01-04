package test.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import test.entity.Order;
import test.entity.OrderStatus;

import java.sql.Timestamp;

public class OrderDTO{
	
	/**
	 * Meme chose que pour spring, les annotation jackson ne permette pas d'avoir un swagger coherent
	 * Le @Schema est obligatoire
	 */
	@Schema(readOnly = true)
	@JsonIgnore
	private Long id;
	
	/**
	 * Pour que le timestamp n'est pas de format bizarre dans le swagger
	 */
	@Schema(implementation = String.class, description = "timestamp in ISO format", example="2021-01-01T00:00:00.000+00:00")
	private Timestamp moment;
	
	private String userName;
	
	private OrderStatus status;
	
	public OrderDTO() {	}

	public OrderDTO(Long id, Timestamp moment, String userName, OrderStatus status) {
		this.id = id;
		this.moment = moment;
		this.userName = userName;
		this.status = status;
	}

	public OrderDTO(Order order) {
		this.id = order.getId();
		this.moment = order.getMoment();
		this.userName = order.getUserName();
		this.status = order.getStatus();
	}
	
	@JsonProperty
	public Long getId() {
		return id;
	}
	
	public Timestamp getMoment() {
		return moment;
	}
	
	public void setMoment(Timestamp moment) {
		this.moment = moment;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public OrderStatus getStatus() {
		return status;
	}
	
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public Order toOrder() {
		Order order = new Order();
		order.setMoment(moment);
		order.setUserName(userName);
		order.setStatus(status);
		return order;
	}
	
}
