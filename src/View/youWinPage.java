package View;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.Main;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class youWinPage implements  Initializable{

    @FXML
    private Button exit;

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button playagain;
    
    private Stage stage;
    
    @FXML
    private ImageView cup , win;
    Main main = new Main();
    @FXML
    private Label message;
    String wingame = "src\\Sounds\\winGame.mp3";

    @FXML
    void Exit(ActionEvent event) throws IOException {
    	
    	((Stage) exit.getScene().getWindow()).close();
    	

    }

    //go to the home page
    @FXML
    void MainMenuAction(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/homePage.fxml"));
        Parent root = loader.load();
        stage.setTitle("gameinfo");
        stage.setScene(new Scene(root));
        stage.show();
        root.requestFocus();

    }

  //go to play again
    @FXML
    void playAgain(ActionEvent event) throws IOException {
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
		PlayHitSound(wingame);
		
		//check if deserve a cup
		if(main.finalScore > 200) {
			try {
				showLabel(win);
				showLabel(cup);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		// TODO Auto-generated method stub
	}
	
	//if the player win show the cup for him
	  public void showLabel(ImageView Image) throws IOException {
	    	TranslateTransition translate = new TranslateTransition();
			translate.setNode(Image);
			translate.setDuration(Duration.millis(3000));
			if(Image ==  win) {
				translate.setByX(200);
				translate.play();	
			}
			else {
				System.out.println("faat");
	    		translate.setByX(450);
			translate.play();
			}
	    	
	    }
	  private void PlayHitSound(String Path) {
			
			Media media = new Media(new File(Path).toURI().toString());
			MediaPlayer m  = new MediaPlayer(media);
			m.setCycleCount(1);
			m.play();
			
		}

}

