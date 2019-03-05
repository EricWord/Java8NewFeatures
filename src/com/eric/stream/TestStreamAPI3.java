package com.eric.stream;

import com.eric.entity.Employee;
import com.sun.xml.internal.messaging.saaj.soap.XmlDataContentHandler;
import com.sun.xml.internal.messaging.saaj.soap.ver1_1.Envelope1_1Impl;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @ClassName: TestStreamAPI3
 * @Description:
 * @Author: Eric
 * @Date: 2019/3/5 0005
 * @Email: xiao_cui_vip@163.com
 */
public class TestStreamAPI3 {
    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Employee.Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
    );

    /*
        allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */
    @Test
    public void test1() {
        boolean b = emps.stream().allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);
        boolean b1 = emps.stream().anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);
        boolean b2 = emps.stream().noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);
    }

    @Test
    public void test2(){
        Optional<Employee> first = emps.stream().sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(first.get());

        System.out.println("--------------------------------");
        Optional<Employee> any = emps.parallelStream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(any.get());

    }

    @Test
    public  void test3(){
        long count = emps.stream().filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .count();
        System.out.println(count);
        Optional<Double> max = emps.stream()
                .map(Employee::getSalary)
                .max(Double::compare);
        System.out.println(max.get());
        Optional<Employee> min = emps.stream()
                .min((peo1, peo2) -> Double.compare(peo1.getSalary(), peo2.getSalary()));
        System.out.println(min.get());
    }

    //流进行了终止操作后不能再次使用
    @Test
    public void test4(){
        Stream<Employee> stream = emps.stream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE));
        long count = stream.count();
//        stream.map(Employee::getSalary)
//                .max(Double::compare);



    }
}
