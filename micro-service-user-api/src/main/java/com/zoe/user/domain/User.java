package com.zoe.user.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Created by gaonl on 2018/9/28.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String password;
    private Long registerDateTime;
    private Integer balance;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRegisterDateTime() {
        return registerDateTime;
    }

    public void setRegisterDateTime(Long registerDateTime) {
        this.registerDateTime = registerDateTime;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    /**
     * 获取用户的注册时间
     *
     * @return 用户的注册时间
     */
    public String getDisplayRegisterDateTime() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return df.format(this.registerDateTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(registerDateTime, user.registerDateTime) &&
                Objects.equals(balance, user.balance);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, password, registerDateTime, balance);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", registerDateTime=" + registerDateTime +
                ", balance=" + balance +
                '}';
    }
}
