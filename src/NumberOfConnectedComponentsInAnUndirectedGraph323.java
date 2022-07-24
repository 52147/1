
public class NumberOfConnectedComponentsInAnUndirectedGraph323 {
	
    // Approach: Union Find 
    // use union find to connect the two node and when we connected 2 node minus the # of total node to get how many connected componets.
    private int[] root;
    private int[] size;

    public int find(int x){
        if(x == root[x]){
            return x;
        }
        
        return root[x] = find(root[x]);
    }    
    // time α(n) α: inverse ackerman function, recursion method that time grow slowly; n the # of nodes
    public int union(int x, int y){
        int rootx = find(x);
        int rooty = find(y);
        
        if(rootx != rooty){ // if 2 node are have different root node, connected them, and return 1 because they become 1 componet
            if(size[rootx] > size[rooty]){
                root[rooty] = rootx;
            }else if(size[rootx] < size[rooty]){
                root[rootx] = rooty;
            }else{
                root[rooty] = rootx;
                size[rootx]++;
            }
             return 1;
        }else{ // otherwise, they are have same root node(already connected) return 0
            return 0;
        }       
    }
    public int countComponents(int n, int[][] edges) {
        root = new int[n]; // space: O(V) store the root node of all vertex
        size = new int[n]; // space: O(V) store the rank of all vertex
        
        for(int i = 0; i < n; i++){
            root[i] = i;
            size[i] = 1;
        }
        
        int connected = n;
        // time O(E) iterate every edge
        for(int i = 0; i < edges.length; i++){
            connected -= union(edges[i][0], edges[i][1]);
        }
        return connected;        
    }
    // total time: O(E * α(n)); total space: O(V)

}
