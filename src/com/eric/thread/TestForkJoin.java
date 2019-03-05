package com.eric.thread;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

/**
 * @ClassName: TestForkJoin
 * @Description: 测试Fork/Join框架
 * @Author: Eric
 * @Date: 2019/3/5 0005
 * @Email: xiao_cui_vip@163.com
 */
public class TestForkJoin {
    //13795毫秒
    @Test
    public void test1() {
        Instant start = Instant.now();
        //创建一个线程池
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinCalculate<Long> task = new ForkJoinCalculate<Long>(0L, 10000000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);


        Instant end = Instant.now();
        System.out.println("执行耗时：" + Duration.between(start, end).toMillis() + "毫秒");

    }

    //使用普通的for循环  14689毫秒
    @Test
    public void test2() {
        Instant start = Instant.now();
        long sum = 0L;
        for (long i = 0L; i <= 10000000000L; i++) {
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("执行耗时：" + Duration.between(start, end).toMillis() + "毫秒");
    }

    //使用java8的并行流 10866毫秒
    @Test
    public void test3(){
        Instant start = Instant.now();
        long sum = LongStream.rangeClosed(0L, 1000000000000L)
                .parallel()
                .sum();
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("执行耗时：" + Duration.between(start, end).toMillis() + "毫秒");
    }
}
