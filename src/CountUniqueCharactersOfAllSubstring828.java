
class Solution {
    // Q: count all unique char in all substring
    //    ex : "ABC" all substring : 
    //         A(1 uni char), B(1 uni char), C(1 uni char), AB(2 uni char), BC(2 uni char), ABC(3 uni char)
    //         total uni char -> 1 + 1 + 1 + 2 + 2 + 3 = 10 -> return 10
    
    // Approach: DP
    // dp state is the last char in the substring
    // and state's value is when we at this state how many uni char we can get 
    //   -> given string "ABC", dp(2) : # of uni cahr of all substring that last index is char c -> 6 (because C, BC, ABC)   
    // use contribution[] to represent in this state how many this char we can get-> index: char, value: # of char
    // use lastpos[] to represent in this state this char last position is at -> index: char, value: char's index
    
    // when we are at i state, the # of uni char will be
    // -> last state # of uni char - this char contribution so far + new contribution when we at this state
    //    -> new contribution when we at this state -> i - lastposition of this char 
    // so dp formula be -> dp(i) = dp(i - 1) - contribution(i) + (i - lastpos)
    
    // for example:
    // given string s = "ABCBD" 
    
    // dp(1)
    // state 1 means substring last char is s.charAt(1) which is B
    // so all sunbstring so far that last char is B ->
    // -> AB(2 uni char), B(1 uni char) -> dp(1) = 2 + 1 = 3
    // update char B contribution and last position(initialize last position is 0 so update 1 be + 1)
    // -> and total char contribution and last position be like
    // -> contribution[A] = 1, contribution[B] = 1, lastpos[A] = 1, lastpos[B] = 2
    
    // dp(2)
    // state 2 mean means substring last char is s.charAt(2) which is C
    // so all sunbstring so far that last char is C ->
    // -> ABC(3 uni char), BC(2 uni char), C(1 uni char) -> dp(2) = 3 + 2 + 1 = 6
    // update char C contribution and last position
    // -> contribution[A] = 1, contribution[B] = 2, contribution[C] = 1, lastpos[A] = 1, lastpos[B] = 2, lastpos[C] = 3
    
    // dp(3)
    // state 3 mean means substring last char is s.charAt(3) which is B
    // so all sunbstring so far that last char is B ->
    // ABCB, BCB, CB, B
    // in BCB and ABCB the char B is not unique, so need to decrease the contribution of B and add the new contribution
    // so BCB decrease the prev B and add current B-> CB (same substring only need to count 1)
    //    ABCB decrease the prev B and add current B -> ACB
    // so total new substring be like -> ACB(3 uni char), CB(2 uni char), B(1 uni char) -> dp(3) = 3 + 2 + 1 = 6
    // dp formula:
    // -> dp(3) = dp(2) - contribution[B] + new contribution[b] which is i - (lastpos[B] - 1) need to minus 1 because all char last pos suppose start with -1
    // -> dp(3) = 6 - 2 + (3 - (2 - 1)) = 6
    // update char B contribution and last position 
    // -> contribution[A] = 1, contribution[B] = 2, contribution[C] = 1, lastpos[A] = 1, lastpos[B] = 4, lastpos[C] = 3


    public int uniqueLetterString(String s) {
        int res = 0;// res for add all dp(i) result together
        // edge case
        if(s == null || s.length() == 0){
            return res;
        }
        
        int cur = 0; // dp(i)'s value -> record current state how many # of unique char we can get
        int contribution[] = new int[128]; // table for record how many # of this char we got at this state
        int lastpos[] = new int[128]; // table for record the last position of this char
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i); // get the last char of the substring
            
            // dp(i) = dp(i - 1) - contribution(i) + (i - (lastpos- 1))
            cur -= contribution[c]; // first minus the contribution of this char
            contribution[c] = i - (lastpos[c] - 1); // count this char new contribution by using i - (lastpos - 1)
            cur += contribution[c]; // then add it in the current dp(i)
            
            // update this char lastposition
            lastpos[c] = i + 1;
            // add all dp state value together
            res += cur; 
        }
        return res;     
    }
}
