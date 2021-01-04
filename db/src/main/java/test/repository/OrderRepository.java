package test.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import test.entity.Order;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {

    public List<Order> findOrdered() {
        return find("ORDER BY id").list();
    }
}
