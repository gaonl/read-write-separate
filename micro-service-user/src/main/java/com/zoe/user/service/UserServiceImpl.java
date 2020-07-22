package com.zoe.user.service;

import com.zoe.account.domain.Account;
import com.zoe.account.service.AccountService;
import com.zoe.common.rw.annotation.Master;
import com.zoe.common.rw.annotation.ReadOnly;
import com.zoe.common.rw.annotation.Slave;
import com.zoe.user.dao.UserDao;
import com.zoe.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private AccountService accountService;

    @Override
    public User getById(Integer id) {
        User user = userDao.getById(id);
        Account account = accountService.get(id);
        if (account != null && account.getBalance() != null) {
            user.setBalance(account.getBalance());
        }
        return user;
    }

    @Override
    @ReadOnly
    public User getByIdReadOnly(Integer id) {
        return getById(id);
    }

    @Override
    @Master
    public User getByIdMaster(Integer id) {
        return getById(id);
    }

    @Override
    @Slave
    public User getByIdSlave(Integer id) {
        return getById(id);
    }

    @Override
    @Slave
    public User getByIdSlaveButAccountMaster(Integer id) {
        User user = userDao.getById(id);
        Account account = accountService.getMaster(id);
        if (account != null && account.getBalance() != null) {
            user.setBalance(account.getBalance());
        }
        return user;
    }
}
