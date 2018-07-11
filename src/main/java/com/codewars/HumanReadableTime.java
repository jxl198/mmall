package com.codewars;

/**
 * @author jiangxl
 * @description
 * @date 2018-07-05 12:00
 **/
public class HumanReadableTime {
    public static String makeReadable(int seconds) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        hour = seconds / (60 * 60);
        minute = (seconds-(hour * 60 * 60)) / 60;
        second = seconds - hour * 60 * 60 - minute * 60;
        return String.format("%02d:%02d:%02d",hour,minute,second);
    }

    public static void main(String[] args) {
        System.out.println(makeReadable(62));
    }
}
