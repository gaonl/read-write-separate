package com.zoe.account.service;

import com.zoe.account.dao.AccountDao;
import com.zoe.account.domain.Account;
import com.zoe.common.rw.annotation.Master;
import com.zoe.common.rw.annotation.ReadOnly;
import com.zoe.common.rw.annotation.Slave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao userDao;

    @Override
    public Account get(Integer userId) {
        return userDao.getById(userId);
    }

    @Override
    @Master
    public Account getMaster(Integer userId) {
        return userDao.getById(userId);
    }

    @Override
    @Slave
    public Account getSlave(Integer userId) {
        return userDao.getById(userId);
    }

    @Override
    @ReadOnly
    public Account getReadOnly(Integer userId) {
        return userDao.getById(userId);
    }
}
