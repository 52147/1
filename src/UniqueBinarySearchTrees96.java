
public class UniqueBinarySearchTrees96 {
    public int numTrees(int n) {
        // Approach: DP
        // state i:
        // dp(i) -> value is the # of combination of i nodes
        // base case: 
        // dp(0) = 1, when have 0 node, total combination is 1
        // dp(1) = 1, when have 1 node, total combination is 1
        // recurrence relation:
        // # of i nodes combination 
        //   -> (# of nodes that is at root node left side) * (# of nodes that is at root node right side)
        // choose j as the root node 
        //   -> dp(i) += dp(j - 1) * dp(i - j)
        // ex:
        // i = 2 
        // dp(2) = dp(0) * dp(1) [means choose 1 as root, left side has 0 node, right side has 1 node]
        //         + dp(1) * dp(0) [means choose 2 as root, left side has 1 node, right side has 0 node] 
        //           = 2 [total combination when has 2 nodes]
        // i = 3
        // dp(3) = dp(0) * dp(2) [means choose node 1 as root, left side has 0 node, right side has 2 nodes]
        //         + dp(1) * dp(1)[means choose node 2 as root, left side has 1 node, right side has 1 node]
        //         + dp(2) * dp(0)[means choose node 3 as root, left side has 2 node, right side has 0 node] 
        //           = 5 [total combination when has 3 nodes]
        
        int dp[] = new int [n + 1]; // space: O(N), N is # of nodes
        dp[0] = 1;
        dp[1] = 1;
        // time : O(n ^ 2)
        for(int i = 2; i <= n; i++){
            for(int j = 1; j <= i; j++){
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }        
        return dp[n];        
    }

}
