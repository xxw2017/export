package cn.gzcb.export.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author xiongxianwei
 * 2018/1/24
 */
public class TimeUtils {
    /**
     * 得到当前日期
     * @return
     */
    public static Date getCurDate(){
        Date date=new Date();
        return date;
    }
    /**
     * 得到当前日期(yy-mm-dd格式)
     * @return
     */
    public static String getCurDateFormat(){
        return getDateFormat(new Date());
    }

    /**
     * 得到yyyyMMdd格式的日期
     * @return
     */
    public static String getDateFormat(Date curDate){
        return getDateFormat(curDate,"yyyy-MM-dd");
    }

    /**
     * 得到pattern格式的日期
     * @return String
     */
    public static String getDateFormat(Date curDate,String pattern){
        DateFormat sdf =new SimpleDateFormat(pattern);
        String date = sdf.format(curDate);
        return date;
    }
    /**
     * 得到pattern格式的日期
     * @return Date
     */
    public static Date getDateFormat(String curDate,String pattern) throws ParseException {
        DateFormat sdf =new SimpleDateFormat(pattern);
        Date date = sdf.parse(curDate);
        System.err.println(getDateFormat(date));
        return date;
    }

    /**
     * 得到当前日期的yy-mm-dd hh:mm:ss格式
     * @return
     */
    public static String getCurDateFormatHour(){
        return getCurDateFormatHour(new Date());
    }

    /**
     * 得到yy-mm-dd hh:mm:ss格式的日期
     * @return
     */
    public static String getCurDateFormatHour(Date curDate){
        DateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(curDate);
        return date;
    }


    /**
     * 得到新日期(日期格式)
     * @param curDate 当前日期
     * @param day 加上的天数
     * @return
     */
    public static Date addDate_Date(Date curDate, long day) {
        //转化为毫秒
        long time = curDate.getTime();
        day = day*24*60*60*1000;
        //相加得到新的毫秒数
        time+=day;
        return new Date(time);
    }

    /**
     * 得到新日期(字符串格式)
     * @param curDate 当前日期
     * @param day 加上的天数
     * @return
     */
    public static String addDate_String(Date curDate, long day) {
        Date date=addDate_Date(curDate,day);
        String afterDate=getDateFormat(curDate);
        return afterDate;
    }

    /**
     * 获得当前时间下一天的yymmdd格式String类型的日期
     * @param curDate
     * @return
     */
    public static String nextDay(String curDate)  {
        try{
            DateFormat fmt =new SimpleDateFormat("yyyyMMdd");
            Date date = fmt.parse(curDate);
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            cd.set(Calendar.DATE, cd.get(Calendar.DATE) + 1);
            date = cd.getTime();
            return fmt.format(date);
        }catch(ParseException ex){
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 获得当前时间上个月的yymmdd格式String类型的日期
     * @param curDate
     * @return
     */
    public static String lastMonthDay(String curDate) {
        try{
            DateFormat fmt =new SimpleDateFormat("yyyyMMdd");
            Date date = fmt.parse(curDate);
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            cd.set(Calendar.MONTH, cd.get(Calendar.MONTH) - 1);
            date = cd.getTime();
            return fmt.format(date);
        }catch(ParseException ex){
            ex.printStackTrace();
            return "";
        }
    }
    /**
     * 获得当前时间下个月的yymmdd格式String类型的日期
     * @param curDate
     * @return
     */
    public static String nextMonthDay(String curDate)  {
        try{
            DateFormat fmt =new SimpleDateFormat("yyyyMMdd");
            Date date = fmt.parse(curDate);
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            cd.set(Calendar.MONTH, cd.get(Calendar.MONTH) + 1);
            date = cd.getTime();
            return fmt.format(date);
        }catch(ParseException ex){
            ex.printStackTrace();
            return "";
        }
    }
    /**
     * 获得当前时间上个月的yymmdd格式String类型的日期并加一天
     * @param curDate
     * @return
     */
    public static String lastMonthDayAddOne(String curDate) {
        try{
            DateFormat fmt =new SimpleDateFormat("yyyyMMdd");
            Date date = fmt.parse(curDate);
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            cd.set(Calendar.MONTH, cd.get(Calendar.MONTH) - 1);
            cd.set(Calendar.DATE, 1);
            date = cd.getTime();
            return fmt.format(date);
        }catch(ParseException ex){
            ex.printStackTrace();
            return "";
        }

    }
}
