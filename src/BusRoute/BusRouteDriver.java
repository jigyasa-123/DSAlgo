package BusRoute;

import java.io.*;
import java.util.*;


class BusRoute{

    List<List<Integer>> route;
    List<List<Integer>> adjList;

    public BusRoute(List<List<Integer>> route){
        this.route = route;
        adjList = new ArrayList<>();
    }

    int noOfBusToDestination(int source,int destination){
        if(source == destination) return 0;
        createGraph();
        Queue<Integer> currentRoute = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        for(int i=0;i<route.size();++i){
            for(int stop : route.get(i)){
                if(stop == source){
                    currentRoute.add(i);
                    visited.add(i);

                }
            }
        }

        int busCount = 0;

        while(!currentRoute.isEmpty()){
            int size = currentRoute.size();
            while(size-- > 0){
                int routeNo = currentRoute.poll();
                List<Integer> stops = route.get(routeNo);
                for(int s : stops){
                    if(s == destination){
                        return busCount;
                    }
                }

                for(int connectedBus: adjList.get(routeNo)){
                    if(!visited.contains(connectedBus)){
                        currentRoute.add(connectedBus);
                        visited.add(connectedBus);
                    }

                }



            }
            ++busCount;

        }


        return -1;
    }

    private void createGraph(){
        for(int i=0;i<route.size();++i){
            Collections.sort(route.get(i));
            adjList.add(new ArrayList<>());
        }

        for(int i=0;i<route.size();++i){
            for(int j = i+1;j<route.size();++j){
                if(haveCommonStop(route.get(i),route.get(j))){
                    adjList.get(i).add(j);
                    adjList.get(j).add(i);
                }
            }
        }
    }

    private boolean haveCommonStop(List<Integer> route1, List<Integer> route2){
        int i=0,j=0;
        while(i < route1.size() && j < route2.size()){
            if(route1.get(i) == route2.get(j)){
                return true;

            }
            if(route1.get(i) > route2.get(j)){
                ++j;
            }else{
                ++i;
            }

        }
        return false;

    }


}



// Main class should be named 'Solution' and should not be public.
class BusRouteDriver {
    public static void main(String[] args) {
        // List<Integer> r1 = new ArrayList<>(Arrays.asList(1,2,7));
        // List<Integer> r2 = new ArrayList<>(Arrays.asList(3,6,7));

        // List<Integer> r1 = new ArrayList<>(Arrays.asList(7,12));
        // List<Integer> r2 = new ArrayList<>(Arrays.asList(4,5,15));
        // List<Integer> r3 = new ArrayList<>(Arrays.asList(6));
        // List<Integer> r4 = new ArrayList<>(Arrays.asList(15,19));
        // List<Integer> r5 = new ArrayList<>(Arrays.asList(9,12,13));

        List<Integer> r1 = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        List<Integer> r2 = new ArrayList<>(Arrays.asList(2,7));

        List<List<Integer>> route = new ArrayList<>(Arrays.asList(r1,r2));
        BusRoute busRoute = new BusRoute(route);
        System.out.println(busRoute.noOfBusToDestination(1,6));

    }
}

/*
* TC:  O(M2∗K+M∗k∗logK)
* SC: O(M*2 + logK)
* */