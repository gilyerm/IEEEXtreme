package RecXor;

import java.util.Scanner;
public class Solution
{
	static long X,Y,n,d1,d2;
	static long d1_x, d1_y, d2_x, d2_y;

	static Scanner scan = new Scanner(System.in);
	public static void main (String[] args) {
		int tests = scan.nextInt();
		for (int t=0; t<tests; t++)
		{
			getAllInputs();
			long whole = xOR_Range(n, X*(Y)); // xor the entire square

			long unwant = 0;
			long len = (d2_x-d1_x)+1;
			for (long y=d1_y; y<=d2_y; y++) {	// f.e. unwanted 'line'
				long first = ((y*X)+d1_x)+n;
				long unLine_xOR = xOR_Range(first,len);	// calc. it
				unwant ^= unLine_xOR;		// xor them all together
			}

			System.out.println(whole^unwant);	// print relevant part
		}
	}

	public static void getAllInputs() {
		X = scan.nextLong();    // .
		Y = scan.nextLong();    // .
		n = scan.nextLong();    // .
		d1 = scan.nextLong();   // .
		d2 = scan.nextLong();   // get all prarameters

		d1_y = (d1-n)/(X);	// .
		d1_x = (d1-n)%(X);	// .
		d2_y = (d2-n)/(X);	// .
		d2_x = (d2-n)%(X);	// calc. unwanted corners

		if (d1_y>d2_y)	// ensure all axis are ordered
			d1_y = d1_y^d2_y ^ (d2_y=d1_y);	// swap
		if (d1_x>d2_x)
			d1_x = d1_x^d2_x ^ (d2_x=d1_x);	// swap
	}

	static long xOR_Range(long n, long len) {
		long xOR = 0;
		long last = n+len-1;
		if (n%2==1) {	// initial N is odd
			long skipTo = (((len/4)-1)*4)+n+1;	// jump to closest N
			xOR = n;
			for (long i=skipTo; i<=last; i++)	// xOR remaining numbers
				xOR ^= i;
		}
		else {    // initial N is even
			long par = 1 - last % 2;  // flipped parity
			xOR += ((last - par) % 4 == (n+1) % 4) ? 1:0; // 1-or-0 of prev/cur
			xOR += last * par;	// PLUS, last value (according to parity)
		}
		return xOR;
	}
}
