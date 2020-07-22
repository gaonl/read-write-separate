package com.zoe.order.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Created by gaonl on 2018/9/28.
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Integer price;
    private Long orderDateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(Long orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(name, order.name) &&
                Objects.equals(price, order.price) &&
                Objects.equals(orderDateTime, order.orderDateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, price, orderDateTime);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", orderDateTime=" + orderDateTime +
                '}';
    }
}
