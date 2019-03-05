package com.eric.stream;

import com.eric.beans.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @ClassName: TestStreamAPI2
 * @Description: 进一步测试StreamAPI
 * @Author: Eric
 * @Date: 2019/3/5 0005
 * @Email: xiao_cui_vip@163.com
 */
public class TestStreamAPI2 {

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66),
            new Employee(101, "张三", 18, 9999.99),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    /*
            映射
            map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
            flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
         */
    @Test
    public void test1() {
        Stream<String> strem = emps.stream()
                .map((e) -> e.getName());

        System.out.println("-------------------------------------------");
        List<String> stringList = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        Stream<String> stream1 = stringList.stream()
                .map(String::toUpperCase);
        //打印输出
        stream1.forEach(System.out::println);

        Stream<Stream<Character>> stream3 = stringList.stream()
                .map(TestStreamAPI2::filterCharacter);
        stream3.forEach((sm) -> {
            sm.forEach(System.out::println);
        });
        System.out.println("---------------------------------------------");
        Stream<Character> stream4 = stringList.stream()
                .flatMap(TestStreamAPI2::filterCharacter);
        stream4.forEach(System.out::println);

    }


    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    //中间操作之排序
    //排序分为自然排序(Comparable)和定制排序(Comparator)
    @Test
    public void test2() {
        emps.stream()
                .map(Employee::getName)
                .sorted()
                .forEach(System.out::println);
        System.out.println("------------------------------------");
        emps.stream()
                .sorted((x, y) -> {
                    if (x.getAge() == y.getAge()) {
                        return x.getName().compareTo(y.getName());
                    } else {
                        return  -Integer.compare(x.getAge(), y.getAge());
                    }

                } ).forEach(System.out::println);

    }

}
