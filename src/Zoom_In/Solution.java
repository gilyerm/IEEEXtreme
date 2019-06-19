package Zoom_In;
import java.util.*;

public class Solution {
	static int rw,cl;
	static HashMap<Character,ArrayList<String>> alpha;
	public static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		cl = scan.nextInt();
		rw = scan.nextInt();
		int abSize = scan.nextInt(); scan.nextLine();
		alpha = new HashMap<>(abSize);
		for (int i=0; i<abSize; i++) {
			char letter = scan.nextLine().charAt(0);
			ArrayList<String> rule = new ArrayList<>(rw);
			for (int j=0; j<rw; j++)
				rule.add(j, scan.nextLine());
			alpha.put(letter,rule);
		}
		int sCount = scan.nextInt(); scan.nextLine();
		for (int i=0; i<sCount; i++) {
			String sentence = scan.nextLine();
			applyRule(sentence).stream()
					.map(StringBuilder::toString)
					.forEach(System.out::println);
		}
	}

	public static ArrayList<StringBuilder> applyRule(String sentence) {
		ArrayList<StringBuilder> after = new ArrayList<>(rw);
		for (int j=0; j<rw; j++)
			after.add(j,new StringBuilder(sentence.length()*rw));
		for (char a : sentence.toCharArray()) {
			ArrayList<String> rule = alpha.get(a);
			for (int j=0; j<rw; j++)
				after.get(j).append(rule.get(j));
		}
		return after;
	}
}