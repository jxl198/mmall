package com.codewars;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * @author jiangxl
 * @description 字母排列组合 选取序列的中间一个，如果是奇数，第一个字母为n/2取整，后面为子串的中间数排列
 * 如果为偶数，第一个字母为n/2-1，后面为子串的降序排列
 * @date 2018-07-12 14:53
 **/
public class MiddlePermutation {
    public static String findMidPerm(String strng) {
        // your code here!
        String[] strArr = strng.split("");
        Arrays.sort(strArr);  //升序排列
        int firstLetterIndex = 0;

        if (strArr.length % 2 == 0) {

            firstLetterIndex = strArr.length / 2 - 1;
            final String firstLetter = strArr[firstLetterIndex];
            String subStr = Arrays.stream(strArr).filter((t) -> !t.equals(firstLetter))
                    .sorted(Comparator.reverseOrder()).collect(Collectors.joining());
            return firstLetter + subStr;
        } else {
            firstLetterIndex = strArr.length / 2;
            final String firstLetter = strArr[firstLetterIndex];
            String subStr = Arrays.stream(strArr).filter((t) -> !t.equals(firstLetter)).collect(Collectors.joining());
            return firstLetter + findMidPerm(subStr);
        }

    }

    public static void main(String[] args) {
        System.out.println(findMidPerm("abc"));
        System.out.println(findMidPerm("abcdx"));
        System.out.println(findMidPerm("abcdxg"));
        System.out.println(findMidPerm("xgabcdf"));
    }
}
