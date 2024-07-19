package View;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class homePage {

    @FXML
    private Button GameHistoey;

    @FXML
    private Button ManageQuestions;

    @FXML
    private Button StartGame;

    @FXML
    private Image btnImage;

    @FXML
    private Button info;
    
    private Stage stage;

    //open rules of the game
    @FXML
    void getInfo(ActionEvent event) throws IOException {
    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/info.fxml"));
          Parent root = loader.load();
          
          stage.setTitle("gamehistory");
          stage.setScene(new Scene(root,800,650));
          stage.show();
          root.requestFocus();
    }

    //go to page gameHistory
    @FXML
    void goGameHistory(ActionEvent event) throws IOException {
    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/gameHistory.fxml"));
          Parent root = loader.load();
          
          stage.setTitle("gamehistory");
          stage.setScene(new Scene(root,800,650));
          stage.show();
          root.requestFocus();

    }

  //go to page questions management
    @FXML
    void goMG(ActionEvent event) throws IOException {
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/questionManagment.fxml"));
        Parent root = loader.load();
        stage.setTitle("gameinfo");
        stage.setScene(new Scene(root,800,650));
        stage.show();
        root.requestFocus();

    }

  //go to page start game - witch open user name window
    @FXML
    void goStartGame(ActionEvent event) throws IOException {
   
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/UserName.fxml"));
        Parent root = loader.load();
        stage.setTitle("gameinfo");
        stage.setScene(new Scene(root,800,650));
        stage.show();
        root.requestFocus();
    }
        

       


}
