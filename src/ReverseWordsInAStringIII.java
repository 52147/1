// 557 reverse words in a string III
// given a sentence of string 
// s = "Let's take LeetCode contest"
// reverse each word in this sentence
// "s'teL ekat edoCteeL tsetnoc"

// approach: stringbuilder + reverse

public class ReverseWordsInAStringIII {

	
	public String reverseWords(String s) {
		
		// spcae: O(n)
		// create 2 stringbuilder 
		// one stringbuilder for append each char to a word and reverse it
		StringBuilder word = new StringBuilder();
		// one stringbuilder for append in each word to res and append white space between each word
		StringBuilder res = new StringBuilder();
		
		// time: O(n)
		// iterate each char in string s
		for(char c: s.toCharArray()) {
			// if current char is not white space, append this char in word stringbuilder
			if(c != ' ') {
				word.append(c);
			}else {
				// otherwise 
				// current char is white space means we process one word and we can reverse it and add the white in res stringbuilder
				res.append(word.reverse());
				res.append(" ");
				// set the word stringbuilder to 0 because we want to use this word stringbuilder reapeteadly process each word
				word.setLength(0);
			}
						
		}
		
		// after for loop end
		// we still have one last word in word stringbuilder not add it in res stringbuilder
		// so add it in the res string builder
		res.append(word.reverse());
		
		// return res tostring
		return res.toString();
		
	}
	

}
