import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Sign_In_System {
	// implement 3 functions:
	// 1. register:
	//    input: "register <username> <password>"
	//    output:
	//       if user already exists
	//       return "User already exists"
	//       otherwise
	//       return "Register successfully"
	// 2. Login:
	//    input: "login <username> <password>"
	//    output:
	//       if username is in system and math with it's password and user not already logged in
	//       then return "Logged In Successfully"
	//       otherwise
	//          return "Login Unsuccessful"
	// 3. Logout:
	//    input: "logout <username>"
	//    output:
	//       if user current logged in, return "Logged out Successful"
	//       otherwise return "Logout Unsuccessful"
	// Approach : HashMap + HashSet

	public static List<String> implementAPI(List<String> logs) {

		List<String> res = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>(); // map<username, password>
		HashSet<String> set = new HashSet<>(); // contain login username
		for (String s : logs) {
			// split by white space and convert to string array to get data
			String[] split = s.split("\\s+");

			String function = split[0];
			String username = split[1];
			String password = split[2];

			if (function.equals("register")) {
				if (map.containsKey(username)) {
					res.add("Username already exists");
				} else {
					res.add("Registered Successfully");
					map.put(username, password);
				}
			} else if (function.equals("login")) {
				if (map.containsKey(username)) {
					if (set.contains(username)) {
						res.add("Login Unsuccessful");
					} else {
						if (map.get(username).equals(password)) {
							res.add("Logged In Successfully");
							set.add(username);
						} else {
							res.add("Login Unsuccessful");
						}
					}
				} else {
					res.add("Login Unsuccessful");
				}

			} else { // log out function
				if (map.containsKey(username)) {
					if (set.contains(username)) {
						res.add("Logged out Successfully");
						set.remove(username);
					} else {
						res.add("Logout Unsuccessful");
					}
				} else {
					res.add("Logout Unsuccessful");
				}
			}

		}
		return res;

	}

}
