package RobotDistance;

import java.io.*;

class RobotDistance {
    static int[][] dpLeft;
    static int[][] dpTop;
    static int[][] dpRight;
    static int[][] dpBottom;

    public static void main(String[] args) {
        char[][] matrix = {{'O','E','E','E','X'},{'E','O','X','X','X'},{'E','E','E','E','E'},{'X','E','O','E','E'},{'X','E','X','E','X'}};
        int[] query = {2,2,1,3};
        int row = matrix.length , col = matrix[0].length;
        //pre compute distance
        computeTopLeft(matrix);
        computeBottomRight(matrix);

        //find robot
        for(int i=0;i<row;++i){
            for(int j=0;j<col;++j){
                if(matrix[i][j] == 'O'){
                    int leftDistance = dpLeft[i][j];
                    int topDistance =  dpTop[i][j];
                    int rightDistance =  dpRight[i][j];
                    int bottomDistance =  dpBottom[i][j];

                    if(leftDistance == query[0] && topDistance == query[1] && bottomDistance == query[2] && rightDistance == query[3]){
                        System.out.println(i+" "+j);
                    }

                }
            }
        }

    }

    private static void computeTopLeft(char[][] matrix){
        int row = matrix.length , col = matrix[0].length;
        dpLeft = new int[row][col];
        dpTop = new int[row][col];
        for(int i=0;i<row;++i){
            for(int j=0;j<col;++j){
                if(matrix[i][j] == 'X' ){
                    continue;
                }

                //calculate for left

                if(j == 0){
                    dpLeft[i][j] = 1;
                }else{
                    dpLeft[i][j] = dpLeft[i][j-1]+1;
                }

                //calculate for top

                if(i == 0){
                    dpTop[i][j] = 1;
                }else{
                    dpTop[i][j] = dpTop[i-1][j] + 1;
                }
            }
        }

    }
    private static void computeBottomRight(char[][] matrix){
        int row = matrix.length , col = matrix[0].length;
        dpRight = new int[row][col];
        dpBottom = new int[row][col];
        for(int i=row-1;i>=0;--i){
            for(int j=col-1;j>=0;--j){
                if(matrix[i][j] == 'X' ){
                    continue;
                }

                //calculate for right

                if(j == col-1){
                    dpRight[i][j] = 1;
                }else{
                    dpRight[i][j] = dpRight[i][j+1]+1;
                }

                //calculate for bottom

                if(i == row-1){
                    dpBottom[i][j] = 1;
                }else{
                    dpBottom[i][j] = dpBottom[i+1][j] + 1;
                }
            }
        }
    }

    private static void print(int[][] matrix){
        for(int i=0;i<matrix.length;++i){
            for(int j=0;j<matrix[0].length;++j){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

/*
* TC: O(n*m)
* SC: O(n*m)
* */
