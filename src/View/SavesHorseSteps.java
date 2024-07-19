package View;

public class SavesHorseSteps {
	
	
	int x ;
	int y;
	int pointsChanges;
	boolean isVisited;
	
	//for saving last steps of the horse for forget square
	public SavesHorseSteps(int x, int y, int pointsChanges, boolean isVisited) {
		super();
		this.x = x;
		this.y = y;
		this.pointsChanges = pointsChanges;
		this.isVisited = isVisited;
	}
	

}
