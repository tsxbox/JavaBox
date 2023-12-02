package com.box.step8Juc.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author tangsx
 * @createTime 2023/11/9 22:55
 * @description
 */
public class DaemonDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "开始运行，" + (Thread.currentThread().isDaemon() ? "守护线程" : "用户线程"));
            while (Boolean.TRUE) {

            }
        }, "t1");
        // 当设置线程为守护线程时，主线程结束，t1，线程结束
        // t1.setDaemon(true);
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t-------end主线程");
    }
}
