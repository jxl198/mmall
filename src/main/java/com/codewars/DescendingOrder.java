package com.codewars;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiangxl
 * @description
 * @date 2018-07-04 16:43
 **/
public class DescendingOrder {
    public static int sortDesc(final int num) {
        String numberStr = num + "";
        char[] chars = numberStr.toCharArray();
        List<String> list = new ArrayList<String>();
        for(char c :chars){
            list.add(c+"");
        }
        String str = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.joining());
        return new Integer(str);

    }

    public static void main(String[] args) {
        System.out.println(sortDesc(4235452));
    }
}
