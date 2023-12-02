package com.box.step8Juc.lock;

/**
 * @author tangsx
 * @createTime 2023/11/12 22:38
 * @description
 */
public class Lock5Demo {
    public static void main(String[] args) {
        final Object obj1 = new Object();
        final Object obj2 = new Object();
        new Thread(() -> {
            synchronized (obj1) {
                System.out.println(Thread.currentThread().getName() + "持有锁obj1 尝试获取锁obj2");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (obj2) {
                    System.out.println("持有obj2");
                }
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (obj2) {
                System.out.println(Thread.currentThread().getName() + "持有锁obj2 尝试获取锁obj1");
                synchronized (obj1) {
                    System.out.println("持有obj1");
                }
            }
        }, "t2").start();
    }
}
