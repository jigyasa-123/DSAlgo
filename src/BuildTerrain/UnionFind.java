package BuildTerrain;

public class UnionFind {

    private int[] parent;
    private int[] rank;

    UnionFind(int n){
        parent = new int[n];
        rank = new int[n];
        for(int i=0;i<n;++i){
            parent[i] = i;
            rank[i] = 0;
        }
    }

    int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    boolean union(int x,int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY){
            return false;
        }

        if(rank[rootX] > rank[rootY]){
            parent[y] = rootX;
        }else if(rank[rootX] < rank[rootY]){
            parent[x] = rootY;
        }else{
            parent[y] = rootX;
            rank[rootX]++;
        }
        return true;
    }

}
