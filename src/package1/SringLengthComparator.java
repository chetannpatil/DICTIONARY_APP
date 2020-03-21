package package1;

import java.util.Comparator;

public class SringLengthComparator implements Comparator<String>{

	@Override
	public int compare(String s1, String s2) {
		
		if(s1.length() != s2.length())
		return s1.length() - s2.length();
		else
			return s1.compareToIgnoreCase(s2);
	}

}
