package com.box.step8Juc.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author tangsx
 * @createTime 2023/11/9 23:18
 * @description
 */
public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(() -> {
            Thread.sleep(3000L);
            return "TASK";
        });

        Thread thread = new Thread(task);
        thread.start();
        while (!task.isDone()) {
            System.out.println(task.get());
        }

        FutureTask<String> futureTask = new FutureTask<>(new ThreadCallable());

        Thread taskThread = new Thread(futureTask);
        taskThread.start();
        System.out.println(futureTask.get());
        System.out.println(futureTask.isDone());
    }
}

class ThreadCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("exec process method  call()");
        return "hello ";
    }
}
