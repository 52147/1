import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MakeArrayZeroBySubstractingEqualAmounts2357 {
	public int minimumOperations(int[] nums) {
		if (nums.length == 1 && nums[0] == 0) {
			return 0;
		}
		HashMap<Integer, Integer> map = new HashMap<>();
		HashSet<Integer> set = new HashSet<>();

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			set.add(nums[i]);
			set.remove(0);
			min = Math.min(min, nums[i]);
		}
		if (set.isEmpty())
			return 0;

		int j = 0;
		for (int n : set) {
			if (!map.containsKey(n)) {
				map.put(n, j);
				j++;
			}
		}

		if (set.size() == map.size()) {
			return set.size();
		}

		List<Integer> l = new ArrayList<>(set);
		int ans = 1;
		while (!l.isEmpty()) {

			for (int i = 0; i < l.size(); i++) {
				l.add(l.get(i) - min);
				l.remove(l.get(i));
				l.remove(0);
			}

			int m = Integer.MAX_VALUE;
			for (int i = 0; i < l.size(); i++) {
				min = Math.min(l.get(i), m);
			}
			ans++;
		}
		return ans;
	}

	public int minimumOperations2(int[] nums) {
		if (nums.length == 1 && nums[0] == 0) {
			return 0;
		}
		HashSet<Integer> set = new HashSet<>();

		for (int i = 0; i < nums.length; i++) {
			set.add(nums[i]);
			set.remove(0);

		}

		return set.size();
	}

}
