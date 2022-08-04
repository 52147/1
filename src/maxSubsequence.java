import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class maxSubsequence {

    public int[] maxSubsequence(int[] nums, int k) {
		// Q find the max sum of k elements and return these k element in subsequence(delete the element but without changing the order)
		// Approach: HashMap + 2 PriorityQueue(min heap)
				
		// Map<index in array, value of this index element> -> Map<i, nums[i]>
		// first PriorityQueue sort by map value and maintain k size
		// After first PriorityQueue done by sorting
		// Use second PriorityQueue to sort the element that in first PriorityQueue by map key
		// while loop to poll element out from second PriorityQueue and add each value in output[] from index 0 to k - 1

		HashMap<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			map.put(i, nums[i]);
		}

		PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

		for (Map.Entry<Integer, Integer> n : map.entrySet()) {
			pq.add(n);
			if (pq.size() > k) {
				pq.poll();
			}
		}

		PriorityQueue<Map.Entry<Integer, Integer>> pq2 = new PriorityQueue<>((a, b) -> a.getKey() - b.getKey());

		for (int j = 0; j < k; j++) {
			pq2.add(pq.poll());
		}

		int[] out = new int[k];

		int i = 0;

		while (!pq2.isEmpty()) {
			out[i] = pq2.poll().getValue();
			i++;
		}
		return out;
	}

}
