package com.zoe.user.service;

import com.zoe.user.domain.User;

public interface UserService {
    User getById(Integer id);

    User getByIdReadOnly(Integer id);

    User getByIdMaster(Integer id);

    User getByIdSlave(Integer id);

    User getByIdSlaveButAccountMaster(Integer id);
}
