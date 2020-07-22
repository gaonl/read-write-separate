package com.zoe.account.dao;

import com.zoe.account.domain.Account;

public interface AccountDao {
    Account getById(Integer id);
}
