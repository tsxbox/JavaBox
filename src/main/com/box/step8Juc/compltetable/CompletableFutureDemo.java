package com.box.step8Juc.compltetable;

import java.util.concurrent.*;

/**
 * @author tangsx
 * @createTime 2023/11/10 22:10
 * @description
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(future.get());
        System.out.println("线程结束");
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "test1";
        }, threadPool);
        System.out.println(future1.get());
        threadPool.shutdown();
    }
}
