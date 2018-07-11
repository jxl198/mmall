package com.codewars;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author jiangxl
 * @description
 * @date 2018-07-05 14:55
 **/
public class WeightSort {

    public static String orderWeight(String strng) {
        // your code
        String[] arr = strng.split(" ");
        return Arrays.stream(arr).sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // compute weight
                int w1 = computeWeight(o1);
                int w2 = computeWeight(o2);
                if (w1 < w2) {
                    return -1;
                } else if (w1 == w2) {
                    return o1.compareTo(o2);
                } else {
                    return 1;
                }

            }
        }).collect(Collectors.joining(" "));
    }

    public static int computeWeight(String str) {
        char[] chars = str.toCharArray();
        return str.chars().map(c->Character.getNumericValue(c)).sum();

    }

    public static void main(String[] args) {
//        System.out.println(orderWeight("2000 10003 1234000 44444444 9999 11 11 22 123"));
        System.out.println(orderWeight("10003 22"));

        System.out.println("10003".compareTo("22"));

        System.out.println(new Integer(1).compareTo(new Integer(2)));

    }
}
