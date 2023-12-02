package com.box.step8Juc.compltetable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author tangsx
 * @createTime 2023/11/11 19:46
 * @description
 */
public class CompletableFutureAPI1Demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testGet();
        testGetTimeOut();
        testJoin();
        testGetNow();
        testComplete();
    }

    /**
     * 测试get方法
     */
    public static void testGet() {
        try {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

                try {
                    TimeUnit.SECONDS.sleep(3L);
                } catch (Exception e) {
                    throw new RuntimeException();
                }
                return "testGet";
            });
            System.out.println(future.get());
        } catch (Exception e) {
            System.out.println("程序出现异常");
        }
    }

    /**
     * 测试testGetTimeOut方法
     */
    public static void testGetTimeOut() {
        try {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

                try {
                    TimeUnit.SECONDS.sleep(3L);
                } catch (Exception e) {
                    throw new RuntimeException();
                }
                return "testGetTimeOut";
            });
            System.out.println(future.get(2, TimeUnit.SECONDS));
        } catch (Exception e) {
            System.out.println("异步任务超时");
        }
    }

    /**
     * 测试join方法，join方法与get方法相比，更多的是异常不被抛出
     */
    public static void testJoin() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (Exception e) {
                throw new RuntimeException();
            }
            return "testGet";
        });
        System.out.println(future.join());
    }

    /**
     * 如果完成，则返回结果值（或引发任何遇到的异常），否则返回给定的值IfAbsent。
     */
    public static void testGetNow() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (Exception e) {
                throw new RuntimeException();
            }
            return "testGet";
        });
        System.out.println(future.getNow("xxx"));
    }

    /**
     *
     */
    public static void testComplete() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (Exception e) {
                throw new RuntimeException();
            }
            return "testGet";
        });
        try {
            Thread.sleep(3000L);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        System.out.println(future.complete("xxx"));
        System.out.println(future.join());
    }
}
