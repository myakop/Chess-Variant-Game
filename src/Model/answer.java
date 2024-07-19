package Model;


public class answer {
	
	private String text;
	private boolean isTrue;
	
	/* constructor && getters && setters method */
	public answer(String text, boolean isTrue) {
		super();
		this.text = text;
		this.isTrue = isTrue;
	}
	
	
	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public boolean isTrue() {
		return isTrue;
	}


	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}
	
	
	
	
	
}
