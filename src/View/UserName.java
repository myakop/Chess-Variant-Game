package View;

import java.io.IOException;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class UserName {

	@FXML
	private Button backbuttom;

	@FXML
	private Button goStartGame;

	@FXML
	private TextField namefield;
	
	@FXML
    private Button infopage;

	private Stage stage;
	
	Main main = new Main();

	//back home
	@FXML
	void backbutto(ActionEvent event) throws IOException {
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/homepage.fxml"));
		Parent root = loader.load();
		stage.setTitle("Knight's Move");
		stage.setScene(new Scene(root,800,650));
		stage.show();
		root.requestFocus();


	}


     //show the rules of the game
	@FXML
	void infopage(ActionEvent event) throws IOException {
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/info.fxml"));
        Parent root = loader.load();
        
        stage.setTitle("gamehistory");
        stage.setScene(new Scene(root,800,650));
        stage.show();
        root.requestFocus();
	}

	
    //go to the game page
	@FXML
	void goStartGame(ActionEvent event) throws IOException {
		
		if(namefield.getText().isEmpty() || namefield.getText().trim().isEmpty()) {
			
			Alert aler = new Alert(AlertType.WARNING);
			aler.setHeaderText("Please enter your name/nickName!");
			aler.showAndWait();
			
		}
		else {
			main.nickName = namefield.getText(); //save the nickname that the user choose
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/GamePage.fxml"));
			Parent root = loader.load();
			stage.setTitle("gameinfo");
			stage.setScene(new Scene(root,800,650));
			stage.show();
			root.requestFocus();
			
		}


	}

}
