package Drawing_Rooted_Binary_Trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//class Main {	// Please name your class Main
public class Solution {

	static Scanner scan = new Scanner(System.in);
	public static void main (String[] args) throws Exception {
		while (scan.hasNext()) {
			String infix = scan.nextLine();
			String prefix = scan.nextLine();
			Node treeRoot = recreateTree(infix, prefix);
			printAsciiTree(treeRoot); // System.out.println();
		}
	}

	static class Node {
		char data;
		Node left, right;
		public Node(char data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
		@Override public String toString() {
			return String.format("{%c} [%s,%s]",this.data,
					this.left == null ? "-" : this.left.data,
					this.right == null ? "-" : this.right.data);
		}
	}

	static Node recreateTree(String infix, String prefix) throws Exception
	{
		char localRoot = prefix.charAt(0);
		int rootOnInfix = infix.indexOf(localRoot);
		if (rootOnInfix == -1)
			throw new Exception("prefix root not found in infix");

		Node left=null, right=null;
		String leftInfix = infix.substring(0, rootOnInfix); // "lllRrrr" where 'R' is at rootOnInfix
		String leftPrefix = prefix.substring(1,1+leftInfix.length()); // "+1" = w/o root, to lenOf("lll")
		if (leftInfix.length() != 0)
			left = recreateTree(leftInfix, leftPrefix);

		String rightInfix = infix.substring(rootOnInfix + 1);// see above, "+1" to skip 'R', to endOfString.
		String rightPrefix = prefix.substring(1 + leftInfix.length());// rest of prefix w/o left&'R'...
		if (rightInfix.length() != 0)
			right = recreateTree(rightInfix, rightPrefix);

		return new Node(localRoot, left, right);
	}

	static void printAsciiTree(Node tree) {
		makeLines(tree).stream()
				.map(StringBuilder::toString)
				.forEach(System.out::println);
	}

	static final char PAD_CHAR = ' ';
	static ArrayList<StringBuilder> makeLines(Node tree)
	{
		if (tree == null) return new ArrayList<>();	// sanity

		ArrayList<StringBuilder> leftsLines = makeLines(tree.left);	// recurse left
		ArrayList<StringBuilder> rightsLines = makeLines(tree.right); // recurse right
		padElementsFromLeft(rightsLines); // shift everything on the right one space over (because root)

		ArrayList<StringBuilder> allLines = new ArrayList<>();
		int maxLeftLen = findLongestLine(leftsLines);
		String leftMargin = repeatChar(maxLeftLen);
		allLines.add(0, new StringBuilder()
				.append(leftMargin).append(tree.data) );	// set current/top line

		int maxLinesCount = Math.max(leftsLines.size(), rightsLines.size());
		leftsLines = normalizeListLength(leftsLines, rightsLines.size(), leftMargin);
		normalizeLinesElements(leftsLines.subList(0,rightsLines.size()), maxLeftLen); // by existing right lines
		rightsLines = normalizeListLength(rightsLines, maxLinesCount, "");

		for (int i = 0; i < maxLinesCount; i++)
			allLines.add(i+1,
				leftsLines.get(i).append(rightsLines.get(i)) );

		return allLines;
	}

	static int findLongestLine(ArrayList<StringBuilder> leftsLines) {
		return leftsLines.stream()
				.mapToInt(StringBuilder::length).max().orElse(0);
	}

	static HashMap<Integer, String> repeatsGenerated = new HashMap();
	static String repeatChar(int length) {
		if (length < 0) return "";	// sanity
		if (repeatsGenerated.containsKey(length))
			return repeatsGenerated.get(length);
		String repeat = Stream.generate(() -> String.valueOf(PAD_CHAR))    // infinite stream
				.limit(length).collect(Collectors.joining());
		repeatsGenerated.put(length, repeat);
		return repeat;
	}

	static void normalizeLinesElements(List<StringBuilder> lines, int toLength) {
		lines.forEach(line -> {
			int missingPadding = toLength - line.length();
			line.append(repeatChar(missingPadding));
		});
	}

	static void padElementsFromLeft(ArrayList<StringBuilder> lines) {
		lines.forEach(line -> line.insert(0, PAD_CHAR));
	}

	static ArrayList<StringBuilder> normalizeListLength(ArrayList<StringBuilder> lines, int toLength, String padItem) {
		if (lines == null) lines = new ArrayList<>(toLength);
		int howManyToAdd = toLength - lines.size();
		if (howManyToAdd < 0) return lines;
		List<StringBuilder> linesToAdd = Stream.generate(() -> new StringBuilder(padItem))
				.limit(howManyToAdd).collect(Collectors.toList());
		lines.addAll(linesToAdd);
		return lines;
	}
}
