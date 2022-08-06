import java.util.HashMap;

public class MinimumKnightMoves {
    // Q : find the min moves that knight(origin position) can reach to target
    // knight can move 8 directions and each time take 2 squares
    
    // Approach : top down dp with memorization
    // the position is symmetric, so we can consider the x, y position to abs(x), abs(y)
    // because every (abs(x), abs(y)) position min move is equal
    // ex: the min move to these 4 position (1, 2), (-1, 2), (1, -2), (-1, -2) is equal
    // and because use abs(x, y),
    // so knight's moving area be restrict at first quadrant (top-right)
    // so each time knight can choose go left 2 + up 1 or left 1 + down 2
    //  => abs(x - 2), abs(y - 1) or abs(x - 1), abs(y - 2)
    //    because move to another direction knight will get further away from origin
    
    // 1. dp(x, y) state: return the min move we reach to this(x, y)
    // 2. base case: 
    //    case 1: dp(x, y) -> x = 0 and y = 0 -> when x and y is equal to 0 means we are at origin, so no need to go forward
    //    case 2: dp(x, y) -> x + y = 2, when x + y equal to 2, we need to go zigzag way to target(2 step), 
    //                        ex: target = (0, 2), 1st step (2, 1), 2nd step (0, 2)
    // 3. dp formula: 
    //    dp(x, y) = min(dp(abs(x - 1), abs(y -2)), dp(abs(x - 2), abs(y - 1))) + 1
    //    need to plus one because when target is (2, 1), no plus 1, will directly return 0,
    //      ex: (2 - 1, 1 - 1) -> (x == 0 && y == 0) -> return 0
    private HashMap<String, Integer> map = new HashMap<>(); // space: O(|x* y|) because memo |x* y|'s key
    
    // time: O(|x* y|) because we restrict the moving area to first quadrant, so in first quadrant we have |x* y|'s positions
    private int dp(int x, int y){
        String key = x + "," + y;
        // use hash map to memo the position (x, y) with min path to reach this (x, y)
        // -> map<(x, y), # of min paths to reach>
        if(map.containsKey(key)){
            return map.get(key);
        }
        
        if(x == 0 && y == 0){
            return 0;
        }else if(x + y == 2){
            return 2;
        }else{
            Integer res = Math.min(dp(Math.abs(x - 1), Math.abs(y - 2)), dp(Math.abs(x - 2), Math.abs(y - 1))) + 1;
            map.put(key, res);
            return res;
        }
    }
    public int minKnightMoves(int x, int y) {
        return dp(Math.abs(x), Math.abs(y));
    }

}
