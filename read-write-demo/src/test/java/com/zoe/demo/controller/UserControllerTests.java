package com.zoe.demo.controller;

import com.zoe.order.domain.Order;
import com.zoe.user.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})

public class UserControllerTests {
    @Autowired
    private UserController userController;

    @Test
    public void business1() {
        //没有标签的，默认主库读取
        Map<String, Object> result = userController.business1(1);
        User user = (User) result.get("user");
        Order order = (Order) result.get("order");
        Assert.assertEquals("user name --------->", user.getName(), "user_master");
        Assert.assertEquals("user password --------->", user.getPassword(), "1234");
        Assert.assertTrue("user account balance --------->", user.getBalance() == 100);
        Assert.assertEquals("order name --------->", order.getName(), "order_master");
        Assert.assertTrue("order price --------->", order.getPrice() == 100);
    }

    @Test
    public void business2() {
        //@Master标签，从主库读取
        Map<String, Object> result = userController.business2(1);
        User user = (User) result.get("user");
        Order order = (Order) result.get("order");
        Assert.assertEquals("user name --------->", user.getName(), "user_master");
        Assert.assertEquals("user password --------->", user.getPassword(), "1234");
        Assert.assertTrue("user account balance --------->", user.getBalance() == 100);
        Assert.assertEquals("order name --------->", order.getName(), "order_master");
        Assert.assertTrue("order price --------->", order.getPrice() == 100);
    }

    @Test
    public void business3() {
        //@Slave标签，从备库读取
        Map<String, Object> result = userController.business3(1);
        User user = (User) result.get("user");
        Order order = (Order) result.get("order");
        Assert.assertEquals("user name --------->", user.getName(), "user_slave");
        Assert.assertEquals("user password --------->", user.getPassword(), "5678");
        Assert.assertTrue("user account balance --------->", user.getBalance() == 1000);
        Assert.assertEquals("order name --------->", order.getName(), "order_slave");
        Assert.assertTrue("order price --------->", order.getPrice() == 1000);
    }



    @Test
    public void business4() {
        //controller slave，user不加标签，order从主库读
        Map<String, Object> result = userController.business4(1);
        User user = (User) result.get("user");
        Order order = (Order) result.get("order");
        Assert.assertEquals("user name --------->", user.getName(), "user_slave");
        Assert.assertEquals("user password --------->", user.getPassword(), "5678");
        Assert.assertTrue("user account balance --------->", user.getBalance() == 1000);
        Assert.assertEquals("order name --------->", order.getName(), "order_master");
        Assert.assertTrue("order price --------->", order.getPrice() == 100);
    }



    @Test
    public void business5() {
        //controller slave，user从主库读，order不加标签
        Map<String, Object> result = userController.business5(1);
        User user = (User) result.get("user");
        Order order = (Order) result.get("order");
        Assert.assertEquals("user name --------->", user.getName(), "user_master");
        Assert.assertEquals("user password --------->", user.getPassword(), "1234");
        Assert.assertTrue("user account balance --------->", user.getBalance() == 100);
        Assert.assertEquals("order name --------->", order.getName(), "order_slave");
        Assert.assertTrue("order price --------->", order.getPrice() == 1000);
    }



    @Test
    public void business6() {
        //controller master，user\order都从备库读
        Map<String, Object> result = userController.business6(1);
        User user = (User) result.get("user");
        Order order = (Order) result.get("order");
        Assert.assertEquals("user name --------->", user.getName(), "user_slave");
        Assert.assertEquals("user password --------->", user.getPassword(), "5678");
        Assert.assertTrue("user account balance --------->", user.getBalance() == 1000);
        Assert.assertEquals("order name --------->", order.getName(), "order_slave");
        Assert.assertTrue("order price --------->", order.getPrice() == 1000);
    }


    @Test
    public void business7() {
        //controller slave，user\order都从主库读
        Map<String, Object> result = userController.business7(1);
        User user = (User) result.get("user");
        Order order = (Order) result.get("order");
        Assert.assertEquals("user name --------->", user.getName(), "user_master");
        Assert.assertEquals("user password --------->", user.getPassword(), "1234");
        Assert.assertTrue("user account balance --------->", user.getBalance() == 100);
        Assert.assertEquals("order name --------->", order.getName(), "order_master");
        Assert.assertTrue("order price --------->", order.getPrice() == 100);
    }


    @Test
    public void business8() {
        //controller master，user从备库读，account从主库读，order从备库读
        Map<String, Object> result = userController.business8(1);
        User user = (User) result.get("user");
        Order order = (Order) result.get("order");
        Assert.assertEquals("user name --------->", user.getName(), "user_slave");
        Assert.assertEquals("user password --------->", user.getPassword(), "5678");
        Assert.assertTrue("user account balance --------->", user.getBalance() == 100);
        Assert.assertEquals("order name --------->", order.getName(), "order_slave");
        Assert.assertTrue("order price --------->", order.getPrice() == 1000);
    }

}
