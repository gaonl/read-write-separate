package com.zoe.demo.controller;

import com.zoe.common.rw.annotation.Master;
import com.zoe.common.rw.annotation.ReadOnly;
import com.zoe.common.rw.annotation.Slave;
import com.zoe.order.domain.Order;
import com.zoe.order.service.OrderService;
import com.zoe.user.domain.User;
import com.zoe.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;


    public Map<String, Object> business1(Integer id) {
        User user = userService.getById(id);
        Order order = orderService.getById(id);

        Map<String, Object> result = new HashMap<>();

        result.put("user", user);
        result.put("order", order);

        return result;
    }

    @Master
    public Map<String, Object> business2(Integer id) {
        User user = userService.getById(id);
        Order order = orderService.getById(id);

        Map<String, Object> result = new HashMap<>();

        result.put("user", user);
        result.put("order", order);

        return result;
    }

    @Slave
    public Map<String, Object> business3(Integer id) {
        User user = userService.getById(id);
        Order order = orderService.getById(id);

        Map<String, Object> result = new HashMap<>();

        result.put("user", user);
        result.put("order", order);

        return result;
    }

    /**
     * controller slave，user不加标签，order从主库读
     *
     * @param id
     * @return
     */
    @Slave
    public Map<String, Object> business4(Integer id) {
        User user = userService.getById(id);
        Order order = orderService.getByIdMaster(id);

        Map<String, Object> result = new HashMap<>();

        result.put("user", user);
        result.put("order", order);

        return result;
    }

    /**
     * controller slave，user从主库读，order不加标签
     *
     * @param id
     * @return
     */
    @Slave
    public Map<String, Object> business5(Integer id) {
        User user = userService.getByIdMaster(id);
        Order order = orderService.getById(id);

        Map<String, Object> result = new HashMap<>();

        result.put("user", user);
        result.put("order", order);

        return result;
    }

    /**
     * controller master，user\order都从备库读
     *
     * @param id
     * @return
     */
    @Master
    public Map<String, Object> business6(Integer id) {
        User user = userService.getByIdSlave(id);
        Order order = orderService.getByIdSlave(id);

        Map<String, Object> result = new HashMap<>();

        result.put("user", user);
        result.put("order", order);

        return result;
    }

    /**
     * controller master，user\order都从备库读
     *
     * @param id
     * @return
     */
    @Slave
    public Map<String, Object> business7(Integer id) {
        User user = userService.getByIdMaster(id);
        Order order = orderService.getByIdMaster(id);

        Map<String, Object> result = new HashMap<>();

        result.put("user", user);
        result.put("order", order);

        return result;
    }

    /**
     * controller master，user从备库读，account从主库读，order从主库读
     *
     * @param id
     * @return
     */
    @Slave
    public Map<String, Object> business8(Integer id) {
        User user = userService.getByIdSlaveButAccountMaster(id);
        Order order = orderService.getByIdSlave(id);

        Map<String, Object> result = new HashMap<>();

        result.put("user", user);
        result.put("order", order);

        return result;
    }
}
