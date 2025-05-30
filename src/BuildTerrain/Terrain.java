package BuildTerrain;

import java.util.List;

public class Terrain {
   int [][] matrix;
   int row;
   int col;
   int islandCount;
   UnionFind uf;
    Terrain(int row,int col){
        this.row = row;
        this.col = col;
        matrix = new int[row][col];
        islandCount = 0;
       uf = new UnionFind(row*col);
    }


    public boolean isLand(int x,int y){
        return x >= 0 && y >= 0 && x < row && y < col && matrix[x][y] != 0;

    }

    int[][]  addLand(int x,int y){
        if(validateCoords(x,y)){
            matrix[x][y] = 1;
            ++islandCount;
            addLandToDisjoinSet(x,y);
        }
        return matrix;
    }

    boolean validateCoords(int x,int y){
        return x >= 0 && y >= 0 && x < row && y < col;
    }

    public int findNoOfIsland(){
        int count =0;
        boolean[][] visited = new boolean[row][col];
        for(int i=0;i<row;++i){
            for(int j=0;j<col;++j){
                if(matrix[i][j] == 1 && !visited[i][j]){
                    ++count;
                    dfs(matrix,i,j,visited);
                }
            }
        }
        return count;
    }

    public int noOfIslandUsingDisjointSet(){
       return islandCount;
    }

    void dfs(int[][] matrix,int x,int y,boolean[][] visited){
        if(x<0 || y<0 || x>=row || y>=col || visited[x][y]  || matrix[x][y] ==0 ){
            return;
        }

        visited[x][y] = true;
        dfs(matrix,x+1,y,visited);
        dfs(matrix,x-1,y,visited);
        dfs(matrix,x,y+1,visited);
        dfs(matrix,x,y-1,visited);

    }

    void addLandToDisjoinSet(int x,int y){
        int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};
        int pos = index(x,y);

        for(int i=0;i<dir.length;++i){
            int newX = x+dir[i][0];
            int newY = y+dir[i][1];
            if(isLand(newX,newY)){
                int neighbor = index(newX,newY);
                if(uf.union(pos, neighbor)){
                    --islandCount;
                }
            }
        }

    }

    int index(int x,int y){
        return x*col + y;
    }


}


