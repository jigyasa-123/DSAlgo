package NextSmallestPalindrome;

public class NextSmallestPalindrome {

    public static void main(String[] args){

        System.out.println(nextSmallerPalindrome(9999));
        System.out.println(nextSmallerPalindrome(12321));
        System.out.println(nextSmallerPalindrome(12945));
        System.out.println(nextSmallerPalindrome(1999993));
        System.out.println(nextSmallerPalindrome(123456789));
        System.out.println(nextSmallerPalindrome(9));
        System.out.println(nextSmallerPalindrome(129999));
        System.out.println(nextSmallerPalindrome(10));
        System.out.println(nextSmallerPalindrome(8));
        System.out.println(nextSmallerPalindrome(1001));
        System.out.println(nextSmallerPalindrome(99999));

    }

    public static long nextSmallerPalindrome(long number){
        //To handle single digit scenario or 9/99/999 case
        number += number+1;
        if(isPalindrome(number)) return number;
        String num = String.valueOf(number);
        int len = num.length();

        int mid =  (len + 1)/2;

        //check left and its reverse
        String left  = num.substring(0,mid);
        boolean isOdd = (len % 2 != 0);

        String nextPalindrome =makeNextPalindrome(left,isOdd);
        if(Long.parseLong(nextPalindrome) > Long.parseLong(num)){
            return  Long.parseLong(nextPalindrome);
        }

        //if left and its reverse is less than n, increment left

        long newLeft = Long.parseLong(left) + 1L;
        left = String.valueOf(newLeft);
         nextPalindrome = makeNextPalindrome(left,isOdd);
        return Long.parseLong(nextPalindrome);

    }

    private static String makeNextPalindrome(String left, boolean isOdd){
        String reverseNumber = left;
        if(isOdd && !reverseNumber.isEmpty()){
            reverseNumber =  reverseNumber.substring(0,reverseNumber.length()-1);
        }
        StringBuilder stringBuilder = new StringBuilder(reverseNumber);
        stringBuilder.reverse();
        return left + stringBuilder.toString();
    }



    private static boolean isPalindrome(long number){
        long originalNum = number;
        long reverseNum = 0;
        while(number!=0){
            long remainder = number%10;
            number = number/10;
            reverseNum = reverseNum * 10 + remainder;
        }
        return originalNum == reverseNum;
    }
}


//Time comlexity: O(length of number)


