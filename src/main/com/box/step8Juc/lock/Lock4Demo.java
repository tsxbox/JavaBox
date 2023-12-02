package com.box.step8Juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tangsx
 * @createTime 2023/11/12 22:17
 * @description
 */
public class Lock4Demo {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t exec 外层");
                lock.lock();
                try {
                    lock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + "\t exec 中层");
                        lock.lock();
                        try {
                            System.out.println(Thread.currentThread().getName() + "\t exec 外层");
                        } finally {
                             lock.unlock();
                        }
                    } finally {
                        lock.unlock();
                    }
                } finally {
                    lock.unlock();
                }
            } finally {
//                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("===============");
            } finally {
                lock.unlock();
            }
        }, "t2").start();

    }
}
