package com.codewars;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangxl
 * @description
 * @date 2018-07-05 16:02
 **/
public class ConwayLife {

    public static int[][] getGeneration(int[][] cells, int generations) {
        // your code goes here

        if (cells != null && cells.length > 0) {
            for (int ge = 0; ge < generations; ge++) {

                int[][] result = arrayCopy(cells);
                for (int a = 0; a < cells.length; a++) {
                    if (cells[a] != null && cells[a].length > 0) {
                        for (int b = 0; b < cells[a].length; b++) {
                            getValue(cells, result, a, b);
                        }
                    }
                }
                cells = result;

            }
            return cells;
        }

        return null;
    }

    public static int[][] arrayCopy(int[][] src) {
        int[][] cells = null;
        int[][] result = null;
        if (src != null) {
            cells= new int[src.length+2][];
            result = new int[src.length+2][];
            for (int i = 0; i < cells.length; i++) {
                cells[i]=new int[cells[i].length];
                result[i] = new int[src[i].length];
                System.arraycopy(src[i], 0, result[i], 0, src[i].length);
            }
        }
        return result;
    }

    public static void getValue(int[][] cells, int[][] result, int a, int b) {
        int current = cells[a][b];
        int liveCount = 0;
        List<Integer> aIndex = new ArrayList<Integer>();
        List<Integer> bIndex = new ArrayList<Integer>();
        if (a - 1 >= 0) {
            aIndex.add(a - 1);
        }
        if (a + 1 < cells.length) {
            aIndex.add(a + 1);
        }
        aIndex.add(a);
        if (b - 1 >= 0) {
            bIndex.add(b - 1);
        }
        if (b + 1 < cells[a].length) {
            bIndex.add(b + 1);
        }
        bIndex.add(b);

        for (int i = 0; i < aIndex.size(); i++) {
            for (int j = 0; j < bIndex.size(); j++) {
                int indexA = aIndex.get(i);
                int indexB = bIndex.get(j);
                if (indexA != a || indexB != b) {
                    if (cells[indexA][indexB] == 1) {
                        liveCount++;
                    }
                }
            }
        }
        if (current == 1) {
            if (liveCount == 2 || liveCount == 3) {
                result[a][b] = 1;
            } else {
                result[a][b] = 0;
            }
        } else {
            if (liveCount == 3) {
                result[a][b] = 1;
            }
        }
    }

    public static void main(String[] args) {
        int[][] a = {{1, 0, 0},
                {0, 1, 1},
                {1, 1, 0}};

        int[][] b = getGeneration(a, 1);
        System.out.println("end");
    }
}

