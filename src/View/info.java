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

public class info {

    @FXML
    private Button okbuttom;
    
    private Stage stage;
    
    //go to UserName page

    @FXML
    void OkButton(ActionEvent event) throws IOException {
    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/UserName.fxml"));
          Parent root = loader.load();
          stage.setTitle("homePage");
          stage.setScene(new Scene(root,800,650));
          stage.show();
          root.requestFocus();
    	
    }
    
    //go to homePage
    @FXML
    void Home(ActionEvent event) throws IOException {
    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/homepage.fxml"));
          Parent root = loader.load();
          stage.setTitle("homePage");
          stage.setScene(new Scene(root,800,650));
          stage.show();
          root.requestFocus();
    	
    }

}