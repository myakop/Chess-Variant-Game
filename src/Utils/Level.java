package Utils;

public enum Level {
	/**difficult difficulty level*/
	HARD ("3"),
	/** medium difficulty level*/
	MEDIUM ("2"),
	/** easy difficulty level*/
	EASY ("1");
	
	/**for debugging purposes.*/
	private String level;
	
	Level(String level) {
		this.level = level;
	}
	
	@Override
	public String toString() {
		return level;
	}

}
