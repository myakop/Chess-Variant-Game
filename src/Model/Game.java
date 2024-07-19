package Model;

import java.sql.Time;
import java.util.ArrayList;


public class Game {
	
	private player player;
	private horse horse;
	private king king;
	private queen queen;
	private board board;
	private ArrayList<question> questions;
	private int points;
	private String phase;
	private String nickName;
	private Time timer;
	
	/* constructor && getters && setters method */
	public Game(Model.player player, Model.horse horse, Model.king king, Model.queen queen, Model.board board,
			ArrayList<question> questions, int points, String phase, String nickName) {
		super();
		this.player = player;
		this.horse = horse;
		this.king = king;
		this.queen = queen;
		this.board = board;
		this.questions = questions;
		this.points = points;
		this.phase = phase;
		this.nickName = nickName;
		
	}
	
	
	/* constructor && getters && setters method */
	public Game( int points, String nickName) {
		super();
		this.points = points;
		this.nickName = nickName;
	}
		
	
	

	public Time getTimer() {
		return timer;
	}

	public void setTimer(Time timer) {
		this.timer = timer;
	}

	public Game(String nickName, int points) {
		super();
		this.points = points;
		
		this.nickName = nickName;
		
	}

	public player getPlayer() {
		return player;
	}

	public void setPlayer(player player) {
		this.player = player;
	}

	public horse getHorse() {
		return horse;
	}

	public void setHorse(horse horse) {
		this.horse = horse;
	}

	public king getKing() {
		return king;
	}

	public void setKing(king king) {
		this.king = king;
	}

	public queen getQueen() {
		return queen;
	}

	public void setQueen(queen queen) {
		this.queen = queen;
	}

	public board getBoard() {
		return board;
	}

	public void setBoard(board board) {
		this.board = board;
	}

	public ArrayList<question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<question> questions) {
		this.questions = questions;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "Game [points=" + points + ", nickName=" + nickName + "]";
	}

	public static Object getInstance() {
		// TODO Auto-generated method stub
		return null;
	}



	
	


	
	
}

