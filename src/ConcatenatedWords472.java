import java.util.ArrayList;
import java.util.List;

public class ConcatenatedWords472 {
	
	class TrieNode{
	    public TrieNode[] children;
	    public boolean isWord;
	    public TrieNode(){
	        this.children = new TrieNode[26];
	        this.isWord = false;
	    }
	}

	class Solution {
	    private TrieNode root;
	    
	    public List<String> findAllConcatenatedWordsInADict(String[] words) {        
	        List<String> res = new ArrayList<>();        
	        this.root = new TrieNode();
	        
	        for(String w : words){
	            insert(root, w);
	        }        
	        for(String w : words){
	            if(search(w, root, 0, 0)){
	                res.add(w);
	            }
	        }
	        return res;        
	    }
	    
	    public void insert(TrieNode root, String word){
	        TrieNode cur = root;
	        for(char c: word.toCharArray()){
	            if(cur.children[c - 'a'] == null){
	                cur.children[c - 'a'] = new TrieNode();
	            }
	            cur = cur.children[c - 'a'];
	        }
	        cur.isWord = true;
	    }
	    // recursive call search method to keep search the small word in large word
	    // parameters : 
	    //   index : each time start from parameter index to search if there is a valid word
	    //   count : record the # of valid words we found in the word string
	    // return value :
	    //   true : if we found a valid word or count >= 1
	    //   false : we not found a valid word
	    // Algorithm:
	    // for loop iterate char in word string
	    //   if we not find one valid word(isWord not true)
	    //     return false
	    //   if we found valid word(isWord is true)
	    //     and if we are at the last index in String word,
	    //        return true if the count >= 1
	    //     and if search the next index of char is a valid word
	    //        return true
	    //   move to next node in trie
	    // for loop end(not find the valid word or count not >= 1)
	    // return false
	    public boolean search(String word, TrieNode root, int index, int count){
	        TrieNode cur = root;
	        for(int i = index; i < word.length(); i++){
	            if(cur.children[word.charAt(i) - 'a'] == null){
	                return false;
	            }
	            
	            if(cur.children[word.charAt(i) - 'a'].isWord){
	                if(i == word.length() - 1){
	                    return count >= 1;
	                }
	                if(search(word, root, i + 1, count + 1)){
	                    return true;
	                }
	            }
	            cur = cur.children[word.charAt(i) - 'a'];
	        }
	        return false;
	    }
	}

}
