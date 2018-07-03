package com.codewars;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangxl
 * @description
 * @date 2018-06-29 15:05
 **/
public class LongestConsec {
    public static String longestConsec(String[] strarr, int k) {
        // your code
        if (strarr == null || strarr.length == 0
                || k > strarr.length || k <= 0) {
            return "";
        }
        //append k string
        String result = "";
        StringBuffer sb = new StringBuffer("");

        for (int i = 0; i + k <=strarr.length; i++) {
            for (int j = 0; j < k; j++) {
                sb.append(strarr[i + j]);
            }
            if (sb.toString().length() > result.length()) {
                result = sb.toString();
            }
            sb.setLength(0);
        }

        return result;


    }

    public static void main(String[] args) {
        System.out.println("longestConsec Fixed Tests");
        String str = LongestConsec.longestConsec(new String[]{"zone", "abigail", "theta", "form", "libe", "zas", "theta", "abigail"}, 2);
        System.out.println(str);
    }
}
