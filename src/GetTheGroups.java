import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// "static void main" must be defined in a public class.
public class GetTheGroups {
	int[] root;
    int[] rank;
    
    public int find(int x){
        if(root[x] == x){
            return x;
        }
        return root[x] = find(root[x]);
    }
    
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
    
    
    public int getGroup(int n , int friend1, int friend2, List<String> queryType, List<List<Integer>> adjlist){
        root = new int[n + 1];
        rank = new int[n + 1];
        
        for(int i = 1; i <= n; i++){
            root[i] = i;
            rank[i] = 1;
        }
        
        int totalpeople = 0;
        for(String q : queryType){            
            if(q == "Friend"){
                if(union(friend1, friend2)){
                    adjlist.get(friend1).add(friend2);
                    adjlist.get(friend2).add(friend1);
                }
                
            }else if(q == "Total"){

                int size1 = adjlist.get(friend1).size();
                if(size1 == 0){
                    size1 = 1;
                }
                for(int i : adjlist.get(friend1)){
                    size1 += adjlist.get(i).size();
                }
                
                int size2 = adjlist.get(friend2).size();
                if(size2 == 0){
                    size2 = 1;
                }
                for(int i : adjlist.get(friend2)){
                    size2 += adjlist.get(i).size();
                }
                totalpeople = size1 + size2;
            }
        }        
        return totalpeople;    
        
    }
    
    
    public static void main(String[] args) {

        List<List<Integer>> adjlist = new ArrayList<>();
        
        for(int i = 0; i <= 4; i++) {
        	adjlist.add(new ArrayList<>());
        }

        GetTheGroups m = new GetTheGroups();
        
        List<String> queryType1 = Arrays.asList("Friend");
        List<String> queryType2 = Arrays.asList("Total");
        
        int n = 4; // total four people (from 1 to 4)
        
        m.getGroup(n, 1, 2, queryType1, adjlist); // query: Friend -> connect 1 and 2
        m.getGroup(n, 2, 3, queryType1, adjlist); // query: Friend -> connect 2 and 3

        System.out.println(m.getGroup(n, 1, 4, queryType2, adjlist)); 
        //query: Total -> return group 1 size + group 4 size  = 3 + 1 = 4
        
        
//          m.getGroup(3, 1, 2, queryType1, adjlist);         
//          System.out.println(m.getGroup(3, 2, 3, queryType2, adjlist));

        
    }
}

