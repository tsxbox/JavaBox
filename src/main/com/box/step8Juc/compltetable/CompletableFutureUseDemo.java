package com.box.step8Juc.compltetable;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author tangsx
 * @createTime 2023/11/11 0:40
 * @description
 */
public class CompletableFutureUseDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        diff();
    }

    /**
     * 完全可以替代Future
     *
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    public static void sameFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "-------come in process");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-------1s-------get result :" + result);
            return result;
        });
        System.out.println(Thread.currentThread().getName() + "线程先去忙其他任务");
        System.out.println(future.get());
    }

    public static void diff() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "-------come in process");
                int result = ThreadLocalRandom.current().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
//                System.out.println(1/0);
                System.out.println("-------1s-------get result :" + result);
                return result;
            }, threadPool).whenComplete((v, e) -> {
                if (Objects.isNull(e)) {
                    System.out.println("=====计算完成，更新系统" + v);
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                System.out.println("系统发生故障，请稍后再试");
                return null;
            });
            System.out.println(Thread.currentThread().getName() + "线程先去忙其他任务");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
        // TimeUnit.SECONDS.sleep(3L);
    }
}
