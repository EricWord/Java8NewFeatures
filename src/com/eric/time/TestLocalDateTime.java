package com.eric.time;

import org.junit.Test;

import java.time.*;

/**
 * @ClassName: TestLocalDateTime
 * @Description:
 * @Author: Eric
 * @Date: 2019/3/5 0005
 * @Email: xiao_cui_vip@163.com
 */
public class TestLocalDateTime {
    @Test
    public void test1() {
        LocalDateTime ldt1 = LocalDateTime.now();
        System.out.println(ldt1);
        LocalDateTime ldt2 = LocalDateTime.of(2019, 10, 02, 15, 23, 22);
        System.out.println(ldt2);
        LocalDateTime ldt3 = ldt2.plusYears(20);
        System.out.println(ldt3);
        LocalDateTime ldt4 = ldt2.minusMonths(2);
        System.out.println(ldt4);

    }

    /**
     * Instant时间戳
     */
    @Test
    public void test2() {
        Instant now = Instant.now();
        System.out.println(now);
        OffsetDateTime odt = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);
        System.out.println(now.getNano());
        Instant s = Instant.ofEpochSecond(5);
        System.out.println(s);
    }

    @Test
    public void test3(){
        LocalDate ld1 = LocalDate.now();
        LocalDate ld2 = LocalDate.of(2009, 10, 01);
        Period pe = Period.between(ld2, ld1);
        System.out.println(pe.getYears());
        System.out.println(pe.getMonths());
        System.out.println(pe.getDays());


    }

}
