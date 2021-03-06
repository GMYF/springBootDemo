package com.light.springboot.util.date;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/9/11 下午9:58
 */
public class DateUtil {
    /**
     * 获取当前时间
     * @return
     */
    public static String getTime(){
        SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        return smt.format(c.getTime());
    }

    public static Long getTimeLocal(){
        Calendar c = Calendar.getInstance();
        return c.getTime().getTime();
    }
    /**
     * 获取指定时间
     * @param date
     * @param pattern
     * @return
     */
    public static String getTime(java.util.Date date,String pattern){
        SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd");
        return smt.format(date);
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,3, 10,TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        threadPoolExecutor.submit(new  CallableDemo());
    }

    public static class CallableDemo implements Callable{

        @Override
        public Object call() throws Exception {
            System.out.println("callable 执行了");
            return null;
        }
    }
}
