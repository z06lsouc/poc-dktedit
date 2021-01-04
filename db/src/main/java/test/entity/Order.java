package test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Timestamp moment = new Timestamp(System.currentTimeMillis());
	
	@Column(name="user_name")
	private String userName;
	
	private OrderStatus status;
	
	@OneToMany(mappedBy = "order")
	private Set<OrderItem> items;
	
	public Long getId() {
		return id;
	}
	
	public OrderStatus getStatus() {
		return status;
	}
	
	public void setStatus(OrderStatus status) {
		this.status = status;
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
	
	public Set<OrderItem> getItems() {
		return items;
	}
}
