package BuildTerrain;

import java.util.Arrays;
import java.util.TreeMap;

public class BuildTerrain {

    public static void main(String[] args){
        Terrain terrain = new Terrain(3,4);
        System.out.println(Arrays.deepToString(terrain.addLand(0, 3)));
        System.out.println(Arrays.deepToString(terrain.addLand(1, 3)));
        System.out.println(Arrays.deepToString(terrain.addLand(0, 0)));
        System.out.println(terrain.findNoOfIsland());
        System.out.println(terrain.noOfIslandUsingDisjointSet());
    }

}


//TC of addLand = O(1)
//TC of findNoOfIsland = O(row*col)
//TC of noOfIslandUsingDisjointSet = O(1)