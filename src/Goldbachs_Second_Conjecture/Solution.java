package Goldbachs_Second_Conjecture;

import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Solution {

	public static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println(ThrdConj());
	}

	static BigInteger TWO = BigInteger.valueOf(2);
	static int DEG = 10;

	public static String ThrdConj() {
		BigInteger n = scan.nextBigInteger();
		BigInteger p1;

		BigInteger deltaEven = TWO;
		do {
			deltaEven = deltaEven.add(TWO);
			p1 = n.subtract(deltaEven);
		} while (!p1.isProbablePrime(DEG));

		return p1+" "+SecConj(deltaEven);
	}

	public static String SecConj(BigInteger even) {
		if (even.equals(BigInteger.valueOf(4)))
			return "2 2";

		BigInteger p2,p3;

		p2 = BigInteger.ONE;
		do {
			p2 = p2.add(TWO);
			p3 = even.subtract(p2);
		} while (!(p2.isProbablePrime(DEG) && p3.isProbablePrime(DEG)));

		return p2+" "+p3;
	}

}
