package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import Model.SysData;
import Model.question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


 
public class editQuestionEasy implements Initializable  {

    @FXML
    private TextField Qustion;

    @FXML
    private Button backButton;

    @FXML
    private TextField correctAnswer;

    @FXML
    private TextField level;

    @FXML
    private TextField txfQA1;

    @FXML
    private TextField txfQA2;

    @FXML
    private TextField txfQA3;

    @FXML
    private TextField txfQA4;
    
    private int currect;
    
	public void closeWindow() {
		((Stage) backButton.getScene().getWindow()).close();
	}

    @FXML
    void back(ActionEvent event) throws IOException {
     	    closeWindow();
    		Stage primaryStage = new Stage();
    		Parent root = FXMLLoader.load(getClass().getResource("/View/QuestionViewEasy.fxml"));
    		Scene scene = new Scene(root,800,650);
    		primaryStage.setScene(scene);
    		primaryStage.setTitle("Hamka Game");
    		primaryStage.show();
    		closeWindow();

    }

  //edit an easy question
    @FXML
    void save(ActionEvent event) throws IOException {
    	
    	String ques = Qustion.getText();
		String answer1 = txfQA1.getText();
		String answer2 = txfQA2.getText();
		String answer3 = txfQA3.getText();
		String answer4 = txfQA4.getText();
		String curr_answer = correctAnswer.getText();
		String Level =level.getText();
		int level2;
	
		//check if there are empty fields
		
		if(ques.isEmpty() || txfQA1.getText().isEmpty() || txfQA2.getText().isEmpty() || txfQA3.getText().isEmpty() || txfQA4.getText().isEmpty()
				|| curr_answer.isEmpty()) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("missing fields");
			alert.setContentText("You must fill all the fields!");
			alert.show();
			
		}
		if (ques.trim().isEmpty() || txfQA1.getText().trim().isEmpty() || txfQA2.getText().trim().isEmpty() || txfQA3.getText().trim().isEmpty()
				|| txfQA4.getText().trim().isEmpty()) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("missing fields");
			alert.setContentText("You must fill all the fields!");
			alert.show();
			return;

		} 
		try{
			currect=Integer.parseInt(curr_answer);
		}catch(NumberFormatException ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid correct answer");
			alert.setContentText("Correct answer can be 1 to 4"); //check if the correct answer 1-4
			alert.show();
			return;
		}
		 if(currect<1 || currect>4) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Details");
			alert.setContentText("The Correct Answer value rang is (1-4) \n");
			alert.show();
			return;
		}
			try{
				level2=Integer.parseInt(Level);
			}catch(NumberFormatException ex) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Invalid correct answer");
				alert.setContentText("Correct answer can be 1 to 4");
				alert.show();
				return;
			}
			 if(level2<1 || level2>3) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Details");
				alert.setContentText("The Level value rang is (1-3) \n");
				alert.show();
				return;
			}
		 
		
			question q=new question(ques, currect, Level , "Tiger");
			
			//check if the answers are different
			
			if(q.getAnswers().contains(answer1) || q.getAnswers().contains(answer2) || 
					q.getAnswers().contains(answer3) || q.getAnswers().contains(answer4)){
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("same answer");
				alert.setContentText("You must enter different answers!"); 
				alert.show();
				return;
				
			}else {
				
				
				if (!q.addAnswer(answer1) || !q.addAnswer(answer2) || !q.addAnswer(answer3) || !q.addAnswer(answer4)) {
					q.getAnswers().clear();
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("same answer");
					alert.setContentText("You must enter different answers!");
					alert.show();
					return;

				}
				
				System.out.println(QuestionViewEasy.editQ1.getContent());
				//SysData.getInstance().editQuestion(QuestionViewEasy.editQ,q );
				SysData.getInstance().removeQuestion(QuestionViewEasy.editQ1);
				SysData.getInstance().addQuestion(q);
				((Stage) backButton.getScene().getWindow()).close();
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/View/QuestionViewEasy.fxml"));
				Scene scene = new Scene(root, 800,650);
				primaryStage.setScene(scene);
				primaryStage.setTitle("questions");
				primaryStage.show();
			}
			
	
		}
			
		

    



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		question q = QuestionViewEasy.editQ1;
		Qustion.setText(q.getContent());
		System.out.println(Qustion.getText());
		txfQA1.setText(q.getAnswers().get(0));
		txfQA2.setText(q.getAnswers().get(1));
		txfQA3.setText(q.getAnswers().get(2));
		txfQA4.setText(q.getAnswers().get(3));
		correctAnswer.setText(Integer.toString(q.getCorrect_ans()));
		level.setText(q.getLevel());
		
	}

}
