package Shortening_in_the_Real_World;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {

	public static final char[] alphabetBase =
			"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	public static final BigInteger BaseSize = BigInteger.valueOf(alphabetBase.length);
	static Scanner scan = new Scanner(System.in);

	public static void main(String... args) {
		String baseURL = scan.nextLine();
		byte[] baseURLArr = asByteArr(baseURL);
		int tests = scan.nextInt(); scan.nextLine();
		for (int t = 0; t < tests; t++) {
			byte[] targetURLArr = asByteArr(scan.nextLine());
			logArr("B_inp", baseURLArr);
			logArr("T_inp", targetURLArr);
			byte[] xor = xORUrls(baseURLArr, targetURLArr);
			System.out.printf("%s/%s\n", baseURL, toBase62(xor));
		}
	}

	public static byte[] asByteArr(String str) {
		return str.getBytes(Charset.forName("UTF8"));
	}

	public static byte[] xORUrls(byte[] baseURLArr, byte[] targetURLArr) {
		byte[] xor = new byte[8];
		int last;

		if (baseURLArr.length < targetURLArr.length) {	// IF baseURL < targetUrl (base shorter than target)
			byte[] repeatBase = Arrays.copyOf(baseURLArr, targetURLArr.length);
			for (int i=baseURLArr.length; i<repeatBase.length; i++)
				repeatBase[i] = baseURLArr[i%baseURLArr.length]; // repeat baseURL as needed until lengths equal.
			baseURLArr = repeatBase;
		}

		last = targetURLArr.length;	// "truncate"/limit baseURL to the targetUrl length.
		byte[] target8 = Arrays.copyOfRange(targetURLArr, last-8, last);
		byte[] base8 = Arrays.copyOfRange(baseURLArr, last-8, last);
		for (int i=0; i<8; i++) {	// xor the 2 8-byte ranges
			xor[i] = (byte) (target8[i] ^ base8[i]);
		}

		logThing("--------");
		logThing(String.format("f=%d, l=%d", last-8, last));
		logArr("B_full", baseURLArr);
		logArr("T_full", targetURLArr);

		logThing("--------");
		logArr("B_8", base8);
		logArr("T_8", target8);

		return xor;
	}

	public static void logArr(String title, byte[] arr) {
		logThing(String.format("%s %s (%d)", title, ArrayToString(arr), arr.length));
	}
	public static void logThing(String s) {
		// System.out.println(s);
	}
	public static String ArrayToString(byte[] arr) {
		ArrayList<Byte> arrayList = new ArrayList<>();
		for (byte b : arr) arrayList.add(b);
		String list = arrayList.stream()
				.map(b -> String.format("%03d",(int)(b)))
				.collect(Collectors.joining(", "));
		return String.format("[%s]",list);
	}

	public static String toBase62(byte[] xor) {
		BigInteger base10Val = new BigInteger(xor);
		StringBuilder sb = new StringBuilder();
		while (base10Val.compareTo(BigInteger.ZERO) != 0) {
			int mod = base10Val.mod(BaseSize).intValue();
			char digit = alphabetBase[mod];
			sb.append(digit);
			base10Val = base10Val.divide(BaseSize);
		}
		if (sb.length() == 0) sb.append(0);
		return sb.reverse().toString();
	}
}
