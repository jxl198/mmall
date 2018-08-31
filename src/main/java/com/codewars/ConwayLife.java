package com.codewars;

import java.util.ArrayList;
import java.util.Arrays;
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
                int[][] nextArr = getNextArr(cells);

                int[][] result = arrayCopy(nextArr);
                for (int a = 0; a < nextArr.length; a++) {
                    if (nextArr[a] != null && nextArr[a].length > 0) {
                        for (int b = 0; b < nextArr[a].length; b++) {
                            getValue(nextArr, result, a, b);
                        }
                    }
                }
                cells = deal(result);

            }
            return cells;
        }

        return null;
    }

    public static int[][] deal(int[][] result) {
        //计算有效长和宽
        if (result != null && result.length > 0) {

            boolean allZero1 = true;  //第一行全部为0
            boolean allZero2 = true;//最后一行全部为0
            boolean allZero3 = true;   //第一列全部为0
            boolean allZero4 = true;  //最后一列全部为0

            for (int j = 0; j < result[0].length; j++) {
                if (result[0][j] != 0) {
                    allZero1 = false;
                    break;
                }
            }
            if (result.length > 1) {
                for (int j = 0; j < result[result.length - 1].length; j++) {
                    if (result[result.length - 1][j] != 0) {
                        allZero2 = false;
                        break;
                    }
                }
            }
            for (int i = 0; i < result.length; i++) {
                if (result[i][0] != 0) {
                    allZero3 = false;
                    break;
                }
            }
            for (int i = 0; i < result.length; i++) {
                if (result[i][result[i].length - 1] != 0) {
                    allZero4 = false;
                    break;
                }
            }

            if(!allZero1&&!allZero2&&!allZero3&&!allZero4){
                return result;
            }
            int m = result.length;
            int n = result[0].length;
            if (allZero1) {
                m = m - 1;
            }
            if (allZero2) {
                m = m - 1;
            }
            if (allZero3) {
                n = n - 1;
            }
            if (allZero4) {
                n = n - 1;
            }
            int[][] arr = new int[m][];
            int startLine = 0;
            int startCol = 0;
            if (allZero1) {
                startLine = 1;
            }

            if (allZero3) {
                startCol = 1;
            }


            for (int a = 0; a < m; a++) {
                arr[a] = new int[n];
                System.arraycopy(result[startLine++], startCol, arr[a],0, n);
            }

            return deal(arr);
        }
        return null;


    }

    public static int[][] getNextArr(int[][] cells) {
        if (cells != null && cells.length > 0) {
            int[][] nextArr = new int[cells.length + 2][];
            for (int i = 0; i < cells.length; i++) {
                if (i == 0) {
                    nextArr[0] = new int[cells[i].length + 2];
                }
                if (i == cells.length - 1) {
                    nextArr[cells.length + 1] = new int[cells[i].length + 2];
                }
                nextArr[i + 1] = new int[cells[i].length + 2];
                System.arraycopy(cells[i], 0, nextArr[i + 1], 1, cells[i].length);
            }
            return nextArr;
        }
        return null;

    }

    public static int[][] arrayCopy(int[][] src) {

        int[][] result = null;
        if (src != null) {
            result = new int[src.length][];
            for (int i = 0; i < src.length; i++) {
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

        int[][] b = getGeneration(a, 2);
        printAarry(b);
        System.out.println("end");
    }

    public static void printAarry(int[][] array) {
        for (int[] arr : array) {
        System.out.println(Arrays.toString(arr));
    }
}
}

