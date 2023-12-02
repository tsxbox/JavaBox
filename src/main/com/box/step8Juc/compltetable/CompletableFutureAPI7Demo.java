package com.box.step8Juc.compltetable;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author tangsx
 * @createTime 2023/11/12 0:11
 * @description
 */
public class CompletableFutureAPI7Demo {
    public static void main(String[] args) {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t任务启动");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("程序中断");
            }
            return 10;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t任务启动");
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("程序中断");
            }
            return 20;
        });
        CompletableFuture<Integer> all = future.thenCombine(future2, (x, y) -> {
            System.out.println("---结果合并---");
            return Math.addExact(x, y);
        });
        System.out.println(all.join());
    }
}
