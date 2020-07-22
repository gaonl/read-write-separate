package com.zoe.order;

import com.zoe.order.domain.Order;
import com.zoe.order.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void test() {
        Order order = orderService.getById(1);
        System.out.println(order);
    }
}
