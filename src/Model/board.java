package Model;

import Utils.squareType;

public class board {
	
	private square[][] gameBoard = new square[8][8];
	
	

	/* constructor && getters && setters method */

	public board() {

		super();
		
		
	}
	public square[][] getGameBoard() {
		return gameBoard;
	}


	public void setGameBoard(square[][] gameBoard) {
		this.gameBoard = gameBoard;
	}
	
	public void bulidBoardGame() {
	  for(int i=0 ; i<8 ; i++) {
	    	   for(int j=0 ; j<8 ; j++) {
	    		   square a = new square(false,squareType.Regular,"red");  
	    		   this.gameBoard[i][j] = a ;
	    	   }
	    	
	    	   }
	  }
	}


