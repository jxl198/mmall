package com.codewars;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author jiangxl
 * @description
 * @date 2018-07-03 10:38
 **/
public class DRoot {
    public static int digital_root(int n) {
        int result = 0;
        if (n >= 10) {
            String numberStr = n + "";
            char[] chars = numberStr.toCharArray();
            Stream<Character> myStreamOfCharacters = IntStream
                    .range(0, chars.length)
                    .mapToObj(i -> chars[i]);
            Stream<Integer> intStream = myStreamOfCharacters.map(c -> new Integer(c.toString()));
            result = intStream.reduce(0, (x, y) -> x + y);
            return digital_root(result);
        } else {
            result = n;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(digital_root(167));

    }
}
