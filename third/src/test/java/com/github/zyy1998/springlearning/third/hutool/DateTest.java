package com.github.zyy1998.springlearning.third.hutool;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTest {
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Test
    public void test1() throws ParseException {
        String d1 = "2022-09-08 10:41:01";
        String d2 = "2022-09-08 10:42:01";
        SimpleDateFormat sdf = new SimpleDateFormat(STANDARD_FORMAT, Locale.ENGLISH);
        Date date1 = sdf.parse(d1);
        Date date2 = sdf.parse(d2);

        long ms = DateUtil.between(date1, date2, DateUnit.MS);
        assertEquals(ms, 60000);
        ms = DateUtil.between(date2, date1, DateUnit.MS);
        assertEquals(ms, 60000);
        ms = DateUtil.betweenMs(date1, date2);
        assertEquals(ms, 60000);
    }
}
