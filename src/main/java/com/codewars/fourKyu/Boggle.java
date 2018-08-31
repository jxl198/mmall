package com.codewars.fourKyu;
import java.util.ArrayList;
import java.util.List;
public class Boggle {

    private char[][] board;
    private String word;
    private int[][] marked;
    private int colLen;
    private int rowLen;

    public Boggle(final char[][] board, final String word) {
        // Your code here!
        this.board = board;
        this.word = word;
        this.marked = new int[board.length][];
        this.rowLen = board.length;
        this.colLen = board[0].length;
        for (int i = 0; i < marked.length; i++) {
            marked[i] = new int[board[i].length];
        }
    }

    public boolean check() {
        char c = word.charAt(0);
        List<Point> firstPoints = findFirstPoint(c);
        if (firstPoints.size() > 0) {
            for (int i = 0; i < firstPoints.size(); i++) {
                Point point = firstPoints.get(i);
                boolean result =   findWord(board, marked, point.getX(), point.getY(), new StringBuffer());
                if (result) {
                    return result;
                }
                continue;

            }
        } else {
            return false;
        }

        return false;
    }

    public List<Point> findFirstPoint(char c) {
        List<Point> points = new ArrayList<Point>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (c == board[i][j]) {
                    points.add(new Point(i, j));
                }
            }
        }
        return points;
    }


    public boolean  findWord(char[][] boggle, int[][] isMarked, int row, int col, StringBuffer str) {

        isMarked[row][col] = 1;
        str.append(boggle[row][col]);
//        System.out.println(str.toString());

        if(this.word.charAt(str.length()-1)!=boggle[row][col]){
            isMarked[row][col] = 0;
            str.delete(str.length() - 1, str.length());
            return false;
        }
        if(str.length()>=this.word.length()){
            if (str.toString().equals(this.word)) {
                System.out.println("-------"+str.toString());
                return true;
            }
            else {
                isMarked[row][col] = 0;
                str.delete(str.length() - 1, str.length());
                return false;
            }
        }
        for (int i = row - 1; i <= row + 1 && i < rowLen; i++) {
            for (int j = col - 1; j <= col + 1 && j < colLen; j++) {
                if (i >= 0 && j >= 0 && isMarked[i][j] == 0) {
                    boolean result = findWord(boggle, isMarked, i, j, str);
                    if(result ){
                        return result;
                    }
                }
            }
        }
        isMarked[row][col] = 0;
        str.delete(str.length() - 1, str.length());
        return false;

    }

    public static void main(String[] args) {

        char[][] board = {
                {'E', 'A', 'R', 'A'},
                {'N', 'L', 'E', 'C'},
                {'I', 'A', 'I', 'S'},
                {'B', 'Y', 'O', 'R'}
        };

        Boggle boggle = new Boggle(board, "ECI");
        System.out.println(boggle.check());


    }

    private class Point {
        private int x;
        private int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;

        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}