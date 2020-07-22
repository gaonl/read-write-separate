package com.zoe.order.dao;

import com.zoe.order.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaonl on 2018/9/28.
 */
@Repository("orderDao") //bean ID=orderDao
@Qualifier("orderDao") //Qualifier=orderDao
public class OrderDaoImpl implements OrderDao {

    @Autowired(required = true)
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public static final RowMapper<Order> ORDER_ROW_MAPPER = new RowMapper<Order>() {
        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setName(rs.getString("name"));
            order.setPrice(rs.getInt("price"));
            order.setOrderDateTime(rs.getLong("order_date_time"));
            return order;
        }
    };

    @Override
    public Order getById(Integer id) {
        String sql = "select id,name,price,order_date_time from t_order where id=:id;";
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);

        List<Order> orderList = namedParameterJdbcTemplate.query(sql, param, ORDER_ROW_MAPPER);
        if (orderList != null && orderList.size() > 0) {
            return orderList.get(0);
        } else {
            return null;
        }
    }
}
