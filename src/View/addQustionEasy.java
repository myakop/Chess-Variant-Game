package View;

import java.io.IOException;

import Model.SysData;
import Model.question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class addQustionEasy { 

	@FXML
	private TextField Qustion;

	@FXML
	private Button addBu;

	@FXML
	private Button bavkButton;

	@FXML
	private TextField correctAnswer;

	@FXML
	private TextField txfQA1;

	@FXML
	private TextField txfQA2;

	@FXML
	private TextField txfQA3;

	@FXML
	private TextField txfQA4;
	int correctAnswer2 = -1;


	public void closeWindow() {
		((Stage) addBu.getScene().getWindow()).close();
	}

	public void back(ActionEvent event) throws Exception {
		closeWindow();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/QuestionViewEasy.fxml"));
		Scene scene = new Scene(root, 800, 650);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Knight's Move");
		primaryStage.show();

	}

	//add an easy question
	@FXML
	void addQuestion(ActionEvent event) throws IOException {

		String content = Qustion.getText();
		String a1 = txfQA1.getText();
		String a2 = txfQA2.getText(); 
		String a3 = txfQA3.getText();
		String a4 = txfQA4.getText();
		String ca = correctAnswer.getText();
		
		//check if there are empty fields

		if (content.isEmpty() || txfQA1.getText().isEmpty() || txfQA2.getText().isEmpty() || txfQA3.getText().isEmpty()
				|| txfQA4.getText().isEmpty() || ca.isEmpty()) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("missing fields");
			alert.setContentText("You must fill all the fields!"); 
			alert.show();
			return;

		}
		if (content.trim().isEmpty() || txfQA1.getText().trim().isEmpty() || txfQA2.getText().trim().isEmpty() || txfQA3.getText().trim().isEmpty()
				|| txfQA4.getText().trim().isEmpty()) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("missing fields");
			alert.setContentText("You must fill all the fields!");
			alert.show();
			return;

		} 
		try {
				correctAnswer2 = Integer.parseInt(ca);
			}catch(NumberFormatException ex) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Invalid correct answer");
				alert.setContentText("Correct answer can be 1 to 4"); //check if the correct answer 1-4
				alert.show();
				return;
			}
		if(correctAnswer2 < 1 || correctAnswer2 > 4) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid correct answer");
			alert.setContentText("Correct answer can be 1 to 4");
			alert.show();
			return;
		}
			
			
			question q = new question(content, correctAnswer2 , "1", "Tiger");
			
			//check if the answers are different

			if (q.getAnswers().contains(a1) || q.getAnswers().contains(a2) || q.getAnswers().contains(a3)
					|| q.getAnswers().contains(a4)) {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("same answer");
				alert.setContentText("You must enter different answers!");
				alert.show();
				return;
			} else {

				if (!q.addAnswer(a1) || !q.addAnswer(a2) || !q.addAnswer(a3) || !q.addAnswer(a4)) {
					q.getAnswers().clear();
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("same answer");
					alert.setContentText("You must enter different answers!");
					alert.show();
					return;

				}
				boolean isAdded = SysData.getInstance().addQuestion(q);
				if (!isAdded) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("same question exists");
					alert.setContentText("You must enter different question!");
					alert.show();
					return;

				} else {
					closeWindow();
					Stage primaryStage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("/View/QuestionViewEasy.fxml"));
					Scene scene = new Scene(root, 800, 650);
					primaryStage.setScene(scene);
					primaryStage.setTitle("questions");
					primaryStage.show();
				}
			}
		}

	}


