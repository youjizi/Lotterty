package com.libai.lottery.test;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @description:
 * @author： 有骥子
 * @date: 2023/7/26
 */


public class test {


    @Test
    public void test() {
        // 获取昨天晚上 18:50 的时间
//        LocalDateTime targetTime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(18, 50));

        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Long oneTime = calendar.getTimeInMillis();
//        System.out.println(calendar.getTimeInMillis() +":    "+ calendar.getTime());

        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Long TwoTime = calendar.getTimeInMillis();
//        System.out.println(calendar.getTimeInMillis() +":    "+ calendar.getTime());
        System.out.println(oneTime-TwoTime);
    }

    @Test
    public void test2() {
        String old = "2023-07-25 18:03:22";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(old, formatter);

        // 当前时间
        String now = "2023-07-26 14:57:23";
        LocalDateTime nowTime = LocalDateTime.parse(now, formatter);
        System.out.println(nowTime+":  " +parse);

        // sum
        Duration between = Duration.between(parse, nowTime);
        System.out.println(between.toHours());
        System.out.println(between.toMillis());
        System.out.println(between.toMillis() > 1800000);

        Duration duration = Duration.ofMinutes(30);
        System.out.println(duration.getSeconds());


    }
}
