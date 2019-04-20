package Xtreme_Fake_Coins;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
	static final char[] abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	static Scanner scan = new Scanner(System.in);
	public static void main (String[] args)
	{
		String[] nmLine = scan.nextLine().split(",");
		int N = Integer.parseInt(nmLine[0]);
		int M = Integer.parseInt(nmLine[1]);
		String[][] tests = new String[M][];
		for (int m=0; m<M; m++)
			tests[m] = scan.nextLine().split("-");

		ArrayList<String> coinPairs = generateAllCoinPairs(N);
		for (int i=0; i<coinPairs.size(); i++)
			for (int j=i+1; j<coinPairs.size(); j++) {
				String pair1 = coinPairs.get(i);
				String pair2 = coinPairs.get(j);
				if (canPairsBeDistinguished(tests, new String[]{pair1,pair2}))
					System.out.printf("%s=%s\n",pair1,pair2);
			}
	}

	public static boolean canPairsBeDistinguished(String[][] tests, String[] pairs) {
		if (tests.length == 0)
			return true;
		int[] fstCount = lrBalance(tests, pairs[0]);
		for (int i=1; i < pairs.length; i++)
			if (!areArraysConsistent(tests.length, fstCount, lrBalance(tests, pairs[i])))
				return false;
		return true;
	}

	enum sides {LEFT, RIGHT}
	public static int[] lrBalance(String[][] tests, String pair) {
		int[] balance = new int[tests.length];
		for (int t=0; t<tests.length; t++)
			for (char c : pair.toCharArray()) {
				if (tests[t][sides.LEFT.ordinal()].contains(""+c))
					balance[t]++;
				if (tests[t][sides.RIGHT.ordinal()].contains(""+c))
					balance[t]--;
			}
		return balance;
	}

	public static boolean areArraysConsistent(int length, int[] count1, int[] count2) {
		for (int t=0; t < length; t++) {
			if (count1[t]<0  && !(count2[t]<0))  return false;
			if (count1[t]>0  && !(count2[t]>0))  return false;
			if (count1[t]==0 && !(count2[t]==0)) return false;
		}
		return true;
	}

	public static ArrayList<String> generateAllCoinPairs(int Len) {
		ArrayList<String> pairs = new ArrayList<>((int)((Len-1)*((double)Len)/2.0));
		for (int i=0; i<Len; i++)
			for (int j=i+1; j<Len; j++)
				pairs.add(""+ abc[i]+ abc[j]);
		return pairs;
	}
}