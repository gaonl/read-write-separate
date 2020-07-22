package com.zoe.order.dao;


import com.zoe.order.domain.Order;

/**
 * Created by gaonl on 2018/9/28.
 */
public interface OrderDao {
    Order getById(Integer id);
}
