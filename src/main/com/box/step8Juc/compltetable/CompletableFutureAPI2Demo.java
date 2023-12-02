package com.box.step8Juc.compltetable;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author tangsx
 * @createTime 2023/11/11 21:35
 * @description
 */
public class CompletableFutureAPI2Demo {
    public static void main(String[] args) {
        // testApply();
        testHandle();
    }

    private static void testHandle() {
        ExecutorService threadPool = null;
        try {
            threadPool = Executors.newFixedThreadPool(3);
            CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3L);
                } catch (Exception e) {
                    System.out.println("catch程序出现异常");
                }
                return new StringBuilder("::::::计算结果1::::::");
            }, threadPool).handle((v, e) -> {
                System.out.println(v);
                return v + "获取1";
            }).handle((v, e) -> {
                System.out.println(v);
                try {
                    int a = 1 / 0;
                } catch (Exception e1) {
                    throw new NumberFormatException();
//                    return "exception";
                }
                return v + "获取2";
            }).handle((v, e) -> {
                // 如果上一步出现异常 v为null， e为上一个步骤产生的异常
                System.out.println(e);
                System.out.println(v);
                return v + "获取3";
            }).whenComplete((v, e) -> {
                if (Objects.isNull(e)) {
                    System.out.println("计算结果:" + v);
                } else {
                    System.out.println("whenComplete程序出现异常");
                }
            });
            System.out.println("exec==========other task");
        } finally {
            if (Objects.nonNull(threadPool)) {
                threadPool.shutdown();
            }
        }
    }

    private static void testApply() {
        ExecutorService threadPool = null;
        try {
            threadPool = Executors.newFixedThreadPool(3);
            CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3L);
                } catch (Exception e) {
                    System.out.println("catch程序出现异常");
                }
                return "::::::计算结果1::::::";
            }, threadPool).thenApply((v) -> {
                System.out.println(v);
                return v + "获取1";
            }).thenApply((v) -> {
                int a = 1 / 0;
                System.out.println(v);
                return v + "获取2";
            }).thenApply((v) -> {
                System.out.println(v);
                return v + "获取3";
            }).whenComplete((v, e) -> {
                if (Objects.isNull(e)) {
                    System.out.println("计算结果:" + v);
                } else {
                    System.out.println("whenComplete程序出现异常");
                }
            });
            System.out.println("exec==========other task");
        } finally {
            if (Objects.nonNull(threadPool)) {
                threadPool.shutdown();
            }
        }
    }
}
