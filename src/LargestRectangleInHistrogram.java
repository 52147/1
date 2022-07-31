
public class LargestRectangleInHistrogram {
    // Q: find the largest rectangle area in histogram
    
    // Approach: Monotonic Stack with array
    
    // find the bar height that we can keep use to count the area, 
    // if the current bar is smaller or equal than the left side bar,
    // we count the area by use left side bar height and discard it 
    // because we can not use this left side bar height to count the area later(with the width that current index - left left side bar index - 1)
    // we can use the stack(first in last out) because stack can keep add the element and check their value then pop by array order
    
    // 1. use an array to implement stack, array length with height.length + 1 
    //    because when we pass the last index, if there is still have bar index in stack
    //    (means is the bar that smaller than every left side and right side bar), we want to use that bar height to count the area
    // 2. put -1 as the first index in array because bar at index 0 still has the width 1
    // 3. Variable maxarea to record max area
    // 4. for loop iterate heights[] elements start at index 0 , end at heights.length
    //    - get the value of current index at heights[], if the i is pass heights[] give it 0
    //    - while loop to check the current bar height(i) with it left side bar height(in stack)
    //      if the current bar height is smaller than left side bar height, keep pop left side bar and use it to count the area
    //      - put the left side bar index in height[] to found height value
    //      - stack index minus one (means we pop this left side bar index from stack)
    //      - peek the top element in stack( because we need to find the distance between current index to this left left side bar, 
    //        first check is not < 0(means empty stack), if it is < 0, assign -1 otherwise assign left left side bar index
    //      - width is current index i minus left left side bar index minus 1
    //      - update the max area
    //    - while loop end means current bar is larger than the last bar(no need to pop) or we just pop left side bar
    //      so now need to put the current bar index in stack
    //    - increment stackindex
    //    - update i to this stack index value
    // 5. for loop end
    //    return maxArea

    public int largestRectangleArea(int[] heights) {
        if(heights.length == 1){ // edge case, if only have one bar in diagram
            return heights[0];
        }
        
        int[] stack = new int[heights.length + 1];
        int stackindex = -1; // first push -1 in stack
        int maxArea = 0;
        for(int i = 0; i <= heights.length; i++){
            int val = i == heights.length ? 0 : heights[i]; // get the current bar height
            while(stackindex >= 0 && heights[stack[stackindex]] >= val){ // if the left side bar(in stack) is larger than current bar, pop and calculate area
                int height = heights[stack[stackindex]]; // left side bar height
                stackindex--; // pop this bar out from stack
                int peek = stackindex < 0 ? -1 : stack[stackindex]; // peek the top element for calculate the width
                int width = i - peek - 1; // width is the distance from current index to peek
                maxArea = Math.max(maxArea, height * width); // update max area
            }
            stackindex++; // plus 1 for stack index because we want to put the current index in stack
            stack[stackindex] = i; // put the current index in stack
        }
        return maxArea;
    }  
    

}
