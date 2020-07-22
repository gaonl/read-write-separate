package com.zoe.order.service;

import com.zoe.order.domain.Order;

public interface OrderService {
    Order getById(Integer id);

    Order getByIdReadOnly(Integer id);

    Order getByIdMaster(Integer id);

    Order getByIdSlave(Integer id);
}
