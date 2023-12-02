package com.box.step8Juc.lock;

/**
 * @author tangsx
 * @createTime 2023/11/12 21:46
 * @description
 */
public class Lock3Demo {

    public static void main(String[] args) {
        final Object object = new Object();
        reEntryM1(object);

        Lock3Demo demo = new Lock3Demo();

        new Thread(demo::m1, "t2").start();
        new Thread(demo::m1, "t3").start();
    }

    public static void reEntryM1(Object object) {
        new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "\t-------外层调用");
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + "\t-------中层调用");
                    synchronized (object) {
                        System.out.println(Thread.currentThread().getName() + "\t-------内层调用");
                    }
                }
            }
        }, "t1").start();
    }

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + "exec  M1");
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + "exec code block");
        }
        this.m2();
    }

    public synchronized void m2() {
        System.out.println(Thread.currentThread().getName() + "exec  M2");
        this.m3();
    }

    public synchronized void m3() {
        System.out.println(Thread.currentThread().getName() + "exec  M3");
    }

}
