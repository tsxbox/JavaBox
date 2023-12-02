package com.box.step8Juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author tangsx
 * @createTime 2023/11/12 14:07
 * @description
 */
public class Lock1Demo {

    /**
     * 程序入口
     *
     * @param args
     */
    public static void main(String[] args) {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        // 发邮件
        new Thread(() -> {
            phone1.sendEmail();
        }, "t1").start();

        // t1 先启动
        try {
            TimeUnit.MILLISECONDS.sleep(200L);
        } catch (Exception e) {
            throw new RuntimeException();
        }

        // 发短信
        new Thread(() -> {
            phone2.sendSms();
        }, "t2").start();
    }
}

/**
 * 资源类
 */
class Phone {

    public static synchronized void sendEmail() {
        try {
            TimeUnit.MILLISECONDS.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("===========sendEmail===========");
    }

    public synchronized void sendSms() {
        System.out.println("===========sendSms=============");
    }

    public void hello() {
        System.out.println("hello");
    }
}