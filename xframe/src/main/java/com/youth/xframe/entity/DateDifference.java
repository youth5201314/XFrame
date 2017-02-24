package com.youth.xframe.entity;


import com.youth.xframe.common.NotObfuscateInterface;

/**
 * 时间差计算结果封装类
 */
public class DateDifference implements NotObfuscateInterface{
    private long millisecond;
    private long second;
    private long minute;
    private long hour;
    private long day;

    public long getMillisecond() {
        return millisecond;
    }

    public void setMillisecond(long millisecond) {
        this.millisecond = millisecond;
    }

    public long getSecond() {
        return second;
    }

    public void setSecond(long second) {
        this.second = second;
    }

    public long getMinute() {
        return minute;
    }

    public void setMinute(long minute) {
        this.minute = minute;
    }

    public long getHour() {
        return hour;
    }

    public void setHour(long hour) {
        this.hour = hour;
    }

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

}
