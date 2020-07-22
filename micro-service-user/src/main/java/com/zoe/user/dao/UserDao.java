package com.zoe.user.dao;


import com.zoe.user.domain.User;

/**
 * Created by gaonl on 2018/9/28.
 */
public interface UserDao {
    User getById(Integer id);
}
