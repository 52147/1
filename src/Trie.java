import java.util.HashMap;
import java.util.Map;

class Trie {
    // use hashmap to represent node(char c)'s children -> Map<path char c, children node>
    class TrieNode{
        public boolean isWord = false;
        public Map<Character, TrieNode> children = new HashMap<>();        
    }    
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    // time : O(M) m is the max length of word string
    // if char not has children node, new one for this children 
    public void insert(String word) {
        TrieNode cur = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(cur.children.get(c) == null){
                cur.children.put(c, new TrieNode());
            }
            cur = cur.children.get(c);
        }
        cur.isWord = true;// end of the insertion of whole word, set the last char flag to true        
    }
    // time: O(M)
    // search for exactly word(not prefix)
    // if we can not find this char children, return false
    // otherwise keep searching the next node
    public boolean search(String word) {        
        TrieNode cur = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(cur.children.get(c) == null){
                return false;
            }
            cur = cur.children.get(c);
        }
        return cur.isWord;// for loop end but because may be is a prefix not a word, so return the curr node flag to check        
    }
    // time: O(M)
    // Search for prefix, if we found the whole path for every char children, can directly return true
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(cur.children.get(c) == null){
                return false;
            }
            cur = cur.children.get(c);
        }
        return true;       
    }
    // total time: O(M)
    // total space: O(M * N) M is max length of the key, N is the number of keys in HashMap
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
