package com.eric.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: MyCallable
 * @Description: 刚看网上的一篇博客说实现多线程有四种方式，以前只知道两种，现在来测试一下后面新知道的两种实现带返回值的多线程的方法
 * @Author: Eric
 * @Date: 2019/3/4 0004
 * @Email: xiao_cui_vip@163.com
 */
public class MyCallable implements Callable {

    public static void main(String[] args) {
        //创建一个实例对象
        MyCallable myCallable = new MyCallable();
        //创建一个FutureTask对象
        FutureTask futureTask = new FutureTask<>(myCallable);
        //由FutureTask对象创建一个Thread对象
        Thread thread = new Thread(futureTask);
        thread.start();
    }
    @Override
    public Object call() throws Exception {
        System.out.println("这个方法是干嘛用的？？");
        return null;
    }
}
