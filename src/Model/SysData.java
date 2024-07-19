package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




public class SysData {
	
	private static SysData SysData;
	private  ArrayList<question> Easyquestions;
	private  ArrayList<question> Mediumquestions;
	private  ArrayList<question> Hardquestions;
	public ArrayList<question> allquestion;
	public HashSet<Game> games;
	
	private static final String QuestionsFormat = "src/QuestionsFormat.json";

	
	//singleton class
	public static SysData getInstance() {
		if (SysData == null) 
			SysData = new SysData();  
		return SysData;
	}

	/* constructor && getters && setters method */
	public SysData() {
		super();
		Easyquestions =  new ArrayList<question>();
		Mediumquestions = new ArrayList<question>();
		Hardquestions = new ArrayList<question>();
	    allquestion = new ArrayList<question>();
	    games = new HashSet<Game>();
	    
	}

	public ArrayList<question> getEasyquestions() {
		return Easyquestions;
	}

	public void setEasyquestions(ArrayList<question> easyquestions) {
		Easyquestions = easyquestions;
	}

	public ArrayList<question> getMediumquestions() {
		return Mediumquestions;
	}

	public void setMediumquestions(ArrayList<question> mediumquestions) {
		Mediumquestions = mediumquestions;
	}

	public ArrayList<question> getHardquestions() {
		return Hardquestions;
	}

	public void setHardquestions(ArrayList<question> hardquestions) {
		Hardquestions = hardquestions;
	}
	
	//update JSON file with the changes
		public boolean updatejsonfile() {
			try {
				JSONParser parser = new JSONParser();

				// get question's JSON file
				FileInputStream fis = new FileInputStream(QuestionsFormat);

				BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
				
				if(allquestion.size() > 1) {
					
				Object obj = parser.parse(reader);
				JSONObject jo = (JSONObject) obj;
				jo.clear();
				
				}
				
				JSONArray JSONquestions = new JSONArray();
				JSONObject toWrite = new JSONObject();
				// go over all questions and add every question to JSON file
				for (question list : allquestion) {
					if (list == null)
						continue;
					// get each question from the ArrayList
						JSONObject ja = new JSONObject();
						// get all answers
						JSONArray answers = new JSONArray();
						for (String a : list.getAnswers()) {
							answers.add(a);
						}
						// put fields in the object
						ja.put("question", list.getContent());
						ja.put("answers", answers);
						ja.put("correct_ans", list.getCorrect_ans());
						ja.put("level", list.getLevel());
						ja.put("team", list.getTeam());
						

						// add the object to json array
						JSONquestions.add(ja);
					}

					// add json array to object question
					toWrite.put("questions", JSONquestions);
				
				// write the JSONObject to .json file
				FileWriter file = new FileWriter(QuestionsFormat);
				file.write(toWrite.toJSONString());
				file.flush();
				System.out.println("Question JSON was saved");

			} catch (IOException e1) {
				// TODO Auto-generated catch block
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
			}
			return true;
		}
		
	
	
	// reading question from JSON 
	@SuppressWarnings("unchecked")
	public boolean loadQuestions()  {

		
		JSONParser parser = new JSONParser();

		try {
			// get question's JSON file
			
			
			FileInputStream fis = new FileInputStream( QuestionsFormat);

			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			Object obj = parser.parse(reader);
			JSONObject jo = (JSONObject) obj;

			// convert question's JSON file to array .
			JSONArray quesArray = (JSONArray) jo.get("questions");

			// iterate over the values (questions).
			Iterator<JSONObject> quesIterator = quesArray.iterator();
			// get the questions data.
			while (quesIterator.hasNext()) {

				JSONObject q = quesIterator.next();
				

				// get question's content
				String content = (String) q.get("question");
				// get question's answers
				JSONArray ans = (JSONArray) q.get("answers");
				ArrayList<String> answers = new  ArrayList<String>();
				for (int i = 0; i < ans.size(); i++) {
					String answercontent = (String) ans.get(i);
					answers.add(answercontent);
					
				}

				// get correct answer's number.
				int correctAnswerNum = Integer.valueOf(q.get("correct_ans").toString());

				// get question's difficulty level.
				String level = (String) q.get("level");

				// get question's created team name.
				String team = (String) q.get("team");
				// create an new object of the question.
				question questionToAdd = new question(content,answers,correctAnswerNum,level,team);
				// Add the question to questions according to the question level.
				if (questionToAdd.getLevel().equals("1")) {
					if(!Easyquestions.contains(questionToAdd) && !allquestion.contains(questionToAdd) ) {
					Easyquestions.add(questionToAdd);
					allquestion.add(questionToAdd);
					}
					

				} 
				if (questionToAdd.getLevel().equals("2")) {
					if(!Mediumquestions.contains(questionToAdd) && !allquestion.contains(questionToAdd) ) {
					Mediumquestions.add(questionToAdd);
					allquestion.add(questionToAdd);
					}

				}
				if (questionToAdd.getLevel().equals("3")) {
					if(!Hardquestions.contains(questionToAdd) && !allquestion.contains(questionToAdd) ) {
					Hardquestions.add(questionToAdd);
					allquestion.add(questionToAdd);
					}

				}

				
			}

		} catch (Exception e) {
			
			
			return false;
		}
		
		return true;
	}
	
