package com.codewars;

import java.util.*;

/**
 * @author jiangxl
 * @description
 * @date 2018-07-02 15:30
 **/
public class WhichAreIn {
    public static String[] inArray(String[] array1, String[] array2) {
        Set<String> set1 = new LinkedHashSet<String>();
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2.length; j++) {
                if (array2[j].indexOf(array1[i]) != -1) {
                    set1.add(array1[i]);
                }
            }
        }

        return set1.toArray(new String[set1.size()]);
    }
}
