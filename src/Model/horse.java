package Model;

public class horse {
	
	private square[][] place = new square[1][1];

	/* constructor && getters && setters method */
	public horse(square[][] place) {
		super();
		this.setPlace(place);
	}

	public square[][] getPlace() {
		return place;
	}

	public void setPlace(square[][] place) {
		this.place = place;
	}
	

}
