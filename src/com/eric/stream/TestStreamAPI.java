package com.eric.stream;

import com.eric.beans.Employee;
import com.sun.tracing.dtrace.ArgsAttributes;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @ClassName: TestStreamAPI
 * @Description: 测试StreamAPI
 * @Author: Eric
 * @Date: 2019/3/4 0004
 * @Email: xiao_cui_vip@163.com
 */
public class TestStreamAPI {

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66),
            new Employee(101, "张三", 18, 9999.99),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55));

    //创建Stream
    @Test
    public void test1() {
        //方法1:通过collection提供的两个方法：stream() parallelStream()
        List<String> list = new ArrayList<>();
        //获取一个顺序流
        Stream<String> stream = list.stream();
        //获取一个并行流
        Stream<String> parallelStream = list.parallelStream();
        //方法2:通过Arrays中的stream()方法获取一个数组流
        Integer[] integers = new Integer[10];
        Stream<Integer> integerStream = Arrays.stream(integers);
        //方法3：通过Stream中的静态方法of()
        Stream<Integer> integerStream1 = Stream.of(1, 2, 3, 4, 5, 6);
        //方法4；创建无限流
        //创建无限流的方法1:迭代
        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 2).limit(10);
        stream2.forEach(System.out::println);
        //创建无限流的方法2:生成
        Stream<Double> stream3 = Stream.generate(Math::random).limit(5);
        stream3.forEach(System.out::println);
    }

    //内部迭代：迭代操作由Stream API 内部完成
    @Test
    public void test2() {
        //所有中间操作不会做任何的处理
        Stream<Employee> stream = emps.stream()
                .filter((e) -> e.getAge() <= 35);
        //只有当做终止操作时，所有的中间操作会一次性地全部执行
        //称为"惰性求值"
        stream.forEach(System.out::println);
    }

    //外部迭代
    @Test
    public void test3() {
        Iterator<Employee> iterator = emps.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test4() {
        emps.stream().filter((e) -> e.getSalary() >= 5000)
//                .limit(3)
//                .skip(2)//跳过前两个
                .distinct()//去重
                .forEach(System.out::println);

    }

}
