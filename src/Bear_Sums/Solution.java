package Bear_Sums;

import java.util.HashSet;
import java.util.Scanner;

public class Solution {
	static Scanner scan = new Scanner(System.in);
	public static void main (String[] args) {
		int tests = scan.nextInt();
		for (int t=0; t<tests; t++) {
			HashSet<Integer> set = new HashSet();
			int S = scan.nextInt();
			int E = scan.nextInt();
			Integer found = null;
			for (int e=0; e<E; e++) {
				int val = scan.nextInt();
				if (found == null) {
					if (set.contains(val))
						found = val;
					else
						set.add(S-val);
				}
			}
			if (found == null)
				System.out.println("!OK");
			else {
				int v1 = found;
				int v2 = S-found;
				if (v1 > v2)  v1 = v1^v2 ^ (v2=v1);
				System.out.printf("%d %d\n", v1, v2);
			}
		}
	}
}
