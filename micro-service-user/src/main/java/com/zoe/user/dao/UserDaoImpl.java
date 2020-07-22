package com.zoe.user.dao;

import com.zoe.user.domain.User;
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
@Repository("userDao") //bean ID=userDao
@Qualifier("userDao") //Qualifier=userDao
public class UserDaoImpl implements UserDao {

    @Autowired(required = true)
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public static final RowMapper<User> USER_ROW_MAPPER = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setRegisterDateTime(rs.getLong("register_date_time"));
            return user;
        }
    };

    @Override
    public User getById(Integer id) {
        String sql = "select id,name,password,register_date_time from t_user where id=:id;";
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);

        List<User> userList = namedParameterJdbcTemplate.query(sql, param, USER_ROW_MAPPER);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }
}
