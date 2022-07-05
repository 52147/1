import java.util.Stack;

// 1209. Remove All adjacent Duplicates in String II
// given string , keep delete adjacent k duplicates
//Input: s = "abcd", k = 2
//Output: "abcd"

// Approach: Stack + StringBuilder

public class RemoveAllAdjacentDuplicatesInStringII {
	
	public String removeDuplicates(String s, int k) {
		// stack to store the each char freq
		Stack<Integer> count = new Stack<>(); // space: O(N)
		StringBuilder sb = new StringBuilder(s);
		
		// time: O(n)
		for(int i = 0; i < sb.length(); i++) {
			// if i == 0 or adj char is not equal, push 1 to stack
			if(i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
				count.push(1);
				
			}else {
				// else there is adj duplicates, pop count out and plus + 1(this time char we found)
				int c = count.pop() + 1;
				if(c == k) { // check if duplicates count is equal to k
					sb.delete(i - k + 1, i + 1); // if it is equal to k, delete the duplicate in sb
					i = i - k; // update the index because the sblenght is decreased
				}else {
					count.push(c); // otherwise, is not equal to k, push count back to k
				}
			}
		}
		
		return sb.toString();
		
		
		
	}
	

}
