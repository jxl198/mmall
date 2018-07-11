package com.codewars;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author jiangxl
 * @description
 * @date 2018-07-05 14:35
 **/
public class SpinWords {
    public static String spinWords(String sentence) {
        String[] arr = sentence.split(" ");
       return  Arrays.stream(arr).map(str->{
            if(str.length()>=5){
                return new StringBuilder(str).reverse().toString();
            }
            else {
                return str;
            }
        }).collect(Collectors.joining(" "));



    }

    public static void main(String[] args) {
        System.out.println(spinWords("Hey fellow warriors"));
    }
}
