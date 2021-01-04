package test.resource;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.dto.OrderDTO;
import test.entity.OrderStatus;
import test.exception.OrderApiException;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.Timestamp;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@Transactional
class OrderResourceIntegrationTest {

    @Inject
    OrderResource orderResource;

    @Test
    void testOrdersInternalService() throws OrderApiException {
        Assertions.assertEquals(0, orderResource.getOrders(null).size());

        Assertions.assertEquals(201, orderResource.createOrder(new OrderDTO(0L,   new Timestamp(System.currentTimeMillis()),"ludo", OrderStatus.CREATED)).getStatus());
        Assertions.assertEquals(201, orderResource.createOrder(new OrderDTO(0L,   new Timestamp(System.currentTimeMillis()),"loic", OrderStatus.APPROVED)).getStatus());

        Assertions.assertEquals(2, orderResource.getOrders(null).size());
    }
}
