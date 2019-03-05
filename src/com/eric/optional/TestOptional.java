package com.eric.optional;

import com.eric.entity.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * @ClassName: TestOptional
 * @Description:
 * @Author: Eric
 * @Date: 2019/3/5 0005
 * @Email: xiao_cui_vip@163.com
 */
public class TestOptional {
    /**
     * Optional.of(T t)用于创建一个Optional实例
     */

    @Test
    public void test1(){
        Optional<Employee> op = Optional.of(new Employee());
        Employee employee = op.get();

        System.out.println(employee);
    }

    /**
     * Optional.empty()用于创建一个空的Optional实例
     *
     *
     */
    @Test
    public void test2(){
        Optional<Employee>optional = Optional.empty();

        System.out.println(optional.get());
    }

    /**
     * Optional.ofNullable(T t) 若t不为Null,创建Optional实例，否则创建空实例
     */
    @Test
    public void test3(){
        Optional<Employee> op= Optional.ofNullable(null);
        System.out.println(op.get());

    }

    /**
     * isPresent()判断是否包含值
     *
     */
    @Test
    public void test4(){
        Optional<Employee> op = Optional.ofNullable(null);
        if(op.isPresent()){
            System.out.println(op.get());
        }

        /**
         * orElse(T t) :如果调用对象包含值，则返回该值，否则返回t
         */
        Employee emp = op.orElse(new Employee("张三"));
        System.out.println(emp);
        /**
         * orElseGet(Supplier s): 如果调用对象有值则返回该值，法则否则返回s获取的值
         */
        Employee emp1 = op.orElseGet(() -> new Employee());

        System.out.println(emp1);

    }

    /**
     * map(Function f) 如果有值则对其进行处理，并返回处理后的Optional,否则返回Optional.empty()
     */
    @Test
    public void test5(){
        Optional<Employee> op = Optional.of(new Employee(101, "张三", 18, 9999.99));
        Optional<String> op2= op.map(Employee::getName);
        System.out.println(op2.get());
        /**
         * flatMap(Function mapper):与map类似，要求返回值必须是Optional
         */
        Optional<String> op3 = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(op3.get());


    }


}
