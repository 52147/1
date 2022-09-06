
public class LongestSubsequenceWithLimitedSum {
	public int[] answerQueries(int[] nums, int[] queries) {
		Arrays.sort(nums);

		int[] ans = new int[queries.length];

		int i = 0;
		for (int q : queries) {
			int sum = 0;
			int size = 0;
			for (int n : nums) {
				sum += n;

				if (sum <= q) {
					size++;
					ans[i] = size;
				} else if (sum > q) {
					ans[i] = size;
				}
			}
			i++;
		}
		return ans;
	}

}
