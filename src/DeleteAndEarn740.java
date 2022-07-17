import java.util.HashMap;

class DeleteAndEarn740 {
    // Q : delet the nums[i] and get the all nums[i] value, 
    // and all nums[i + 1] & nums[i - 1] will be delete
    // return the max value we can get when we delete all element in array
    
    // Approach: bottom up DP
    
    // 1. use a HashMap to store the value when we delete nums[i] -> Map<nums[i], total nums[i]>
    // 2. a variable maxvalue to record the largest nums[i]
    // 3. dp state is dp(number) -> number is 1, 2, 3, 4 .... max
    //    -> when we are at that number how max value we can get,
    //       so dp array length is largest nums[i] + 1(because start with 1)
    // 4. base case: dp[1] -> when we delete 1, we can only get total 1's value
    // 5. itereate dp length and recurrence relation to fill all state
    // 6. recurrence relation:
    //    when we are at i, we can choose either (i - 1)'s total points or (i - 2)'s total points + i's total value, 
    //    so compare them and choose larger one put in dp[i]
    //    -> dp[i] = max(dp[i - 1],  dp[i - 2] + map.get(i))
    // 7. return the last element in the dp[] (means when we are at the last index of nums[] and how many max points we can get)
    
    public int deleteAndEarn(int[] nums) {
        // map<nums[i], total nums[i]>
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // the max nums[i] be the dp length
        int max = 0;
        
        // time for this for loop : O(N) N is the total elements in nums[]
        // space: O(N) the total element in HashMap, n is the total elements in nums[]
        for(int n : nums){
            map.put(n, map.getOrDefault(n, 0) + n);
            max = Math.max(max, n);
        }
        // dp length + 1(start from 1, not need to compute 0)
        int[] dp = new int[max + 1]; // space: O(K) the total elements in dp[], k is max + 1
        
        // base case: dp[1] 
        dp[1] = map.getOrDefault(1, 0);
        
        // time for this for loop : O(k) k is the total elements in dp[] which is max + 1 
        // recurrence relation: dp[i] = dp[i] = max(dp[i - 1],  dp[i - 2] + map.get(i))
        for(int i = 2; i < dp.length; i++){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + map.getOrDefault(i, 0));
        }
        // when we are at the last position of nums[], the max points we can get is dp[max]
        return dp[max];        
    }
}

// total time: O(N + K) -> N is the total elements in nums[], K is max + 1
// total space:  O(N + K) -> N is the total elements in nums[] that store in HashMap, K is max + 1 elements that in dp[]
