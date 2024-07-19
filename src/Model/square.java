package Model;

import Utils.squareType;
import javafx.scene.paint.Color;

public class square {
	
	private boolean isVisited ;
	private squareType type;
	private String color;
	

	/* constructor && getters && setters method */
	public square(boolean isVisited, squareType type, String color) {

		super();
		this.isVisited = isVisited;
		this.type = type;
		this.color = color;
	}


	public boolean isVisited() {
		return isVisited;
	}


	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}


	public squareType getType() {
		return type;
	}


	public void setType(squareType type) {
		this.type = type;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}
	
	
	


}
