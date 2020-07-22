package com.zoe.account.dao;

import com.zoe.account.domain.Account;
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
@Repository("accountDao")
@Qualifier("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired(required = true)
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public static final RowMapper<Account> ACCOUNT_ROW_MAPPER = new RowMapper<Account>() {
        @Override
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            Account account = new Account();
            account.setId(rs.getInt("id"));
            account.setUserId(rs.getInt("user_id"));
            account.setBalance(rs.getInt("balance"));
            account.setRegisterDateTime(rs.getLong("register_date_time"));
            return account;
        }
    };

    @Override
    public Account getById(Integer userId) {
        String sql = "select id,user_id,balance,register_date_time from t_account where user_id=:userId;";
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);

        List<Account> accountList = namedParameterJdbcTemplate.query(sql, param, ACCOUNT_ROW_MAPPER);
        if (accountList != null && accountList.size() > 0) {
            return accountList.get(0);
        } else {
            return null;
        }
    }
}
