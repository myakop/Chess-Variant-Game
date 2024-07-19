package Model;

import java.util.ArrayList;

public class question {
	 
	private  int questionID;
	static int counter=1;
	private String level;
	private String content;
	private ArrayList<String> answers;
	private String team;
	private int correct_ans;
	
	/* constructor && getters && setters method */
	public question( String content, ArrayList<String> answers,int correct_ans, String level, String team) {
		super();
		this.level = level;
		this.content = content;
		this.answers = answers;
		this.team = team;
		this.correct_ans = correct_ans;
	}
	
	public question( String content, ArrayList<String> answers,int correct_ans, String team) {
		super();
		this.content = content;
		this.answers = answers;
		this.team = team;
		this.correct_ans = correct_ans;
	}
	public question( String content,int correct_ans, String level, String team) {
		super();
		this.level = level;
		this.content = content;
		this.answers = new ArrayList<String>();
		this.team = team;
		this.correct_ans = correct_ans;
	}
	
	@Override
	public boolean equals(Object obj) {
		if( obj == null || !(  obj instanceof question) ) {
			return false;
		}
		question other = (question) obj;
		return this.content.equals(other.content);
	}
	
	public int getQuestionID() {
		return questionID;
	}
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ArrayList<String> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public int getCorrect_ans() {
		return correct_ans;
	}
	public void setCorrect_ans(int correct_ans) {
		this.correct_ans = correct_ans;
	}
	@Override
	public String toString() {
		return "question , level=" + level + ", content=" + content + ", answers="
				+ answers + ", team=" + team + ", correct_ans=" + correct_ans + "]";
	}
	
	public boolean addAnswer(String answer){
		
		if(answer!=null && !this.answers.contains(answer)){
			this.answers.add(answer);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean removeAnswer(String answerRemove){
		
		if(answerRemove!=null && this.answers.contains(answerRemove)){
			this.answers.remove(answerRemove);
			return true;
		}
		return false;
	}

	
	
	
	
	

}
