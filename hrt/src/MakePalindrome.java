import java.util.*;


public class MakePalindrome {
	
	public int solution(String inputStr) {
		int[] map = new int[26];
		for (int i = 0; i < inputStr.length(); i++) {
			char c = inputStr.charAt(i);
			map[c - 'a']++;
		}

		int odd = 0;
		int even = 0;

		for (int i = 0; i < 26; i++) {
			if (map[i] % 2 == 0) {
				even++;
			} else {
				odd++;
			}
		}

		if (odd == 0) {
			return 27;
		} else if (odd == 1) {
			//add zero letter or add the same single letter 
			return 2;
		} else if (odd == 2) {
			//add the single letter with 2 choices
			return 2;
		} else {
			return 0;
		}
	}


	public static void main(String[] args) {
		String test1 = "aabb";
		String test2 = "abbb";
		MakePalindrome t1 = new MakePalindrome();
		int r1 = t1.solution(test1); //expect to be 27
		System.out.println(r1);
		int r2 = t1.solution(test2); //expect to be 2
		System.out.println(r2);

	}
}