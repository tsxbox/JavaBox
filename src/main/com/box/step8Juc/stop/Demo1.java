package com.box.step8Juc.stop;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author tangsx
 * @createTime 2023/11/14 21:36
 * @description
 */
public class Demo1 {

    static volatile boolean isStop = false;
    static AtomicBoolean isSetStop = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(20L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        thread.interrupt();
//        autoVolatile();
//        autoAtomicBoolean();
//        autoByApi();
    }

    public static void autoVolatile() {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + "\t isStop 被修改为true.程序停止");
                    break;
                }
                System.out.println("t1 hello volatile");
            }
        }, "t1");
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(20L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Thread t2 = new Thread(() -> isStop = true, "t2");
        t2.start();
    }

    public static void autoAtomicBoolean() {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (isSetStop.get()) {
                    System.out.println(Thread.currentThread().getName() + "\t isStop 被修改为true.程序停止");
                    break;
                }
                System.out.println("t1 hello volatile");
            }
        }, "t1");
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(20L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Thread t2 = new Thread(() -> isSetStop.set(true), "t2");
        t2.start();
    }


    public static void autoByApi() {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t isStop 被修改为true.程序停止");
                    break;
                }
                System.out.println("t1 hello volatile");
            }
        }, "t1");
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(20L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Thread t2 = new Thread(t1::interrupt, "t2");
        t2.start();
    }
}
