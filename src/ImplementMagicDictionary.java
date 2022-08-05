
// Approach: Trie
//class TrieNode{
//    public boolean isWord;
//    public TrieNode[] children;
//    public TrieNode(){
//        this.isWord = false;
//        this.children = new TrieNode[26];
//    }        
//}
class MagicDictionary {
    private TrieNode root;
    public MagicDictionary() {
        this.root = new TrieNode();
    }    
    public void buildDict(String[] dictionary) {
        for(String s: dictionary){
            TrieNode cur = root;
            for(char c: s.toCharArray()){
                if(cur.children[c - 'a'] == null){
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
            }
            cur.isWord = true;
        }
    }
    // Search if word can be found in Trie that change one character
    // if word that char change to 26 letter can be found in Trie, return true
    // if the for loop end not return true, return false
    // because is the conditions -> the same word in Trie or need to change more that one char
    // use another private search method to search the new word(change only one char) whether is in the dict 
    public boolean search(String searchWord) {
        char arr[] = searchWord.toCharArray();
        for(int i = 0; i < arr.length; i++){
            // change current char to 26 letter
            for(char c = 'a'; c <= 'z'; c++){
                // if current char is the same of c, jump to next iteration, otherwise change current char to this c
                if(arr[i] == c){
                    continue;
                }
                // first store the original char 
                char originalchar = arr[i];
                //change current char to char c
                arr[i] = c;
                // convert new word to string
                String newword = String.valueOf(arr);
                // check if the new word can be found in the dictionary(Trie)
                if(searchNewWord(newword, root)){
                    return true;
                }
                // change the char back to original char for the next iteration
                arr[i] = originalchar;                
            }
        }
        return false;
    }    
    // search new word that change one char 
    private boolean searchNewWord(String neww, TrieNode root){
        TrieNode cur = root;
        for(char c : neww.toCharArray()){
            if(cur.children[c - 'a'] == null){
                return false;
            }
            cur = cur.children[c - 'a'];
        }
        return cur.isWord;
    }
}
