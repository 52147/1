import java.util.HashMap;

// 1. two sum
// given an array and a number target
// find the two number index sum is equal to target 
// return array contain 2 index ex: [1, 2]

// approach : one - pass table
public class TwoSum {
	
	public int[] twoSum(int[] nums , int target) {
		
		// space: O(n)
		// map<element, index>
		HashMap<Integer, Integer> map = new HashMap<>();
		
		// time: O(n)
		// for loop iterate the nums
		for(int i = 0; i < nums.length; i++) {
			// get the complement from each iteration by target - current element
			int com = target - nums[i];
			// check if map contains this complement
			if(map.containsKey(com)) {
				// if map it is contains, return complement index and current element index
				return new int[] {map.get(com), i};
			}
			// otherwise put the element map with its index in map
			map.put(nums[i], i);
		}
		
		// iteration done, no return 2 index array, return null which means can not find ans in given array
		return null;
		
	}

}
