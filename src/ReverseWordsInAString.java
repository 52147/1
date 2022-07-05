import java.util.ArrayDeque;
import java.util.Deque;

// 151. Reverse Words in String
// reverse word order in sentence not char in word
// there is white space in front of the sentence and behind the last word
// Input: s = "the sky is blue"
// Output: "blue is sky the"

// approach: StringBuilder  + Dequq
// stringbuilder for append each word
// deque for move the word to the front

public class ReverseWordsInAString {
	
	public String reverseWords(String s) {
		
		// use left and right pointer to remove the leading and trailing white space
		int l = 0;
		int r = s.length() - 1;
		
		// while loop to remove the leading white space 
		while(l <= r && s.charAt(l) == ' ') {
			l++;
		}
		// while loop to remove the trailing white space 
		while(l <= r && s.charAt(r) == ' ') {
			r--;
		}
		// for append char to form word
		StringBuilder word = new StringBuilder();
		// for revere the word in sentence
		Deque<String> d = new ArrayDeque<>();
		
		
		// iterate the char in string s
		while(l <= r) {
			// if the word stringbuilder is not empty and element is white space
			// move the word to the front and set the word length to 0
			if(word.length() != 0 && s.charAt(l) == ' ') {
				d.offerFirst(word.toString());
				word.setLength(0);
			// otherwise
		    // there is letter in this index, append the word in stringbuilder
			}else if(s.charAt(l) != ' ') {
				word.append(s.charAt(l));
			}
			
			// forward left pointer
			l++;
		}
		
		// move the last word to front
		d.offerFirst(word.toString());
		
		// convert the array deque to string and split with white space
		return String.join(" ", d);
		
		
		
	}
	

}
