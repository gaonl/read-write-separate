package com.zoe.account;

import com.zoe.account.domain.Account;
import com.zoe.account.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Test
    public void test() {
        Account account = accountService.get(1);
        System.out.println(account);
    }
}
