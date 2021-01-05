package test.dto;

import test.entity.OrderItem;

public class OrderItemDTO{
	
	private Long id;
	
	private String productId;
	
	private Long quantity;
	
	public OrderItemDTO() {
		
	}
	
	public OrderItemDTO(OrderItem item) {
		id = item.getId();
		productId = item.getProductId();
		quantity = item.getQuantity();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getProductId() {
		return productId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
    public Long getQuantity() {
		return quantity;
	}
    
    public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
    
    public OrderItem toOrderItem() {
		OrderItem item = new OrderItem();
		item.setProductId(productId);
		item.setQuantity(quantity);
		return item;

	}
}
