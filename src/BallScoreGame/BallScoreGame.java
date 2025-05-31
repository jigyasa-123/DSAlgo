package BallScoreGame;

import java.util.*;


// Main class should be named 'Solution' and should not be public.
class DigitValue{
    int value;
    int sumOfDigit;
    public DigitValue(int value){
        this.value = value;
        this.sumOfDigit = sumOfDigit(value);

    }

    private int sumOfDigit(int n){
        int sum = 0;
        while(n!=0){
            int rem = n%10;
            sum+=rem;
            n = n/10;
        }
        return sum;


    }
}
class BallScore{
    int k;
    List<Integer> balls;
    String tossOutcome;

    public BallScore(int k,List<Integer> balls,String tossOutcome){
        this.k = k;
        this.balls = balls;
        this.tossOutcome = tossOutcome;
    }

    public List<List<Integer>> playGame(){
        List<List<Integer>> answer = new ArrayList<>();

        answer.add(new ArrayList<Integer>());
        answer.add(new ArrayList<Integer>());

        //first create loop for turn
        int turn =  tossOutcome == "HEAD" ?0:1;
        int countOfBalls = 0;
        int players = 2;


        Comparator<DigitValue> digitValueComparator = new Comparator<DigitValue>() {
            @Override
            public int compare(DigitValue a,DigitValue b){
                int sumCompare = Integer.compare(b.sumOfDigit, a.sumOfDigit);
                if(sumCompare == 0){
                    return Integer.compare(b.value, a.value);
                }
                return sumCompare;
            }
        };


        //Add values in priority_queue
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<DigitValue> sumDigitsMax = new PriorityQueue<>(digitValueComparator);
        //create map and its count
        Map<Integer,Integer> countBalls = new HashMap<>();
        Map<Integer,PriorityQueue> playerMap = new HashMap<>();
        for(int b : balls){
            int c = countBalls.getOrDefault(b, 0) + 1;
            countBalls.put(b, c);
            maxHeap.add(b);
            sumDigitsMax.add(new DigitValue(b));
            countOfBalls+=1;

        }
        playerMap.put(0, maxHeap);
        playerMap.put(1, sumDigitsMax);
        int currPlayer = 0;
        while(countOfBalls> 0){
            int taken =0;
            currPlayer = turn%players;
            turn+=1;
            while(countOfBalls > 0 && taken < k){
                PriorityQueue currPriorityQueue = playerMap.get(currPlayer);
                Object candidate = currPriorityQueue.peek();
                int ballVal = 0;
                if (currPlayer == 0) {
                    ballVal = (Integer) candidate;
                } else {
                    ballVal = ((DigitValue) candidate).value;
                }

                if(countBalls.getOrDefault(ballVal, 0)>0){
                    answer.get(currPlayer).add(ballVal);
                    int c = countBalls.getOrDefault(ballVal, 0);
                    c-=1;
                    countBalls.put(ballVal, c);
                    ++taken;
                    --countOfBalls;

                }

                currPriorityQueue.poll();


            }
        }
        return answer;

    }

}
class BallScoreGame {
    public static void main(String[] args) {
        BallScore BallScore = new BallScore(2, Arrays.asList(100,99,98), "TAIL");
        System.out.println(BallScore.playGame());

    }
}

/*
* TC: O(NlogN)
* SC: O(N)
* */
