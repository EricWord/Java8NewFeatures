package com.eric.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName: TestDateTimeFormatter
 * @Description: Java8中新的时间日期格式化类
 * @Author: Eric
 * @Date: 2019/3/5 0005
 * @Email: xiao_cui_vip@163.com
 */
public class TestDateTimeFormatter {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        Callable<LocalDate> task = new Callable<LocalDate>() {

            @Override
            public LocalDate call() throws Exception {
                LocalDate ld = LocalDate.parse("20190305", dtf);

                return ld;
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<LocalDate>> res = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            res.add(pool.submit(task));
        }
        //遍历输出
        for (Future<LocalDate> future : res) {
            System.out.println(future.get());

        }
        pool.shutdown();


    }
}
