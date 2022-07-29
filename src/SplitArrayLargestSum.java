
public class SplitArrayLargestSum {
	
    // Q: split the m subarray in nums[] and return the largest sum in one of the splited array.
    // Approach: Binary Search
    // use binary search to search the subarray sum start from the range left(max value element in array) to right(sum of array)
    // subarray need to meet the requirement -> split array to m subarray
    // so we use a method to split the subarray when subarray sum is larger than mid(current subarray maximum sum)
    // if the # of split subarray not larger than m
    // keep update the mid to split array largest sum
    // and made right smaller to found the percise split array largest sum
    public int split(int[] nums, int maxSum){
        int currentsum = 0;
        int splitnumber = 0;
        
        // O(n) time
        for(int n: nums){
            if(currentsum + n <= maxSum){
                currentsum += n; // greedily add n to current subarray sum until subarray sum become larger than max sum
            }else{
                currentsum = n; // if current subarray sum > max subarray sum, split the array and reset the currentsum to n 
                splitnumber++;
            }
        }        
        return splitnumber + 1; // return the total # of splited subarray
    }
    public int splitArray(int[] nums, int m) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        
        for(int n: nums){
            sum += n;
            max = Math.max(max, n);
        }
        
        int left = max;
        int right = sum;
        int splitlargestsum = 0;
        // binary search time: log(S) -> S is the the sum of integer in array
        while(left <= right){
            int mid = left + (right - left) / 2;
            // split method: O(n) time; each call iterate n element 
            if(split(nums, mid) <= m){ // if # of splited subarray not larger than m
                right = mid - 1; // move right to mid - 1 (smaller the range to left side of mid)
                splitlargestsum = mid; // updat the splited subarray largest sum to mid
            }else{
                left = mid + 1; // if # of splited subarray larger than m, move left to mid + 1(right side of the mid)
            }
        }
        return splitlargestsum;
        // total time: binary search * split method -> O(n * log(S)) time
        // total space: O(1)
    }

}
