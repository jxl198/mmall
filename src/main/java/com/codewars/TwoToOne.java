package com.codewars;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * @author jiangxl
 * @description
 * @date 2018-07-02 16:07
 **/
public class TwoToOne {
    public static String longest(String s1, String s2) {
        StringBuffer sb = new StringBuffer(s1);
        sb.append(s2);
        String result = sb.chars().filter(n -> n >= 'a' && n <= 'z').distinct()
                .sorted().collect(StringBuffer::new,
                        StringBuffer::appendCodePoint, StringBuffer::append).toString();


        return result;

    }

    public static void main(String[] args) {

//        System.out.println(longest("ccssaaaa", "bbbssaaa"));
        String s = 123+"";
        System.out.println(s);
    }
}
