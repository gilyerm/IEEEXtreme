package Running_Up_Stairs;	// Don't place your source in a package

import java.math.BigInteger;
import java.util.Scanner;
import java.lang.*;

public class Solution	// Please name your class Main
{
	private static BigInteger fib(int place) {
		BigInteger a = new BigInteger("0");
		BigInteger b = new BigInteger("1");
		BigInteger t;
		while (place-- > 1) {
			t = b;
			b = a.add(b);
			a = t;
		}
		return b;
	}

	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int i=0;i<t;i++){
			int n = in.nextInt();
			System.out.println(fib(n+1));
		}
	}
}