import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {
    // Q: return right side view node(each level last node)
    // Approach: BFS level order traversal
    
    // use LinkedList to implement Queue interface
    // add the first root node in Queue
    // while loop to keep iterate when Queue is not empty
    //   - a variable size to store # of this level nodes
    //   - for loop to iterate this level node(variable size)
    //      - poll the node out when we go throught this node
    //      - and if this node is at the last index of this level
    //      - add it in the ouput list
    //      - check this node left and right child,
    //        if they are not null, add left and add right
    // while loop end
    // return result list
    public List<Integer> rightSideView(TreeNode root) {
        
        List<Integer> res = new ArrayList<>();
        
        if(root == null)
            return res;
        
        Queue<TreeNode> q = new LinkedList<>();
        
        q.add(root);
        
        while(!q.isEmpty()){
            int size = q.size();
            
            for(int i = 0; i < size; i++){
                TreeNode node = q.remove();
                
                if(i == size - 1){ // if node is at last index of size (means is the last node of this level)
                    res.add(node.val); // add this node value in output list
                }
                if(node.left != null){
                    q.add(node.left);
                }
                if(node.right != null){
                    q.add(node.right);
                }                
            }
        }        
        return res;
        // time: O(n) each node process once
        // space: O(D) D is tree diameter(the length of the longest path between two nodes in a tree)        
    }

}
