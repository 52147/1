import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentWords692 {
    // Q: find the top k freq words 
    //    and return words ordered by freq from the highest to lowest
    //    and if the freq is equal return by the order of lexicographical order
    // Approach: Min Heap + HashMap
    // 0. create an output list to store output, 
    //    this time use linked list is better than arraylist
    //    (because we want to add element at first, linkedlist takes O(1)time)
    // 1. edge case: if words[] is null or words[] length = 0 or words[] length = 1, return empty list
    // 2. create a hash map to store the word freq -> Map<word, freq>
    // 3. for loop iterate words[] to calculate each word freq and put it in map
    // 4. create a min heap which reference is Map.Entry<String, Integer> because we want to sort the order 
    //      -> a. if 2 word freq in map is equal -> use compareTo() sort by lexicographical
    //      -> b. otherwise -> sort by their values in map (word's freq)
    // 5. for loop iterate entry set
    //       add each entry in min heap
    //       if minheap size larger than k
    //          poll first element out to maintain minheap elements that contains 3 largest freq word so far
    // 6. for loop end, now minheap contains 3 largest freq word in words[]
    //    use while loop to keep poll the element out and keep add that element's key in the output list's first index(index 0)
    //    (because first poll out element is smallest freq, 
    //     we want output list order be largest freq word -> smallest freq word)
    // 7. return output list
    public List<String> topKFrequent(String[] words, int k) {
        
        List<String> res = new LinkedList<>(); // choose linked list to implement is better than ArrayList, 
                                               // because we want to keep add element at the first index to form output,
                                               // add first in linkedlist takes O(1) time, in ArrayList takes O(N) time
        // edge case
        if(words == null || words.length == 0 || words.length == 1){
            return res;
        }
        // map<word, word's freq>
        HashMap<String, Integer> map = new HashMap<>();
        // for loop to count word's freq
        for(String w : words){
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        // min heap to sort the hashmap entry(key-value pair): 1. value are equal -> sort by key(lexicographical) 2. value are not equal -> sort by value(small to large)
        PriorityQueue<Map.Entry<String, Integer>> minheap = new PriorityQueue<>((a, b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue());
        // iterate entry set, keep add entry in min heap, if min heap size > k, poll elements out
        for(Map.Entry<String, Integer> entry: map.entrySet()){
            minheap.add(entry);
            if(minheap.size() > k){
                minheap.poll();
            }
        }
        // fill output list: keep add poll out element's at first of the output list 
        while(!minheap.isEmpty()){
            res.add(0, minheap.poll().getKey());
        }        
        return res;
    } // time: O(nlogk): add() & poll() has logk time and use for loop to iterate them n time ; space: O(N): hashmap contains n elements 
}
