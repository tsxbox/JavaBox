package com.box.step8Juc.compltetable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author tangsx
 * @createTime 2023/11/11 23:31
 * @description
 */
public class CompletableFutureAPI6Demo {
    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future1 COME IN");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("程序中断");
            }
            System.out.println("future1");
            return "future1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2 COME IN");
            try {
                TimeUnit.MILLISECONDS.sleep(4000);
            } catch (InterruptedException e) {
                System.out.println("程序中断");
            }
            System.out.println("future2");
            return "future2";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("future3 COME IN");
                TimeUnit.MILLISECONDS.sleep(6000);
            } catch (InterruptedException e) {
                System.out.println("程序中断");
            }
            return "future3";
        });

        CompletableFuture<String> future = future1.applyToEither(future2, f -> {
            return f + "  is winner";
        });
        System.out.println(future.join());
    }
}
