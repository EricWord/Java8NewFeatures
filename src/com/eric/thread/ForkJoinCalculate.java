package com.eric.thread;

import java.util.concurrent.RecursiveTask;

/**
 * @ClassName: ForkJoinCalculate
 * @Description:
 * @Author: Eric
 * @Date: 2019/3/5 0005
 * @Email: xiao_cui_vip@163.com
 */
public class ForkJoinCalculate<L extends Number> extends RecursiveTask<Long> {

    private long start;
    private long end;
    private static final long THRESHOLD = 10000L;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public ForkJoinCalculate() {

    }

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;

            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoinCalculate<Number> left = new ForkJoinCalculate<Number>(start, middle);
            left.fork();//拆分，并将该子任务压入线程队列
            ForkJoinCalculate<Number> right = new ForkJoinCalculate<Number>(middle + 1, end);
            right.fork();//拆分，并将该子任务加入线程队列

            //最后合并
            long leftRes = (long) left.join();
            long rightRes = (long) right.join();
            return leftRes + rightRes;//这里暂时返回Null,等Fork/Join框架的问题解决了运行时要改过来
        }


    }
}
