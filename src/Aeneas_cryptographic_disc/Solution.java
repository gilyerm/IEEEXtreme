package Aeneas_cryptographic_disc;
import java.util.*;
import java.lang.*;

public class Solution   // Please name your class Main
{
	static Disc disc;
	static double r;
	static char[] messageArr;

	static Scanner scan = new Scanner(System.in);
	public static void main (String[] args) {
		getAllInputs();
		ArrayList<Point> path = tracePointPath(messageArr);
		double distance = totalThreadDistance(path);
		long result = (long) Math.ceil(distance);
		System.out.println(result);
	}

	public static void getAllInputs() {
		disc = new Disc();
		r = scan.nextDouble();
		for (int i=0; i<26; i++)
			disc.setPoint(
				scan.next().charAt(0),	// letter to set
				new Point(r, scan.nextDouble())	// at point(r,angle)
			);
		StringBuilder sb = new StringBuilder();
		while(scan.hasNext()) sb.append(scan.next());
		messageArr = filterMessageLetters(sb.toString());
	}

	public static char[] filterMessageLetters(String message) {
		return message
			.replaceAll("[^a-zA-Z]", "") // only alphabetical chars
			.replaceAll("(.)\\1+", "$1") // no consecutive repeating chars
			.toUpperCase() 		// all letters to upper case
			.toCharArray();		// keep as char array
	}

	static ArrayList<Point> tracePointPath(char[] letters) {
		ArrayList<Point> path = new ArrayList<>();
		path.add(Point.CENTER);
		for (char l : letters)
			path.add(disc.getPoint(l));
		return path;
	}

	public static double totalThreadDistance(ArrayList<Point> path) {
		double distance = 0;
		for (int i=0; i<path.size()-1; i++)
			distance += path.get(i).distance(path.get(i+1));
		return distance;
	}

	static class Disc
	{
		Point[] abcPoints = new Point[26];
		public void setPoint(char letter, Point point) {
			abcPoints[index(letter)] = point;
		}
		public Point getPoint(char letter) {
			return abcPoints[index(letter)];
		}
		public int index(char letter){
			return letter - 'A';
		}
	}

	static class Point
	{
		double x,y;
		Point(double r, double angle) {
			double rad = Math.toRadians(angle);
			this.x = Math.cos(rad) * r;
			this.y = Math.sin(rad) * r;
		}
		public static Point CENTER = new Point(0,0);
		public double distance(Point p) {
			return Math.hypot( Math.abs(this.x - p.x), Math.abs(this.y - p.y) );
		}
	}

}
