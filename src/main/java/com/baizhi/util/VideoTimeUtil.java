package com.baizhi.util;

import java.util.Date;

public class VideoTimeUtil {
    /**
     * 毫秒时间
     * Long类型时间转换成视频时长
     */
    public static String format(Long time) {
        if (time == null) {
            return null;
        } else {
            Date date = new Date(time);
            long hour = time / (60 * 60 * 1000);
            long minute = (time - hour * 60 * 60 * 1000) / (60 * 1000);
            long second = (time - hour * 60 * 60 * 1000 - minute * 60 * 1000) / 1000;
            return (hour == 0 ? "00" : (hour > 10 ? hour : ("0" + hour))) + ":" + (minute == 0 ? "00" : (minute > 10 ? minute : ("0" + minute))) + ":" + (second == 0 ? "00" : (second > 10 ? second : ("0" + second)));
        }
    }

    /**
     * 时间为秒
     * Long类型时间转换成视频时长
     */
    public static String formatTime(Long time) {
        if (time == null) {
            return null;
        } else {
            Date date = new Date(time);
            long hour = time / (60 * 60);
            long minute = (time - hour * 60 * 60) / 60;
            long second = time - hour * 60 * 60 - minute * 60;
            return (hour == 0 ? "00" : (hour > 10 ? hour : ("0" + hour))) + ":" + (minute == 0 ? "00" : (minute > 10 ? minute : ("0" + minute))) + ":" + (second == 0 ? "00" : (second > 10 ? second : ("0" + second)));

        }
    }


    /**
     * 字节转kb/mb/gb
     *
     * @param size
     * @return
     */
    public static String getPrintSize(long size) {
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        //因为还没有到达要使用另一个单位的时候
        //接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            //因为如果以MB为单位的话，要保留最后1位小数，
            //因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "MB";
        } else {
            //否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "GB";
        }
    }

}