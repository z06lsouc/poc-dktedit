package test.resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import test.entity.Order;
import test.exception.OrderApiException;
import test.repository.OrderRepository;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OrderResourceUnitTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderResource orderResource;

    @Test
    void testGetOrders() throws OrderApiException {
        Mockito.when(orderRepository.listAll()).thenReturn(Collections.emptyList());
        Assertions.assertEquals(Collections.emptyList(), orderResource.getOrders(null));

        Mockito.when(orderRepository.listAll()).thenReturn(List.of(Mockito.mock(Order.class)));
        Assertions.assertEquals(1, orderResource.getOrders(null).size());

        Mockito.reset(orderRepository);
    }
}
