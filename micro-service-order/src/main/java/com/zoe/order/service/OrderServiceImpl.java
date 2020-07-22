package com.zoe.order.service;


import com.zoe.common.rw.annotation.Master;
import com.zoe.common.rw.annotation.ReadOnly;
import com.zoe.common.rw.annotation.Slave;
import com.zoe.order.dao.OrderDao;
import com.zoe.order.domain.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public Order getById(Integer id) {
        return orderDao.getById(id);
    }

    @Override
    @ReadOnly
    public Order getByIdReadOnly(Integer id) {
        return orderDao.getById(id);
    }

    @Override
    @Master
    public Order getByIdMaster(Integer id) {
        return orderDao.getById(id);
    }

    @Override
    @Slave
    public Order getByIdSlave(Integer id) {
        return orderDao.getById(id);
    }
}
