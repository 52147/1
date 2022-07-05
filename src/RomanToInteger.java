import java.util.HashMap;

// 13. Roman To Integer
// convert the roman to integer
// given a string of roman symbol
// return vlue of this roman symbol string
// there is one char roman symbol and two char roman symbol
// approach : HashMap <String, Integer> <roman symbol, its value>

public class RomanToInteger {

	// map<roman symbol(one and two), symbol value>
	static HashMap<String, Integer> map = new HashMap<>();

	// space : O(1) only contains char [I, V, X, L, C, D, M]
	static {

		// one char roman symbol
		map.put("I", 1);
		map.put("V", 5);
		map.put("X", 10);
		map.put("L", 50);
		map.put("C", 100);
		map.put("D", 500);
		map.put("M", 1000);

		// two char roman symbol
		map.put("IV", 4);
		map.put("IX", 9);
		map.put("XL", 40);
		map.put("XC", 90);
		map.put("CD", 400);
		map.put("CM", 900);

	}

	public int romanToInt(String s) {

		// i for record index
		int i = 0;
		// ans for record total sum
		int ans = 0;

		// time : O(1) roman value ranger[1, 3999]
		// while loop iterate the whole string
		while (i < s.length()) {

			// if there is space for 2 symbol
			if (i < s.length() - 1) {
				// get the two symbor from string s
				String two = s.substring(i, i + 2);
				// check if map contains these two symbol
				if (map.containsKey(two)) {
					// if map contains, get the vlue from map and add it in ans
					ans += map.get(two);
					// update index with 2
					i += 2;
					// jump to next iteration becaus do need to process these symbol again
					continue;
				}
			}

			// otherwise there is one symbol in the map
			// get the value of these one symbol from stirng s by using substring()
			String one = s.substring(i, i + 1);
			// get this one symbol value from map and add it in ans
			ans += map.get(one);
			// update the index of string s by one 
			i += 1;

		}

		return ans;
	}

}
