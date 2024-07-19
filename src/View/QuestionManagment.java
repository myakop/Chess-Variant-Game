package View;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class QuestionManagment {

    @FXML
    private Button back;

    @FXML
    private Button easyButton;

    @FXML
    private Button hardButton;

    @FXML
    private Button mediumButton;
    
    private Stage stage;
    
    public static  int questNumStr=0;


  //back click
    @FXML
    void back(ActionEvent event) throws IOException {
    	
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/homepage.fxml"));
        Parent root = loader.load();
        stage.setTitle("knight's Move");
        stage.setScene(new Scene(root));
        stage.show();
        root.requestFocus();

    }

    // go to page view the easy questions
    @FXML
    void updateEasyQuestion(ActionEvent event) throws IOException {
    	
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/QuestionViewEasy.fxml"));
        Parent root = loader.load();
        stage.setTitle("knight's Move");
        stage.setScene(new Scene(root));
        stage.show();
        root.requestFocus();


    }

 // go to page view the hard questions
    @FXML
    void updateHardQuestion(ActionEvent event) throws IOException {
    	
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/QuestionViewHard.fxml"));
        Parent root = loader.load();
        stage.setTitle("knight's Move");
        stage.setScene(new Scene(root));
        stage.show();
        root.requestFocus();

    }

 // go to page view the medium questions
    @FXML
    void updateMediumQuestion(ActionEvent event) throws IOException {
    	
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/QuestionViewMed.fxml"));
        Parent root = loader.load();
        stage.setTitle("knight's Move");
        stage.setScene(new Scene(root));
        stage.show();
        root.requestFocus();

    }

}
