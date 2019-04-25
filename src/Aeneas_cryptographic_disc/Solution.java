package Aeneas_cryptographic_disc;

import java.util.*;
import java.lang.*;

// Please name your class Main
public class Solution {
	static Scanner scan = new Scanner(System.in);
	static HashMap<Character, Point> points = new HashMap<>();
	public static void main (String[] args) {
		double r = scan.nextDouble();
		System.out.println(r);
		for (int i=0; i<26; i++) {
			Character letter = scan.next().charAt(0);
			double angle = scan.nextDouble();
			System.out.printf("%c %f\n", letter, angle);
			points.put(letter, new Point(r, angle));
		}

		points.entrySet().stream().forEach(System.out::println);

		StringBuilder sb = new StringBuilder();
		while(scan.hasNext()) sb.append(scan.next());
		String message = sb.toString().replaceAll("[^a-zA-Z]", "").toUpperCase();
		System.out.println(message);
		System.out.println(Math.ceil(calcThreadLength(message)));
	}

	static double calcThreadLength(String message) {
		// double length = 0;
		char[] arr = message.toCharArray();
		ArrayList<Point> path = new ArrayList<>();
		path.add(Point.CENTER);
		char prev='\0';
		for (int i=0; i < arr.length; prev=arr[i], i++) {
			char cur = arr[i];
			if (prev == cur)
				path.add(Point.CENTER);
			path.add(points.get(cur));
		}
// 		path.add(Aeneas_cryptographic_disc.Point.CENTER);
		return totalDistance(path);
	}

	public static double totalDistance(ArrayList<Point> path) {
		double distance = 0;
		for (int i=0; i<path.size()-1; i++)
			distance += path.get(i).distance(path.get(i+1));
		return distance;
	}

}

class Point {
	double x,y;
	Point(double r, double angle) {
		this.x = Math.cos(angle) * r;
		this.y = Math.sin(angle) * r;
	}
	public static Point CENTER = new Point(0,0);
	public double distance(Point p) {
		return Math.hypot(Math.abs(this.x - p.x), Math.abs(this.y - p.y));
	}
	@Override
	public String toString() {
		return String.format("{x=%d\t,y=%d}",x,y);
	}
}