	//add new questions method
	public boolean addQuestion(question question) {
		if(allquestion.contains(question)) {
			return false;
		}
		if (question.getLevel().equals("1")) {
			Easyquestions.add(question);
			allquestion.add(question);
			updatejsonfile();
			return true;

		} 
		if (question.getLevel().equals("2")) {
			Mediumquestions.add(question);
			allquestion.add(question);
			updatejsonfile();
			return true;
			

		}
		 if (question.getLevel().equals("3")) {
			 Hardquestions.add(question);
			 allquestion.add(question);
			 updatejsonfile();
			 return true;
			 
		}
		
		return false;
	}
	
	// remove question
	public boolean removeQuestion(question question2) {
		
		
		
		if (question2.getLevel().equals("1")) {
			if(Easyquestions.contains(question2)) {
				Easyquestions.remove(question2);
				 allquestion.remove(question2);
				
				updatejsonfile();
				return true;
			}
		}
				
			

		if (question2.getLevel().equals("2")) {
			if(Mediumquestions.contains(question2)){
				Mediumquestions.remove(question2);
			   allquestion.remove(question2);
			   updatejsonfile();
			   return true;
			}
				}
				
			
		 if (question2.getLevel().equals("3")) { 
			 if(getHardquestions().contains(question2)) {
			
				getHardquestions().remove(question2);
				allquestion.remove(question2);
				updatejsonfile();
				return true;
			
			 }
				
			}
			
		return false;
	}
	
	//edit question
	public boolean editQuestion(question question, question newQuestion) {
		if(addQuestion(newQuestion)) {
			removeQuestion(question);
			updatejsonfile();
			return true;
		}
		return false;

	}
	
	
	//load the history of the games
	public boolean LoadGames() {
		
		
		JSONParser parser = new JSONParser();
		
		try {
			// get games's JSON file
			FileInputStream fis = new FileInputStream("src/gamesHistory.json");
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			Object obj = (Object) parser.parse(reader);
			JSONObject jo = (JSONObject) obj;
			
			// convert Games's JSON file to array.
			JSONArray gamesArray = (JSONArray) jo.get("games");
			
			// iterate over the values (Games).
			if (gamesArray == null)
				return false;
			Iterator<JSONObject> gameIterator = gamesArray.iterator();
			
			// get the Games data.
			while (gameIterator.hasNext()) {
				
				JSONObject g = gameIterator.next();
				
				// get Games content
				
				String nickName = (String) g.get("nickName");
				int points = Integer.valueOf(g.get("points").toString());;
				
				
				Game GameToAdd = new Game(nickName,points);
				for (Game game : games) {
					if(GameToAdd.getNickName().equals(game.getNickName()) && GameToAdd.getPoints() == (game.getPoints()) ) {
						return false;
					}	
				}

					games.add(GameToAdd);
				}
					
				
				
			
			
		} catch (Exception e) {
			
			return false;
		}

		return true;
	}
	
	//update JSON file with the new games
	public boolean saveGame() {
		
		try {
			JSONParser parser = new JSONParser();

			// get games's JSON file
			FileInputStream fis = new FileInputStream("src/gamesHistory.json");

			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			
			if(games.size() > 1) {
				Object obj = parser.parse(reader);
				JSONObject jo = (JSONObject) obj;
				jo.clear();
			}
			
			JSONArray JSONgames = new JSONArray();
			JSONObject toWrite = new JSONObject();
			
			
			
			// go over all games and add every game to json file
			for (Game list : games) {
				if (list == null)
					continue;
				// get each games from the ArrayList
					JSONObject ja = new JSONObject();
					
					
					// put fields in the object
					ja.put("nickName", list.getNickName());
					ja.put("points", list.getPoints());
				
					// add the object to json array
					JSONgames.add(ja);
				}

				// add json array to object game
				toWrite.put("games", JSONgames);
			
			// write the JSONObject to .json file
			FileWriter file = new FileWriter("src/gamesHistory.json");
			file.write(toWrite.toJSONString());
			file.flush();
			System.out.println("Question JSON was saved");

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}
	
	//add new game
	public boolean addGame(Game newGame) {
		if(!games.contains(newGame)) {
			games.add(newGame);
			saveGame();
		}
		
		return true;
	}	
	
	public boolean removeGame(Game newGame) {
		if(games.contains(newGame)) {
			games.remove(newGame);
			saveGame();
		}
		
		return true;
	}
	
}
