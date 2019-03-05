package com.eric.stream;

import com.eric.entity.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: TestStreamAPI4
 * @Description:
 * @Author: Eric
 * @Date: 2019/3/5 0005
 * @Email: xiao_cui_vip@163.com
 */
public class TestStreamAPI4 {

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 79, 6666.66, Employee.Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
    );

    /*
        归约
		reduce(T identity, BinaryOperator) /
		reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
	 */

    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        System.out.println("----------------------------------------");
        Optional<Double> reduce = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(reduce.get());
    }

    //搜索名字中出现“六”的次数
    //这个的输出结果并不正确，输出结果为0
    @Test
    public void test2() {
        Optional<Integer> num = emps.stream()
                .map(Employee::getName)
                .flatMap(TestStreamAPI2::filterCharacter)
                .map((ch) -> {
                    System.out.println(ch);//ch.equals("六")
                    //注意这里不能使用equals
                    if (ch == '六')
                        return 1;
                    else
                        return 0;
                }).reduce(Integer::sum);
        System.out.println(num.get());

    }

    //collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
    @Test
    public void test3() {
        List<String> list = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("----------------------------------");
        Set<String> set = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("----------------------------------");
        HashSet<String> hashSet = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);
    }

    @Test
    public void test4() {
        Optional<Double> collect = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.maxBy(Double::compare));
        System.out.println(collect.get());

        Optional<Employee> collect1 = emps.stream()
                .collect(Collectors.minBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(collect1.get());
        Double c2 = emps.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(c2);
        Double c3 = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(c3);
        Long c4 = emps.stream()
                .collect(Collectors.counting());
        System.out.println("---------------------");
        System.out.println(c4);
        DoubleSummaryStatistics c5 = emps.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("-----------------------");

        System.out.println(c5.getMax());

    }

    //分组
    @Test
    public void test5() {
        Map<Employee.Status, List<Employee>> collect = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(collect);

    }

    //多级分组
    @Test
    public void test6(){
        Map<Employee.Status, Map<String, List<Employee>>> collect = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {

                    if (e.getAge() >= 60)
                        return "老年";
                    else if (e.getAge() >= 35)
                        return "中年";
                    else
                        return "成年";
                })));
        System.out.println(collect);


    }

    //分区
    @Test
    public void test7(){
        Map<Boolean, List<Employee>> collect = emps.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() >= 5000));
        System.out.println(collect);
    }
    @Test
    public void test8(){
        String collect = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",", "---", "---"));
        System.out.println(collect);

    }
    @Test
    public void test9(){
        Optional<Double> collect = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.reducing(Double::sum));
        System.out.println(collect);


    }
}
