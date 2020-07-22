package com.zoe.order;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * springboot启动类
 */
@SpringBootApplication
public class OrderApplication {

    /**
     * main 方法，用户启动springboot
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
        context.start();

        synchronized (OrderApplication.class) {
            while (true) {
                try {
                    OrderApplication.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
