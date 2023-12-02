package com.box.step8Juc.compltetable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author tangsx
 * @createTime 2023/11/11 23:06
 * @description
 */
public class CompletableFutureAPI5Demo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        try {
            CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    System.out.println("程序中断");
                }
                System.out.println(Thread.currentThread().getName() + "\t 1号任务开始执行========");
                return "==task1==";
            }, threadPool).thenRun(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    System.out.println("程序中断");
                }
                System.out.println(Thread.currentThread().getName() + "\t 2号任务开始执行========");
            }).thenRun(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    System.out.println("程序中断");
                }
                System.out.println(Thread.currentThread().getName() + "\t 3号任务开始执行========");
            }).thenRunAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 4号任务开始执行========");
            }, threadPool);
            System.out.println(future.join());
        } finally {
            threadPool.shutdown();
        }
    }
}
