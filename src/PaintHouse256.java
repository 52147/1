/**
 * 256. Paint House
 * - paint has 3 color
 * - can not paint adj house in same color
 * - find min cost to paint house
 * 
 * approach: dp
 * 
 * Algorithm:
 * 
 * iterate backwards over all the rows in the grid (starting from the second-to-last) 
 * and calculate a total cost for each cell
 *
 */
public class PaintHouse256 {
	
	public int minCost(int[][] costs) {
		
		for(int n = costs.length - 2; n >= 0; n--) {
			
			// total cost of painting the nth house red
			costs[n][0] += Math.min(costs[n + 1][1], costs[n + 1][2]);
			
			// total cost of painting the nth house green
			costs[n][1] += Math.min(costs[n + 1][0], costs[n + 1][2]);
			
			// total cost of painting the nth house blue
			costs[n][2] += Math.min(costs[n + 1][0], costs[n + 1][1]);
		}
		
		if(costs.length == 0) {
			return 0;
		}
		
		return Math.min(Math.min(costs[0][0], costs[0][1]), costs[0][2]);
	}

}
/**
 * Time Complexity : O(n)
 * 
 * Finding the minimum of two values and adding it to another value is an O(1) operation. We are doing these O(1) operations for 3⋅(n−1) cells in the grid. 
 * Expanding that out, we get 3⋅n−3. 
 * The constants don't matter in big-oh notation, so we drop them, leaving us with O(n).
 * 
 * A word of warning: This would not be correct if there were m colors. For this particular problem we were told there's only 3 colors. 
 * However, a logical follow-up question would be to make the code work for any number of colors. 
 * In that case, the time complexity would actually be O(n⋅m), because m is not a constant, whereas 3 is. 
 * If this confused you, I'd recommend reading up on big-oh notation.
 * 
 * Space Complexity : O(1)
 * 
 * We don't allocate any new data structures, 
 * and are only using a few local variables. 
 * All the work is done directly into the input array. 
 * Therefore, the algorithm is in-place, requiring constant extra space.
 */



