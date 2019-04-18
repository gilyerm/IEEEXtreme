package Sheldons_Favorite_Game;

import java.math.BigInteger;
import java.util.*;

public class Solution {

	enum Result{
		Lose,Tied,Win;
		Result oponentsResult() {
			switch (this) {
				case Lose:    return Win;
				case Tied:    return Tied;
				case Win:     return Lose;
				default:      return null;   // should never happen
			}
		}
	}
	enum Move{
		Rock("Rock"), Paper("Paper"), Scissors("Scissors"), Lizard("Lizard"), Spock("Spock");
		private static HashMap<String,Move> hashMap=null;

		String name;
		Move(String move) {
			this.name =move;
		}
		public String getName() {
			return name;
		}

		public static HashMap<String, Move> getHashMap() {
			if (hashMap==null){
				hashMap=new HashMap<>();
				hashMap.put(Rock.name,Rock);
				hashMap.put(Paper.name,Paper);
				hashMap.put(Scissors.name,Scissors);
				hashMap.put(Lizard.name,Lizard);
				hashMap.put(Spock.name,Spock);
			}
			return hashMap;
		}
		public List<Move> loseTo(){
			Move move=this;
			ArrayList<Move> moves=new ArrayList<>();
			switch (move) {
				case Rock:{
					moves.add(Move.Paper);
					moves.add(Move.Spock);
					break;
				}
				case Paper:{
					moves.add(Move.Lizard);
					moves.add(Move.Scissors);
					break;
				}
				case Scissors:{
					moves.add(Move.Rock);
					moves.add(Move.Spock);
					break;
				}
				case Spock:{
					moves.add(Move.Lizard);
					moves.add(Move.Paper);
					break;
				}
				case Lizard:{
					moves.add(Move.Scissors);
					moves.add(Move.Rock);
					break;
				}
			}
			return moves;
		}

		@Override
		public String toString() {
			return this.getName();
		}
	}
	static BigInteger TWO = BigInteger.valueOf(2);

	public static Result calcWinner(Move me,Move op){
		if (me.equals(op)) return Result.Tied;
		if (op.loseTo().contains(me)) return Result.Win;
		return Result.Lose;
	}

	public static void printGameWinner(){
		BigInteger alicewin, bobwin, ties;
		alicewin = getResultCount(Result.Win);
		bobwin = getResultCount(Result.Lose);
		ties = getResultCount(Result.Tied);
		if (bobwin.compareTo(alicewin)<0)
			System.out.printf("%s wins, by winning %d game(s) and tying %d game(s)\n","Alice",alicewin,ties);
		else if (alicewin.compareTo(bobwin)<0)
			System.out.printf("%s wins, by winning %d game(s) and tying %d game(s)\n","Bob",bobwin,ties);
		else
			System.out.printf("Alice and Bob tie, each winning %d game(s) and tying %d game(s)\n",bobwin,ties);
	}

	public static Move BobLogic(Move lastMove,Result result){
		if (!lastMove.equals(Move.Spock)) return Move.Spock;
		switch (result){
			case Win:      return Move.Rock;
			case Tied:     return Move.Lizard;
			case Lose:     return Move.Paper;
		}
		return null; // should not happen
	}
	public static Move AliceLogic(Move lastMove,Result result,Move opMove){
		if (result.equals(Result.Win)) return lastMove;
		List<Move> beatMoves = null;
		switch (result) {
			case Win: {
				return lastMove;
			}
			case Tied:{
				beatMoves = lastMove.loseTo();
				break;
			}
			case Lose:{
				beatMoves = opMove.loseTo();
			}
		}
		Result moveThatWins = calcWinner(beatMoves.get(0), beatMoves.get(1));
		if (moveThatWins.equals(Result.Win))
			return beatMoves.get(0);
		else return beatMoves.get(1);
	}

	public static BigInteger[] alicesCount;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int tests=scanner.nextInt();
		for (int game = 0; game < tests; game++)
		{
			Move alice = Move.getHashMap().get(scanner.next());
			Move bob = Move.getHashMap().get(scanner.next());
			BigInteger turns = scanner.nextBigInteger();

			alicesCount = new BigInteger[Result.values().length];    // zero the board
			for (BigInteger play = BigInteger.ZERO; play.compareTo(turns) < 0; play=play.add(BigInteger.ONE))
			{
				if (alice.equals(Move.Lizard) && (bob.equals(Move.Paper) || bob.equals(Move.Spock))) {
					updateResults(Result.Win, turns.add(play.negate()));
					break;
				}
				Result resultA = calcWinner(alice, bob);
				Result resultB = updateResults(resultA, BigInteger.ONE); // update & get results

				alice = AliceLogic(alice,resultA,bob);    // iterate
				bob = BobLogic(bob,resultB);

				if (alice.equals(Move.Paper) && bob.equals(Move.Paper)) {
					BigInteger left = turns.add(play.negate());
					BigInteger mod = left.mod(BigInteger.valueOf(4));
					BigInteger circle = left.add(mod.negate());
					BigInteger divide = circle.divide(BigInteger.valueOf(4));
					updateResults(Result.Win, divide);
					updateResults(Result.Tied, divide);
					updateResults(Result.Lose, divide.multiply(TWO));
					play=turns.add(mod.negate());
				}
			}

			printGameWinner();
		}
	}

	public static Result updateResults(Result res, BigInteger toAdd) {
		setResultCount(res, getResultCount(res).add(toAdd));
		return res.oponentsResult();
	}

	public static BigInteger getResultCount(Result res) {
		if (alicesCount[res.ordinal()] == null)
			setResultCount(res, BigInteger.ZERO);
		return alicesCount[res.ordinal()];
	}
	public static void setResultCount(Result res, BigInteger val) {
		alicesCount[res.ordinal()] = val;
	}
}
