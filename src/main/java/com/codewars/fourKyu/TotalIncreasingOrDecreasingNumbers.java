package com.codewars.fourKyu;

import java.math.BigInteger;

/**
 * @author jiangxl
 * @description
 * @date 2018-07-24 16:12
 **/
public class TotalIncreasingOrDecreasingNumbers {
    public static BigInteger totalIncDec(int x) {
        // Good Luck!
        // 1. increasing numbers: draw x from (0, ..., 9) with replacement
        // 2. we have two zeroes, one regular digit and one leading (invisible) zero
        //    decreasing numbers: draw x from (0, ..., 9, 0') with replacement
        // 3. 9*x 'constant' numbers (111, 222, ...) were counted twice
        // 4. x+1 zeroes can be drawn from the decreasing pot which also
        //    have to be subtracted as we have already have a zero:
        //        x * 0
        //        (x-1) * 0 + 0'
        //        ...
        //        0 + (x-1)*0'
        //        x * 0'
        BigInteger increasing = binomial(x + 9, x);
        BigInteger decreasing = binomial(x + 10, x);
        BigInteger duplicates = BigInteger.valueOf(10 * x + 1);
        return increasing.add(decreasing).subtract(duplicates);
    }

    private static BigInteger binomial(int n, int k) {
    /*
     n! / (k! * (n-k)!)
       = ( n * ... * (n-k + 1) ) / (1 * ... * k)
    */
        if (k == 0) {
            return BigInteger.ONE;
        }
        if (k == 1) {
            return BigInteger.valueOf(n);
        }
        return BigInteger.valueOf(n)
                .multiply(binomial(n - 1, k - 1))
                .divide(BigInteger.valueOf(k));
    }

    public static void main(String[] args) {
//        System.out.println(totalIncDec1(1));
//        System.out.println(totalIncDec1(2));
//        System.out.println(totalIncDec1(3));
//        System.out.println(totalIncDec1(4));


    }
}
