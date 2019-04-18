package Shortening_in_the_Real_World;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		String base = scan.nextLine();
		int tests = scan.nextInt();
		scan.nextLine();
		for (int i=0; i<tests; i++) {
			String url = scan.nextLine();
			int start,end;
			// assume base.len < url.len
			start = url.length() % base.length();
			end = 8-start;
			int lastBase = base.length(), lastURL=url.length();
			String subBase =
					base.substring(lastBase-end, lastBase) + base.substring(0,start);
			String subURL = url.substring(lastURL-8, lastURL);
			byte[] bytesBase = subBase.getBytes(Charset.forName("UTF-8")),
					bytesURL =  subURL.getBytes(Charset.forName("UTF-8"));

//			System.out.println(subBase + Arrays.toString(bytesBase));
//			System.out.println(subURL + Arrays.toString(bytesURL));

			long base10Val = 0;
			for (int j=0; j<7; j++) {
				byte b = (byte) (bytesBase[j] ^ bytesURL[j]);
//				System.out.printf("%02x,",b);
				base10Val += b;
				base10Val <<= 8;
			}
			byte b = (byte) (bytesBase[7] ^ bytesURL[7]);
			base10Val += b;
//			System.out.println("\n"+base10Val);

//			System.out.println(alphabet.length()+" "+alphabet);
			StringBuilder sb = new StringBuilder();
			while (base10Val != 0) {
				int mod = Integer.parseInt(base10Val % 62 + "");
				char digit = alphabet.charAt(mod);
				sb.append(digit);
				base10Val /= 62l;
			}

			System.out.printf("%s/%s\n", base, sb.reverse().toString());
		}

	}
}

// http://www.ieee.comhttp://
// http://www.ieee.org/xtreme

// http://www.ieee.comhttp://www.ieee.comhttp://www.ieee.comhttp://www.ieee.comhttp://www.ieee.com
// http://www.ieee.org/membership_services/membership/young_professionals/index.html

// http://www.ieee.commhttp://www.ieee.commhttp://www.ieee.commhttp://www.ieee.commh
// http://www.ieee.org/membership_services/membership/young_professionals/index.html


// http://giladsawsomewesite.com
// http://gil.hi
