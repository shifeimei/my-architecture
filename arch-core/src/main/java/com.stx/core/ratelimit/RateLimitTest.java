package com.stx.core.ratelimit;

import java.util.List;
import java.util.concurrent.Executor;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author tongxiang.stx
 * @date 2019/07/16
 */
public class RateLimitTest {
    static final RateLimiter rateLimiter = RateLimiter.create(2.0);

    void submitTasks(List<Runnable> tasks, Executor executor) {
        for (Runnable task : tasks) {
            rateLimiter.acquire(); // may wait
            executor.execute(task);
        }
    }

    public static void main(String[] args) throws Exception {
        Long start = System.currentTimeMillis();

        double waitTime = rateLimiter.acquire(300999000);
        System.out.println("获取2个：" + (System.currentTimeMillis() - start) + " :" + waitTime);

        Thread.sleep(1000);

        waitTime = rateLimiter.acquire(6);
        System.out.println("获取4个：" + (System.currentTimeMillis() - start) + " :" + waitTime);

        waitTime = rateLimiter.acquire(2);
        System.out.println("获取2个：" + (System.currentTimeMillis() - start) + " :" + waitTime);
    }
}
