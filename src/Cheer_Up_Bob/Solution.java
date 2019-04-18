package Cheer_Up_Bob;
import java.util.Scanner;

public class Solution {

	static class MoveClass{
		int count = 0;
		String move = "";
	}

	public static void main (String[] args) {
		int[][] moves= new int[9][2];
		Character[][] ttt = new Character[3][3];
		Scanner in = new Scanner(System.in);
		for(int i=0;i<9;i++){
			moves[i][0]=in.nextInt()-1;
			moves[i][1]=in.nextInt()-1;
		}
		System.out.println(calc(moves,ttt).move);
	}

	public static int[] bobNextMove(int[][] moves,Character[][] ttt){
		for(int i=0;i<9;i++){
			if(ttt[moves[i][0]][moves[i][1]]==null){
				ttt[moves[i][0]][moves[i][1]]='X';
				return moves[i];
			}
		}
		return null;
	}

	public static MoveClass calc(int[][] moves,Character[][] ttt){
		int[] bm = bobNextMove(moves,ttt);
		if(isWin(ttt,'X')){
			ttt[bm[0]][bm[1]] =null;
			return new MoveClass();
		}
		MoveClass min = new MoveClass();
		min.count=9999;
		for(int i=0;i<9;i++){
			if(ttt[i/3][i%3]!=null)
				continue;
			ttt[i/3][i%3] ='O';
			if(isWin(ttt,'O')){
				ttt[i/3][i%3] =null;
				continue;
			}
			MoveClass mc =calc(moves,ttt);
			mc.move = ""+((i/3)+1)+" "+((i%3)+1)+"\n"+mc.move;
			min =min.count<=mc.count? min :mc;
			ttt[i/3][i%3] =null;
		}
		ttt[bm[0]][bm[1]] =null;
		min.count = min.count+1;
		return min;

	}

	public static boolean isWin(Character[][] ttt,Character ch){
		for(int i=0;i<3;i++){
			if(ttt[i][0]==ch&&ttt[i][1]==ch&&ttt[i][2]==ch)
				return true;
			if(ttt[0][i]==ch&&ttt[1][i]==ch&&ttt[2][i]==ch)
				return true;
		}
		if(ttt[0][0]==ch&&ttt[1][1]==ch&&ttt[2][2]==ch)
			return true;
		if(ttt[2][0]==ch&&ttt[1][1]==ch&&ttt[0][2]==ch)
			return true;
		return false;
	}
}
