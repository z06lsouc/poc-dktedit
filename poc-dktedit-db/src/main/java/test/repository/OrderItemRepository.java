package test.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import test.entity.OrderItem;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderItemRepository implements PanacheRepository<OrderItem> {
}
