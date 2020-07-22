package com.zoe.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * springboot启动类
 */
@SpringBootApplication
public class UserApplication {

    /**
     * main 方法，用户启动springboot
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
        context.start();

        synchronized (UserApplication.class) {
            while (true) {
                try {
                    UserApplication.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
