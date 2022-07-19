// 346. Moving Average from Data Stream

// Q : given a class MovingAverage, implement 2 functions in it
//     1. MovingAverage(int size) : initialize the size of sliding window object
//     2. double next(int val) : return the average in sliding window
//        (if # of element > slidingwindow size, move the window to the current added element and count the average in sliding window)

// Approach: using array as circular queue

// we want the array to be like queue(first in first out when array size larger than sliding window size remove first added element),
// so we need to keep head and tail element next each other in array
// use this formula we can keep update tail index in array -> tail = (head + 1) % size (the tail will be circular updated)

// double next(int val) method:
// 1. increment count to record the how many times we call next() to add new element
// 2. found the tail index use -> tail = (head + 1) % size
// 3. and we count the sum in sliding window = 
//      last time window sum - tail index element + current added element
// 4. then we update the head index to tail index
// 5. and put the current element in head index
// 6. calculate the slindingwindow average = 
//      windowsum * (1.0) / current window size which is min(slidingwindow size, # of element we added) 

class MovingAverage {
    
    int head = 0; // initialize head index with 0
    int size = 0; // for slinding window size
    int count = 0; // for record # of time we add new element
    int windowsum = 0; // for record current sliding window sum
    int queue[]; // for circular queue

    public MovingAverage(int size) {
        this.size = size; // initialize slinding window size
        queue = new int[size]; // initialize the queue array with slinding window size        
    }
    
    public double next(int val) {
        
        count++; // # of element we add
        int tail = (head + 1) % size; // found the tail index in array
        windowsum = windowsum - queue[tail] + val; // compute windown sum by decrease first added element then added current new element
        head = (head + 1) % size; // move the head index to tail index
        queue[head] = val; // put the current element in head index in queue[]
        
        return windowsum * 1.0 / Math.min(size, count);// return the average in sliding window         
    }
}

// time : O(1) because no for loop
// space: O(N) use array as circular queue

