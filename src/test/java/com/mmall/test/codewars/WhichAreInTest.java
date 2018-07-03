package com.mmall.test.codewars;

import com.codewars.WhichAreIn;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author jiangxl
 * @description
 * @date 2018-07-02 15:37
 **/
public class WhichAreInTest {
    @Test
    public void test1() {
        String a[] = new String[]{ "arp", "live", "strong" };
        String b[] = new String[] { "lively", "alive", "harp", "sharp", "armstrong" };
        String r[] = new String[] { "arp", "live", "strong" };
        assertArrayEquals(r, WhichAreIn.inArray(a, b));
    }
}
