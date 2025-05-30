package RobotDistance;

import java.util.*;

class Query{
    public int left;
    public int top;
    public int bottom;
    public int right;

    public Query(int left,int top,int bottom,int right){
        this.left = left;
        this.top = top;
        this.bottom = bottom;
        this.right = right;
    }
}
class Robot{
    int[][] dpLeft;
    int[][] dpTop;
    int[][] dpRight;
    int[][] dpBottom;
    char[][] matrix;
    int row;
    int col;

    public Robot(char[][] matrix){
        this.matrix = matrix;
        this.row = matrix.length;
        this.col = matrix[0].length;
        dpLeft = new int[row][col];
        dpTop = new int[row][col];
        dpRight = new int[row][col];
        dpBottom = new int[row][col];
        computeTopLeft(matrix);
        computeBottomRight(matrix);
    }

    public List<List<Integer>> findRobot(Query query){
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0;i<row;++i){
            for(int j=0;j<col;++j){
                if(matrix[i][j] == 'O'){
                    int leftDistance = dpLeft[i][j];
                    int topDistance =  dpTop[i][j];
                    int rightDistance =  dpRight[i][j];
                    int bottomDistance =  dpBottom[i][j];

                    if(leftDistance == query.left && topDistance == query.top && bottomDistance == query.bottom && rightDistance == query.right){
                        List<Integer> robot = new ArrayList<>();
                        robot.add(i); robot.add(j);
                        result.add(robot);

                    }

                }
            }
        }
        return result;


    }


    private void computeTopLeft(char[][] matrix){
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


    private  void computeBottomRight(char[][] matrix){
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

    private  void print(int[][] matrix){
        for(int i=0;i<matrix.length;++i){
            for(int j=0;j<matrix[0].length;++j){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
class RobotDistanceUsingClass {

    public static void main(String[] args) {

        char[][] matrix = {{'O','E','E','E','X'},{'E','O','X','X','X'},{'E','E','E','E','E'},{'X','E','O','E','E'},{'X','E','X','E','X'}};
        Robot robot = new Robot(matrix);
        Query query = new Query(2,2,1,3);
        List<List<Integer>> ans = robot.findRobot(query);

        for(List<Integer> current : ans){
            System.out.println(current.get(0) + " "+current.get(1));
        }




    }

}

