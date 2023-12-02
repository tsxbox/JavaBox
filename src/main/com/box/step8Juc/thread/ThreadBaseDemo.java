package com.box.step8Juc.thread;

/**
 * @author tangsx
 * @createTime 2023/11/9 21:21
 * @description
 */
public class ThreadBaseDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {

        }, "t1");
        t1.start();

        Object o = new Object();
        Thread t2 = new Thread(() -> {
            synchronized (o) {

            }
        }, "t2");
        t2.start();
    }
}
