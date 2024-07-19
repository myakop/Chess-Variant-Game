package View;

import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import Model.Game;
import Model.SysData;
import Model.question;
import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class questionPopUp implements Initializable {

    @FXML
    private Button answer;
  

    @FXML
    private RadioButton answerA;

    @FXML
    private RadioButton answerB;

    @FXML
    private RadioButton answerC;

    @FXML
    private RadioButton answerD;

    @FXML
    private ToggleGroup group1;

    @FXML
    private ToggleGroup group11;
    @FXML
    private Label asnwerCheck;
    @FXML
    AnchorPane scenePane;
    Stage stage;
    String incorrect = "src\\Sounds\\incorrectAnswer.wav";
    String correct = "src\\Sounds\\correctAnswer.wav";
    @FXML
    private TextField questionArea;
    Main main = new Main();
    Random s = new Random();
	int x = s.nextInt(7);
	
	GamePage Game = GamePage.getInstance();
	 
	
    
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	SysData.getInstance().loadQuestions();
    	Random s = new Random();
    	int x = s.nextInt(SysData.getInstance().getEasyquestions().size()-1);
    	int x2 = s.nextInt(SysData.getInstance().getMediumquestions().size()-1);
    	int x3 = s.nextInt(SysData.getInstance().getHardquestions().size()-1);
    	
    	//set the question content by a level 
    	
    	if(main.imageToSave.equals("easy")) {
    		
    		main.questionToSave = SysData.getInstance().getEasyquestions().get(x);
    		questionArea.setText(SysData.getInstance().getEasyquestions().get(x).getContent());
    		answerA.setText(SysData.getInstance().getEasyquestions().get(x).getAnswers().get(0));
    		answerB.setText(SysData.getInstance().getEasyquestions().get(x).getAnswers().get(1));
    		answerC.setText(SysData.getInstance().getEasyquestions().get(x).getAnswers().get(2));
    		answerD.setText(SysData.getInstance().getEasyquestions().get(x).getAnswers().get(3));
    		
    		
    	}
    	if(main.imageToSave.equals("med")) {
    		main.questionToSave = SysData.getInstance().getMediumquestions().get(x2);
    		questionArea.setText(SysData.getInstance().getMediumquestions().get(x2).getContent());
    		answerA.setText(SysData.getInstance().getMediumquestions().get(x2).getAnswers().get(0));
    		answerB.setText(SysData.getInstance().getMediumquestions().get(x2).getAnswers().get(1));
    		answerC.setText(SysData.getInstance().getMediumquestions().get(x2).getAnswers().get(2));
    		answerD.setText(SysData.getInstance().getMediumquestions().get(x2).getAnswers().get(3));
    		
    	}
    	if(main.imageToSave.equals("hard")) {
    		main.questionToSave = SysData.getInstance().getHardquestions().get(x3);
    		questionArea.setText(SysData.getInstance().getHardquestions().get(x3).getContent());
    		answerA.setText(SysData.getInstance().getHardquestions().get(x3).getAnswers().get(0));
    		answerB.setText(SysData.getInstance().getHardquestions().get(x3).getAnswers().get(1));
    		answerC.setText(SysData.getInstance().getHardquestions().get(x3).getAnswers().get(2));
    		answerD.setText(SysData.getInstance().getHardquestions().get(x3).getAnswers().get(3));
    	}
    	
    
	}
    
    //update the points by the answer of the player
    @FXML
    void  checkAnswer(ActionEvent event) {
    	 if(!answerA.isSelected() && !answerB.isSelected() && !answerC.isSelected() && !answerD.isSelected()) {
    		   Alert alert = new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("false");
    			alert.setContentText("You Must choose one answer");
    			alert.showAndWait();
    			return;
    		   
    	   }
    	 if(checkanswerCorrect() == true) {
    		 PlayHitSound(correct);
    		 Alert alert = new Alert(AlertType.CONFIRMATION);
	    		alert.setTitle("true");
	    		alert.setContentText("congratulations your answer is true");
	    		alert.showAndWait();
	    		
    		 if(main.imageToSave.equals("easy")) {
    			 Game.updatePoints2(1);
    			 Game.savequestionpoints = 1;
    			 
    		 }
    		 if(main.imageToSave.equals("med")) {
    			 Game.updatePoints2(2);
    			 Game.savequestionpoints = 2;
    		 }
    		 if(main.imageToSave.equals("hard")) {
    			 Game.updatePoints2(3);
    			 Game.savequestionpoints = 3;
    		 }
    	 }
    	 else {
    		 PlayHitSound(incorrect);
    		 Alert alert = new Alert(AlertType.CONFIRMATION);
	    		alert.setTitle("false");
	    		alert.setContentText("Oops your answer is false");
	    		alert.showAndWait();
    		 if(main.imageToSave.equals("easy")) {
    			 Game.updatePoints2(-2);
    			 Game.savequestionpoints = -2;
    		 }
    		 if(main.imageToSave.equals("med")) {
    			 Game.updatePoints2(-3);
    			 Game.savequestionpoints = -3;
    		 }
    		 if(main.imageToSave.equals("hard")) {
    			 Game.updatePoints2(-4);
    			 Game.savequestionpoints = -4;
    		 }
    		 asnwerCheck.setText("baaaay");
    	 }
    	 stage =(Stage)scenePane.getScene().getWindow();
         Game.timeline.play();
         main.score=5;
    	 stage.close();
    

    }
    
    //check if player's answer is true
    Boolean checkanswerCorrect() {
    if(answerA.isSelected() &&  main.questionToSave.getCorrect_ans() == 1) {
		return true;
	}
	if(answerB.isSelected() &&  main.questionToSave.getCorrect_ans() == 2) {
		return true;
	}
	if(answerC.isSelected() &&  main.questionToSave.getCorrect_ans() == 3) {
		return true;
	}
	if(answerD.isSelected() &&  main.questionToSave.getCorrect_ans() == 4) {
		return true;
	}
	return false;
    }
    
    
    private void PlayHitSound(String Path) {
    	
    	Media media = new Media(new File(Path).toURI().toString());
    	MediaPlayer m  = new MediaPlayer(media);
    	m.setCycleCount(1);
    	m.play();
    	
    }

	

}
