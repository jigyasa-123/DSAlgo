package SquareOfSortedArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Main class should be named 'Solution' and should not be public.
class SquareOfSortedArray {
    public static void main(String[] args) {
        System.out.println(sortSquares(Arrays.asList(-4,-1,0,3,10)));
        System.out.println(sortSquares(Arrays.asList(-7,-3,2,3,11)));
    }

    public static List<Integer> sortSquares(List<Integer> nums){
        List<Integer> ans = new ArrayList<>(nums.size());
        int l = 0,r=nums.size()-1;
        while(l<=r){
            if(nums.get(l)*nums.get(l) > nums.get(r) * nums.get(r)){
                ans.add(nums.get(l)*nums.get(l));
                ++l;

            }else{
                ans.add(nums.get(r)*nums.get(r));
                --r;
            }
        }
        return ans;

    }
}
