
public class SubstringWithLargestVariance2272 {
    // Q: find the max variance of two char
    //    variance is the max difference of freq of 2 char in a substring
    //    ex: s = "aababbb" 
    //        -> return 3, the max variance is 3 because in substring "babbb" -> 4(b's freq) - 1(a's freq) = 3(is the largest variance we can find in all substring)
    
    // Approach: DP(Kandan's Algorithm)
    
    // Kandan's algorithm:
    // - iterative dp algorithm
    // - calculate max sum subarray ending at index i by using max sum subarray ending at index i-1 
    //   -> dp formula: dp(i) = max(dp(i - 1) + arr[i], arr[i]) or maxsubarray = max(maxsubarray, currentsubarray)
    //   -> time: O(N) N is size of given array ; space: O(1) 2 variables maxsubarray & currentsubarray
    
    // In this question, we can use Kandan's algorithm to find all substring max difference between 2 char's occurrences
    
    // 1. use count table to find all char freq in string s
    // 2. 2 nested for loop to find all 2 combination of 26 letters
    //    - get the freq of char i and char j and strore them in variable totalifreq, totaljfreq respectively
    //    - if i and j is equal or one of then freq is 0, continue to next iteration
    
    //    2.1. run the Kandan's Algorithm
    //         - define 2 variable currentifreq, currentjfreq for 2 char freq in substring
    //         - iterate the char in string s
    //         - get cuurent char ch
    //         - if ch is equal to char j -> currentjfreq++
    //         - if ch is equal to char i -> currentjfreq++ and 
    //                                    -> totalifreq-- 
    //                                    -> (to check if j and i freq is equal and there still have i remaining in the rest substring, 
    //                                    -> we need to set the current freq i and j to 0)
    //                                    -> because we first choose to find the max by j's freq - i's freq's, 
    //                                    -> so j's freq need to be larger than i's freq to prevent the max be negative
    //         - if currentifreq > 0 -> count the max variance until now
    //                               -> maxvariance = max(maxvariance, currentjfreq - currentifreq) -> j freq - i freq
    //         - check if j current freq is smaller than curren i freq and total i freq still >= 1, 
    //           -> because it means i freq and j freq are equal and in later index there is more i in the string
    //              -> so reset the currentifreq and currentjfreq to 0
    //                 -> because we want the max (j freq - i freq), if we don check this condition, the value will be negative
    //                 -> ex: bbbaaaaaa when we are at index 5, 3(b's freq) - 3(a's freq) = 0 -> later than index 5 value will be negative
    public int largestVariance(String s) {
        int[] count = new int[26];
        // count all char in string s freq
        for(int i = 0; i < s.length(); i++){
            count[s.charAt(i) - 'a']++;
        }        
        int maxv = 0; // to record max variance
        // find the combination of 2 char 
        for(int i = 0; i < 26; i ++){
            for(int j = 0; j < 26; j++){
                
                int totalifreq = count[i]; // get the i freq
                int totaljfreq = count[j]; // get the j freq
                // if i and j are equal or either of them is 0 contunue to find the next char of j
                if(i == j || count[i] == 0 || count[j] == 0){
                    continue;
                }
                
                //Kandan's Algorithm start:
                int curifreq = 0; // record the current i freq in cuurent index
                int curjfreq = 0; // record the current j freq in cuurent index
                // iterate each char
                for(int c = 0 ; c < s.length(); c++){
                    int ch = s.charAt(c) - 'a';
                    // if current char is equal to j, current j freq++
                    if(ch == j){
                        curjfreq++;
                    }
                    // if current char is equal to i, current i freq++
                    if(ch == i){
                        curifreq++;
                        totalifreq--; // and we need to decrease the total i freq 
                                      // because this time we want to use current j freq - current i freq to find the max variance
                                      // so we need to make sure the current i freq can not larger than j freq
                    }
                    // if we found the i, count the difference between i and j freq
                    if(curifreq > 0){
                        maxv = Math.max(maxv, curjfreq - curifreq);
                    }
                    // make sure i freq can not larger than j's freq, if it is, reset the current i and j freq to 0
                    if(curifreq > curjfreq && totalifreq >= 1){
                        curifreq = 0;
                        curjfreq = 0;
                    }
                }          
            }
        }
        return maxv;
        // total time: O(26 * 26 * N) 3 nested for loop, the 2 iterate 26 times and the other iterate n time(length of string s)
        // total space: O(1) use only variables
    }
}
