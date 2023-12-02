package com.box.step8Juc.compltetable;

import java.util.concurrent.CompletableFuture;

/**
 * @author tangsx
 * @createTime 2023/11/11 22:55
 * @description
 */
public class CompletableFutureAPI4Demo {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> "result1").thenRun(() -> System.out.println("执行任务1"));
        System.out.println(CompletableFuture.supplyAsync(() -> "result2").thenAccept(r -> System.out.println("执行任务2")).join());
        System.out.println(CompletableFuture.supplyAsync(() -> "result2").thenApply(r -> {
            System.out.println("执行任务2");
            return "执行任务2";
        }).join());
    }
}
