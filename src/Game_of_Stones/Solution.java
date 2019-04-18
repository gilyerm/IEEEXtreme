package Game_of_Stones;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Solution {

	public static String[] players = {"Alice","Bob"};
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int tests = scan.nextInt();
		for (int t=0; t<tests; t++) {
			int turn = 0;  // alice
			BigInteger games = scan.nextBigInteger();
			for (BigInteger g=BigInteger.ZERO; g.compareTo(games) < 0; g = g.add(BigInteger.ONE)) {
				BigInteger piles = scan.nextBigInteger();
				for (BigInteger p=BigInteger.ZERO; p.compareTo(piles) < 0; p = p.add(BigInteger.ONE)) {
					BigInteger stones = scan.nextBigInteger();    //System.out.print(stones+" ");
					BigInteger mod = stones.mod(BigInteger.valueOf(4));
					if (mod.compareTo(BigInteger.valueOf(3)) == 0) // good 4 current player
						turn = 1 - turn; // opponent "gets" next turn
                /*    else if (mod.compareTo(BigInteger.valueOf(1)) == 0) // good 4 the opponent
                        ; // you keep your turn.
                    else
                        System.out.println("error - even number input");    */
				}
			}
			int winner = 1 - turn;
			System.out.println(players[winner]);
			//System.out.printf("game winner: %s\n", players[winner]);
		}
	}
}
