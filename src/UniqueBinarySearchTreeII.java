
public class UniqueBinarySearchTreeII {
	
	// Q: given n (means have 1 ~ n nodes) return all combination of n nodes
    // Approach: recursion
    // recursion method GT(parameter: left(node 1), right(node n)); output: list contains all combination
    // 1. create an output list res
    // 2. if left > right -> means no node need to create
    //     - add null in res
    //     - return res
    // 3. for loop iterate from left to right to keep generate left and right subtree; i means root node
    //    - left tree = GT(left, i - 1) -> left subtree range from left to i - 1 because i is root node and this range is i left side
    //    - right tree = GT(i + 1, right) -> right subtree range from i + 1 to right becaue is root node and this range is i right side
    // 4. iterate left tree and right tree to creat i node and connet with left and right child, then add new i node in res
    // 5. return res 
    
    // generateTrees method to trigger recurion(parameter n means n nodes)
    // if n == 0 retrun empty list
    // return GT(1, n) -> initialze left with 1(fist node), right with n (last node)
    public List<TreeNode> generateT(int left, int right){
        List<TreeNode> res = new LinkedList<>();
        
        if(left > right){
            res.add(null);
            return res;
        }
        
        for(int i = left; i <= right; i++){
            List<TreeNode> leftT = generateT(left, i - 1);
            List<TreeNode> rightT = generateT(i + 1, right);
            
            for(TreeNode l : leftT){
                for(TreeNode r: rightT){
                    TreeNode parent = new TreeNode(i);
                    parent.left = l;
                    parent.right = r;
                    res.add(parent);
                }
            }
        }
        return res;
    }
    public List<TreeNode> generateTrees(int n) {
        
        if(n == 0){
            return new LinkedList<>();
        }
        return generateT(1, n);
        // time: n * (Catalan number); catalan number -> # of possible BST(combination),satisfy recurrence relations
        // space: n * (Catalan number)
        
    }
}

}
