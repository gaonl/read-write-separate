package com.zoe.account.domain;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer userId;
    private Integer balance;
    private Long registerDateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Long getRegisterDateTime() {
        return registerDateTime;
    }

    public void setRegisterDateTime(Long registerDateTime) {
        this.registerDateTime = registerDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(userId, account.userId) &&
                Objects.equals(balance, account.balance) &&
                Objects.equals(registerDateTime, account.registerDateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, balance, registerDateTime);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId=" + userId +
                ", balance=" + balance +
                ", registerDateTime=" + registerDateTime +
                '}';
    }
}
