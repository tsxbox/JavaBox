package com.box.step8Juc.lock;

/**
 * @author tangsx
 * @createTime 2023/11/12 15:42
 * @description
 */
public class Lock2Demo {

    Object object = new Object();

    public void method1() {
        synchronized (object) {
            System.out.println("----------------method1 synchronized object--------------");
        }
    }
    public synchronized void method2() {
        System.out.println("----------------method2 synchronized object--------------");
    }

    public static synchronized void method3() {
        System.out.println("----------------static method3 synchronized object--------------");
    }

    public static void main(String[] args) {
    }
}
