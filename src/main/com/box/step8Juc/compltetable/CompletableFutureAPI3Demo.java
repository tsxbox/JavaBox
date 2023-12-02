package com.box.step8Juc.compltetable;

import java.util.concurrent.CompletableFuture;

/**
 * @author tangsx
 * @createTime 2023/11/11 22:19
 * @description
 */
public class CompletableFutureAPI3Demo {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            return new StringBuilder("xxx");
        }).thenApply(f -> {
            return f.append("test1");
        }).thenApply(f -> {
            return f.append("test2");
        }).thenApply(f -> {
            return f.append("test3");
        }).thenApply(f -> {
            return f.append("test4");
        }).thenApply(f -> {
            return f.append("test6");
        }).thenApply(f -> {
            return f.append("test6");
        }).thenAccept(System.out::println);
    }
}
