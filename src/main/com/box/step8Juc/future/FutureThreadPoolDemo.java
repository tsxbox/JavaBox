package com.box.step8Juc.future;

import java.util.concurrent.*;

/**
 * @author tangsx
 * @createTime 2023/11/10 20:41
 * @description
 */
public class FutureThreadPoolDemo {
    public static void main(String[] args) {
        testSerial();
        testAsync();
    }

    /**
     * 测试异步执行
     */
    private static void testAsync() {
        long startTime = System.currentTimeMillis();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        FutureTask<String> task1 = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(3L);
            return Thread .currentThread().getName();
        });
        FutureTask<String> task2 = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(3L);
            return Thread .currentThread().getName();
        });
        FutureTask<String> task3 = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(3L);
            return Thread .currentThread().getName();
        });
        threadPool.submit(task1);
        threadPool.submit(task2);
        threadPool.submit(task3);

        try {
            System.out.println(task1.get());
            System.out.println(task2.get());
            System.out.println(task3.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("async-------cost time:" + (endTime - startTime) + "ms");
        System.out.println(Thread.currentThread().getName() + "-------end");
        threadPool.shutdown();
    }

    /**
     * 顺序执行
     */
    private static void testSerial() {
        long startTime = System.currentTimeMillis();

        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Serial -------cost time:" + (endTime - startTime) + "ms");
        System.out.println(Thread.currentThread().getName() + "-------end");
    }
}
