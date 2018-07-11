package com.codewars;

/**
 * @author jiangxl
 * @description
 * @date 2018-07-04 17:14
 **/
public class Max {

    public static int sequence(int[] arr) {
        int max = 0;
        int current = 0;
        for (int a : arr) {
            current += a;
            if (current < 0) {
                current = 0;
            }
            if (max < current) {
                max = current;
            }

        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1,-1,5,3,-7,1};
        System.out.println( sequence(arr));
    }
}
