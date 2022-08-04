
class TrieNode{
    // use array to represent the children, every node has 26 length TrieNode[]
    public TrieNode children[];
    public boolean isWord;
    public TrieNode(){
        this.children = new TrieNode[26];
        this.isWord = false;
    }
}
class WordDictionary {
    private TrieNode root;

    public WordDictionary() {
        this.root = new TrieNode();
    }
    
    // time: O(n), n is length of word
    public void addWord(String word) {
        TrieNode cur = root;
        for(char c : word.toCharArray()){
            if(cur.children[c - 'a'] == null){
                cur.children[c - 'a'] = new TrieNode();
            }
            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;        
    }
    
    public boolean search(String word) {        
        return search(word, root, 0);
    }
    // time: O(26^ n), in worst case, have 26 children at every trie node
    // if the index is equal to word length(find the total char of word), return cur node flag
    // else if the char is not '.', keep use recursive search() search the next char's children, and if the children is null return false
    // else char is equal to '.', iterate this node all children and recursive serach each path, if one path return true, return true 
    private boolean search(String word, TrieNode cur, int index){        
        if(index == word.length()){
            return cur.isWord;
        }else if(word.charAt(index) != '.'){
           return cur.children[word.charAt(index) - 'a'] != null && search(word, cur.children[word.charAt(index) - 'a'], index + 1);       
        }else{
            for(TrieNode n: cur.children){
                if(n != null && search(word, n, index + 1)){
                    return true;
                }
            }
            return false;
        }        
    }
}

