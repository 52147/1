
public class RemovingStarsFromaString {

	public String removeStars(String s) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		int sblength = 0;
		while (i < s.length()) {

			if (sb.isEmpty() || s.charAt(i) != '*') {
				sb.append(s.charAt(i));
				sblength++;
			} else if (!sb.isEmpty() && sb.charAt(sblength - 1) != '*' && s.charAt(i) == '*') {
				sb.deleteCharAt(sblength - 1);
				sblength--;
			}
			i++;
		}

		return sb.toString();
	}

}
