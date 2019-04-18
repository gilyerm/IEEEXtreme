package Shortening_in_the_Real_World;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

// https://raw.githubusercontent.com/ieeesbspain/IEEEXtreme/master/challenges/ieeextreme9/P20%20-%20Shortening%20in%20the%20Real%20World/CraftCoders/Solution/Program.cs
public class Solution {

	public static String alphabetBase = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		String baseURL = scan.nextLine();
		int tests = scan.nextInt();
		scan.nextLine();
		for (int i=0; i<tests; i++) {
			String targetURL = scan.nextLine();
			int baseURLLen = baseURL.getBytes().length,
					targetURLLen = targetURL.getBytes().length;

//			byte[] allBytesBaseURL = baseURL.getBytes(Charset.forName("UTF8")),
//				 allBytesTargetURL = targetURL.getBytes(Charset.forName("UTF8"));
			byte[]  allBytesBaseURL = StandardCharsets.UTF_8.encode(baseURL).array(),
					allBytesTargetURL = StandardCharsets.UTF_8.encode(targetURL).array();

			ArrayList<Byte>
					bytesBaseURL = new ArrayList<>(),
					bytesTargetURL = new ArrayList<>();

			if (baseURLLen < targetURLLen) {	// System.out.println("baseURL < tgt");
				int start,end;
				start = targetURLLen % baseURLLen;
				end = 8 - start % 8;

				int baseStart = baseURLLen - end,
						baseEnd = baseURLLen;
				for (int j=baseStart; j<baseEnd; j++)
					bytesBaseURL.add(allBytesBaseURL[j]);
				for (int j=0; j<start; j++)
					bytesBaseURL.add(allBytesBaseURL[j]);

				int targetStart = targetURLLen - 8,
						targetEnd = targetURLLen;
				for (int j=targetStart; j<targetEnd; j++)
					bytesTargetURL.add(allBytesTargetURL[j]);
			}
			else {	// System.out.println("baseURL >= tgt");
				int start,end;
				end = targetURLLen;
				start = targetURLLen - 8;
				for (int j=start; j<end; j++) {
					bytesBaseURL.add(allBytesBaseURL[j]);
					bytesTargetURL.add(allBytesTargetURL[j]);
				}
			}

//			System.out.println(subBase + Arrays.toString(bytesBaseURL));
//			System.out.println(subTarget + Arrays.toString(bytesURL));

			BigInteger base10Val = BigInteger.ZERO;
			int j;
			for (j=0; j<7; j++) {
				byte b = (byte) (bytesBaseURL.get(j) ^ bytesTargetURL.get(j));
//				System.out.printf("%02x,",b);
				base10Val = base10Val.add(BigInteger.valueOf(b));
				base10Val = base10Val.shiftLeft(8);
			}
			byte b = (byte) (bytesBaseURL.get(j) ^ bytesTargetURL.get(j));
			base10Val = base10Val.add(BigInteger.valueOf(b));
//			System.out.println("\n"+base10Val);
//			System.out.println(alphabetBase.length()+" "+alphabetBase);

			StringBuilder sb = new StringBuilder();
			while (base10Val.compareTo(BigInteger.ZERO) != 0) {
				int mod = base10Val.mod(BigInteger.valueOf(62)).intValue();
				char digit = alphabetBase.charAt(mod);
				sb.append(digit);
				base10Val = base10Val.divide(BigInteger.valueOf(62));
			}
			if (sb.length() == 0)
				sb.append(0);

			System.out.printf("%s/%s\n", baseURL, sb.reverse().toString());
		}

	}
}
