package com.box.step8Juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tangsx
 * @createTime 2023/11/12 17:16
 * @description
 */
public class SaleTicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                ticket.sale();
            }
        }, "t1").start();


        new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                ticket.sale();
            }
        }, "t2").start();


        new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                ticket.sale();
            }
        }, "t3").start();
    }
}

class Ticket {
    private int number = 200;

    ReentrantLock lock = new ReentrantLock();


    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第：\t" + (number--) + "\t 还剩下：" + number);
            }
        } finally {
            lock.unlock();
        }

    }
}
