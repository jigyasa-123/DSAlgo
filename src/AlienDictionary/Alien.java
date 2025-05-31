package AlienDictionary;

import java.util.*;

// Main class should be named 'Solution' and should not be public.
class AlienDictionary{
    List<String> words;

    public AlienDictionary(List<String> words){
        this.words = words;

    }

    public List<Character> getSequence(){
        List<Character> ans = new ArrayList<Character>();

        //create graph and inorder degree array
        Map<Character,List<Character>> adjList  = new HashMap<>();
        Map<Character,Integer> indegree = new HashMap<>();

        for(String w : words){
            for(Character c: w.toCharArray()){
                adjList.put(c, new ArrayList<>());
                indegree.put(c, 0);


            }
        }

        for(int i=0;i<words.size()-1;++i){
            String curr = words.get(i);
            String next = words.get(i+1);
            if(curr.length() > next.length() && curr.startsWith(next)){
                return ans;
            }


            //find the first non match word
            for(int j=0;j<Math.min(curr.length(), next.length());++j){
                if(curr.charAt(j) != next.charAt(j)){
                    adjList.get(curr.charAt(j)).add(next.charAt(j));
                    int v = indegree.getOrDefault(next.charAt(j), 0);
                    v+=1;
                    indegree.put(next.charAt(j), v);
                    break;

                }

            }
        }


        //Breadth first search
        Queue<Character> queue = new LinkedList<>();
        for(Map.Entry<Character,Integer> c : indegree.entrySet()){
            if(c.getValue() == 0){
                queue.add(c.getKey());
            }
        }

        while(!queue.isEmpty()){
            Character curr = queue.poll();
            ans.add(curr);
            for(Character c: adjList.get(curr)){
                indegree.put(c, indegree.get(c)-1);
                if(indegree.get(c).equals(0)){
                    queue.add(c);
                }
            }


        }


        return ans;
    }

}
class Alien {
    public static void main(String[] args) {
        //List<String> words = Arrays.asList("wrt","wrf","er","ett","rftt");
        List<String> words = Arrays.asList("caa", "aaa", "aab");


        AlienDictionary alienDictionary = new AlienDictionary(words);
        System.out.println(alienDictionary.getSequence());
    }
}
