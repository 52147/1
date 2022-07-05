// 1047. Remove all adjacent duplicates in string
// keep remove the adjacent duplicate until there is no adjacent duplicate in string

// approach: use StringBuilder to work like Stack
// input : s = "abbaca"
// output: "ca"

public class RemoveAllAdjacentDuplicatesInString {
	
	public String removeDuplicates(String s) {
		// use stringbuilder to work first in last out way
		StringBuilder stack = new StringBuilder();
		
		// use a variable to record current stringbuilder length
		int sblength = 0;
		
		// time: O(N)
		// iterare each char in string s
		for(char c: s.toCharArray()) {
			// if sblength variable is not 0 and the last char in stringbuilder is equal the current char
			// delete the c in string builder and decrease the sblength
			if(sblength != 0 && stack.charAt(sblength - 1) == c) {
				stack.deleteCharAt(sblength - 1); // space: O(N - Duplicates)
				sblength--;
			}else {
				// otherwise there is empty of stringbuilder or char in stringbuilder and string s is not equal
				// append the char in stringbuilder 
				// increment the sblength
				stack.append(c);
				sblength++;
			}
		}
		
		return stack.toString();
	}
	

}
