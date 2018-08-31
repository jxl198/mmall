package com.codewars;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author jiangxl
 * @description 找到第一个比给定数字小的数
 * @date 2018-07-12 10:27
 **/
public class Kata {

    public static long nextSmaller(long n) {
        String str = String.valueOf(n);
            String[] strArr = str.split("");
            if (strArr.length == 1) {
                return -1;
            }
            String ascStr = Arrays.stream(strArr).sorted().collect(Collectors.joining());
            if (ascStr.equals(str)) {
            return -1;
        }
        int subNum = 2;
        long result = getSmaller(str, strArr, subNum);

        return result;
    }

    public static long getSmaller(String str, String[] strArr, int subNum) {
        if (subNum < strArr.length) {
            String[] dest = Arrays.copyOfRange(strArr, strArr.length - subNum, strArr.length);

            String destStr = sort(dest);
            String result = str.substring(0, strArr.length - subNum) + destStr;
            if (result.compareTo(str) < 0  ) {
                return Long.parseLong(result);
            } else {
                subNum++;
                return getSmaller(str, strArr, subNum);
            }

        } else {
            String[] copyArr = Arrays.copyOf(strArr, strArr.length);
            String destStr = sort(copyArr);
            if(destStr.startsWith("0")){
                return -1;
            }
            if (destStr.compareTo(str) >= 0) {
                return -1;
            } else {
                return Long.parseLong(destStr);
            }
        }


    }

    public static String sort(String[] dest) {
        String start = dest[0];
        Arrays.sort(dest);
        StringBuffer sb = new StringBuffer();
        int index =-1;
        for (int i = dest.length - 1; i >= 0; i--) {
            if (dest[i].compareTo(start) < 0) {
                if(index==-1){
                    index= i;
                    sb.insert(0, dest[i]);
                }
                else {
                    sb.append(dest[i]);
                }

            } else {

                sb.append(dest[i]);
            }
        }
        return sb.toString();

    }

    public static void main(String[] args) {

        System.out.println(nextSmaller(59884848459853L));
//        System.out.println(nextSmaller(907));
//
//        System.out.println(nextSmaller(531));
//
//        System.out.println(nextSmaller(1027));
//        System.out.println(nextSmaller(441));
//        System.out.println(nextSmaller(123456798));

    }
}
