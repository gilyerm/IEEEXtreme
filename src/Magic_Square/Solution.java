package Magic_Square;
import java.util.*;

public class Solution {

	public static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {

		int N = scan.nextInt();
		int key=0, anti=0;
		int[] rows = new int[N];
		int[] cols = new int[N];

		for (int r=0; r<N; r++)
			for (int c=0; c<N; c++) {
				int num = scan.nextInt();
				rows[r] += num;
				cols[c] += num;
				if (r==c) key += num;
				if (r==N-1-c) anti += num;
			}

		ArrayList<Integer> miss = new ArrayList<>();
		for (int c=N; c>0; c--)
			if (cols[c-1] != key) miss.add(-c);
		if (anti != key) miss.add(0);
		for (int r=1; r<=N; r++)
			if (rows[r-1] != key) miss.add(r);

		System.out.println(miss.size());
		miss.stream().forEach(System.out::println);
	}

}
