package com.eric.thread;

import com.sun.org.apache.xalan.internal.xsltc.dom.SAXImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName: DeCompileCallable
 * @Description: 反编译Callable
 * @Author: Eric
 * @Date: 2019/3/4 0004
 * @Email: xiao_cui_vip@163.com
 */
public class DeCompileCallable implements Callable<Object> {
    //线程数
    private String taskNum;

    public DeCompileCallable() {
    }

    public DeCompileCallable(String taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public Object call() throws Exception {
        System.out.println(">>>>>" + taskNum + "个任务启动<<<<<");
        Date date1 = new Date();
        Thread.sleep(1000);
        Date date2 = new Date();
        long time = date2.getTime() - date1.getTime();
        System.out.println(">>>>>" + taskNum + "个任务终止<<<<<");


        return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(">>>>>程序开始运行...<<<<<");
        Date date1 = new Date();
        //线程池的大小
        int taskSize = 5;
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        //创建多个有返回值的任务
        List<Future> list = new ArrayList<>();
        for (int i = 0; i < taskSize; i++) {
            DeCompileCallable c = new DeCompileCallable(i + " ");
            //执行任务并获取Futured对象
            Future<Object> future = pool.submit(c);
            list.add(future);
        }
        //关闭线程池
        pool.shutdown();
        //获取所有并发任务的执行结果
        for (Future f : list) {
            //从Future对象上获取任务的返回值
            System.out.println(">>>>>" + f.get().toString());
        }
        Date date2 = new Date();
        System.out.println("----程序结束运行----，程序运行时间【"
                + (date2.getTime() - date1.getTime()) + "毫秒】");

    }
}
