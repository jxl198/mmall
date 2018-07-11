package com.codewars;

import java.util.Arrays;

/**
 * @author jiangxl
 * @description
 * @date 2018-07-05 11:37
 **/
public class FindOutlier {

    static int find(int[] integers){
        int[] evenArr = Arrays.stream(integers).filter(n->n%2==0).toArray();
        if(evenArr.length==1){
            return evenArr[0];
        }else  {
            int[] oddArr = Arrays.stream(integers).filter(n->n%2!=0).toArray();
            if(oddArr.length==1){
                return oddArr[0];
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] a ={1,3,5,8};
        System.out.println(find(a));
    }
}
