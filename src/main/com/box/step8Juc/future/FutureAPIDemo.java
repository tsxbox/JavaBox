package com.box.step8Juc.future;

import java.util.concurrent.*;

/**
 * @author tangsx
 * @createTime 2023/11/10 21:03
 * @description
 */
public class FutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        testBlock(new FutureTask<>(new TestCallAble()));
        testDone(new FutureTask<>(new TestCallAble()));
        testExpire(new FutureTask<>(new TestCallAble()));
    }

    /**
     * 测试阻塞，当futureTask调用get()方法时，会出现阻塞，知道结果出现，这样的情况对于程序是不友好的
     *
     * @param futureTask 异步任务
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    private static void testBlock(FutureTask<String> futureTask) throws ExecutionException, InterruptedException {
        Thread t1 = new Thread(futureTask, "testBlock()");
        t1.start();
        System.out.println(futureTask.get());
        System.out.println(Thread.currentThread().getName() + "\t 忙其他任务去了");
    }

    /**
     * 测试过期不候策略，当调用get()方法超过3S后，会抛出异常
     * 会导致异常过多，不美观，不健壮
     *
     * @param futureTask 异步任务
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     * @throws TimeoutException     TimeoutException
     */
    private static void testExpire(FutureTask<String> futureTask) throws ExecutionException, InterruptedException, TimeoutException {
        Thread t1 = new Thread(futureTask, "testExpire()");
        t1.start();
        System.out.println(futureTask.get(3, TimeUnit.SECONDS));
        System.out.println(Thread.currentThread().getName() + "\t 忙其他任务去了");
    }

    /**
     * 通过轮询不断的判断任务是否执行完毕（通常的做法，尽量不要造成阻塞，通过休眠避免cpu被完全占用）
     * 轮询的方式会耗费无谓的资源
     * 而且也不见得能及时的得到计算结果
     *
     * @param futureTask futureTask
     * @throws ExecutionException ExecutionException
     * @throws InterruptedException InterruptedException
     */
    private static void testDone(FutureTask<String> futureTask) throws ExecutionException, InterruptedException {
        Thread t1 = new Thread(futureTask, "testDone()");
        t1.start();
        while (true) {
            if (futureTask.isDone()) {
                System.out.println(futureTask.get());
                break;
            } else {
                TimeUnit.SECONDS.sleep(5L);
                System.out.println("程序执行,不要再催了");
            }
        }
    }

}

class TestCallAble implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t------exec futureTask");
        TimeUnit.SECONDS.sleep(5L);
        return "task over";
    }
}
