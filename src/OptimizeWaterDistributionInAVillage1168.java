import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OptimizeWaterDistributionInAVillage1168 {
	
    // Q: house that can choose build wells or pipes, choose the minimum cost way and return minimum cost
    
    // Approach : Kruskal Algorithm(with union find)
    // Kruskal Algorithm: 
    // a greedy algorithm in graph theory, keep add lowest weight edges to form Minimum Spanning Tree(MST: no cycle, in undirected graph, minimum total edges weights)
    
    // use Union Find to implement Kruskal Algorithm :
    // 1. sort the edges weight in ascending order
    // 2. then use union() to connect two different group vertex with minimum weight edges and form MST
    private int[] root;
    private int[] rank;
    
    // find() to find root node
    public int find(int x){
        if(root[x] == x){
            return x;
        }
        return root[x] = find(root[x]);
    }
    // union() to connect different group vertex, if connected return true, otherwise return false
    public boolean union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        
        if(rootX != rootY){
            if(rank[rootX] > rank[rootY]){
                root[rootY] = rootX;
            }else if(rank[rootX] < rank[rootY]){
                root[rootX] = rootY;
            }else{
                root[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }else{
            return false;
        }
    }    
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        
        root = new int[n + 1]; // initialize root[] & rank[] with length n + 1 because house start from 1 to n
        rank = new int[n + 1];
        
        for(int i = 1; i <= n; i++){ // union find data structure: need 2 array so space be O(N)
            root[i] = i;
            rank[i] = 1;
        }        
        // create a new sorted graph that contains the well edge and pipes edges
        List<int[]> sortedgraph = new ArrayList<>(); // build the sorted graph(wells + pipes edges) time: O(N + M)-> N: # of house, M: # of pipes 
                                                     // list space: O(N + M)
        // add wells edges
        for(int i = 0; i < wells.length; i++){
            sortedgraph.add(new int[]{0, i + 1, wells[i]});
        }
        // add pipes edges
        for(int i = 0; i < pipes.length; i++){
            sortedgraph.add(pipes[i]);
        }
        // sort all edges by their cost in ascending order
        Collections.sort(sortedgraph, (a, b) -> a[2] - b[2]); // sort the sorted graph time : O((N + M)* log(N + M))
                                                              // Collections.sort() space: O(log n), n is # of elements
        int totalcost = 0;
        // iterate all edges in sorted graph and use union to connect 2 vertex and calculate minimum cost
        for(int[] edge : sortedgraph){// this for loop iterate union() N + M time so total time is (N + M) * α(n)
            if(union(edge[0], edge[1])){ // union() time: α(n) -> inverse ackerman
                totalcost += edge[2];
            }
        }        
        return totalcost;
        // total time: O((N + M) * log(N + M)) dominated by sort function
        // total space: O(N + M) dominated by sorted graph list, contain wells + pipes edges        
    }

}
