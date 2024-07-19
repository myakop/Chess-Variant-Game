package View;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class youLosePage implements Initializable {

    @FXML
    private Button exit;
    @FXML
    private Label message;

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button playagain;
    String losegame = "src\\Sounds\\loseGame.wav";
    private Stage stage;
    Main main = new Main();

    //go to home page
    @FXML
    void MainMenuAction(ActionEvent event) throws IOException {
    	
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/homepage.fxml"));
        Parent root = loader.load();
        stage.setTitle("gameinfo");
        stage.setScene(new Scene(root));
        stage.show();
        root.requestFocus();

    }

    //exit from the game
    @FXML
    void exit(ActionEvent event) throws IOException {
    	((Stage) exit.getScene().getWindow()).close();



    }

    //go to play again
    @FXML
    void play(ActionEvent event) throws IOException {
    	 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/GamePage.fxml"));
         Parent root = loader.load();
         stage.setTitle("gameinfo");
         stage.setScene(new Scene(root));
         stage.show();
         root.requestFocus();


    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		message.setText(main.message); 
		PlayHitSound(losegame);
	}
	 private void PlayHitSound(String Path) {
			
			Media media = new Media(new File(Path).toURI().toString());
			MediaPlayer m  = new MediaPlayer(media);
			m.setCycleCount(1);
			m.play();
			
		}

}
