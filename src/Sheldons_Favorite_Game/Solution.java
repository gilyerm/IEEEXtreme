package Sheldons_Favorite_Game;

import java.math.BigInteger;
import java.util.*;

public class Solution {

	enum Result{
		Lose,Tied,Win
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

		public String shortname() {
			Move m =this;
			switch (m) {
				case Rock: return "Ro";
				case Paper: return "Pa";
				case Scissors: return "Sc";
				case Spock: return "Sp";
				case Lizard: return "Li";
			}
			return null;
		}


		@Override
		public String toString() {
			return this.getName();
		}
	}

	public static Result calcWinner(Move me,Move op){
		if (me.equals(op)) return Result.Tied;

		if (op.loseTo().contains(me)) return Result.Win;
		return Result.Lose;
//        switch (me) {
//            case Rock:{
//                if (op.equals(Move.Scissors)||op.equals(Move.Lizard))
//                    return Result.Win;
//                return Result.Lose;
//            }
//            case Paper:{
//                if (op.equals(Move.Rock)||op.equals(Move.Spock))
//                    return Result.Win;
//                return Result.Lose;
//            }
//            case Scissors:{
//                if (op.equals(Move.Paper)||op.equals(Move.Lizard))
//                    return Result.Win;
//                return Result.Lose;
//            }
//            case Spock:{
//                if (op.equals(Move.Scissors)||op.equals(Move.Rock))
//                    return Result.Win;
//                return Result.Lose;
//            }
//            case Lizard:{
//                if (op.equals(Move.Spock)||op.equals(Move.Paper))
//                    return Result.Win;
//                return Result.Lose;
//            }
//        }
//        return null;
	}


	public static Move BobLogic(Move lastMove,Result result){
		if (!lastMove.equals(Move.Spock)) return Move.Spock;
		switch (result){
			case Win:
				return Move.Rock;
			case Tied:
				return Move.Lizard;
			case Lose:
				return Move.Paper;
		}
		return null;
	}

	public static Move AliceLogic(Move lastMove,Result result,Move opMove){
		if (result.equals(Result.Win)) return lastMove;
		List<Move> beatmoves =null;
		switch (result) {
			case Win:
				return lastMove;
			case Tied:{
				beatmoves = lastMove.loseTo();
				break;
			}
			case Lose:{
				beatmoves = opMove.loseTo();
			}
		}
		Result result1 = calcWinner(beatmoves.get(0), beatmoves.get(1));
		if (result1.equals(Result.Win)) return beatmoves.get(0);
		else return beatmoves.get(1);
	}

	public static void printwin(BigInteger alicewin,BigInteger bobwin,BigInteger ties){
		if (bobwin.compareTo(alicewin)<0)
			System.out.printf("%s wins, by winning %d game(s) and tying %d game(s)\n","Alice",alicewin,ties);
		else if (alicewin.compareTo(bobwin)<0)
			System.out.printf("%s wins, by winning %d game(s) and tying %d game(s)\n","Bob",bobwin,ties);
		else
			System.out.printf("Alice and Bob tie, each winning %d game(s) and tying %d game(s)\n",bobwin,ties);
	}

	public static void main(String[] args) {

		HashMap<String, Move> hashMap = Move.getHashMap();

//        List<String> nodes=new ArrayList<>();
//        Map<String,String> Edges=new HashMap<>();
//        for (Move a : hashMap.values()) {
//            for (Move b : hashMap.values()) {
//                nodes.add("("+a.shortname()+","+b.shortname()+")");
//            }
//        }

		Scanner scanner=new Scanner(System.in);
		int tests=scanner.nextInt();
		for (int j = 0; j < tests; j++) {
			Move alice=hashMap.get(scanner.next());
			Move bob=hashMap.get(scanner.next());
			BigInteger turns = scanner.nextBigInteger();
			BigInteger alicewin=BigInteger.ZERO,bobwin=BigInteger.ZERO,ties=BigInteger.ZERO;
			for (BigInteger i = BigInteger.ZERO; i.compareTo(turns) < 0; i= i.add(BigInteger.ONE)) {
				if (alice.equals(Move.Lizard)&&(bob.equals(Move.Paper)||bob.equals(Move.Spock))){
					alicewin= alicewin.add(turns.add(i.negate()));
					break;
				}
//                System.out.print(i+":\t");
				Result resulta = calcWinner(alice, bob);
				Result resultb = null;
				switch (resulta) {
					case Win:{
						alicewin= alicewin.add(BigInteger.ONE);
						resultb=Result.Lose;
						break;
					}
					case Tied:{
						ties= ties.add(BigInteger.ONE);
						resultb=Result.Tied;
						break;
					}
					case Lose:{
						bobwin= bobwin.add(BigInteger.ONE);
						resultb=Result.Win;
						break;
					}
				}


				String s=("("+alice.shortname()+","+bob.shortname()+")");

				alice = AliceLogic(alice,resulta,bob);
				bob =BobLogic(bob,resultb);
//                System.out.println(s);
				if (alice.equals(Move.Paper)&&bob.equals(Move.Paper)){
					BigInteger left = turns.add(i.negate());
					BigInteger mod = left.mod(BigInteger.valueOf(4));
					BigInteger circle = left.add(mod.negate());
					BigInteger divide = circle.divide(BigInteger.valueOf(4));
					alicewin= alicewin.add(divide);
					bobwin= bobwin.add(divide);
					bobwin= bobwin.add(divide);
					ties= ties.add(divide);
					i=turns.add(mod.negate());
				}

//                Edges.put(s,"("+alice.shortname()+","+bob.shortname()+")");
////                System.out.println("("+alice.shortname()+","+bob.shortname()+")");
//                System.out.println(s+"="+"("+alice.shortname()+","+bob.shortname()+")"+"="+resulta.name());
			}
			printwin(alicewin,bobwin,ties);
		}
	}
}