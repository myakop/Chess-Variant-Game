package View;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Game;

import Model.SysData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class gameHistory implements Initializable{
	
	@FXML
    private TableView<Game> tbData;

    @FXML
    private TableColumn<Game, Integer> points;

    @FXML
    private TableColumn<Game,String> nickName;
    private Stage stage;
    @FXML
    private Button back;
    
    ObservableList<Game> list =  FXCollections.observableArrayList();

 //back click
@FXML
void back(ActionEvent event) throws IOException {
	((Stage) back.getScene().getWindow()).close();
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/homepage.fxml"));
      Parent root = loader.load();
      stage.setTitle("gamehistory");
      stage.setScene(new Scene(root,800,650));
      stage.show();
      root.requestFocus();
      

}

@Override
public void initialize(URL location, ResourceBundle resources) {
	
	SysData.getInstance().LoadGames();
	
	
	list.setAll(SysData.getInstance().games);
	nickName.setCellValueFactory(new PropertyValueFactory<Game,String>("nickName"));
	points.setCellValueFactory(new PropertyValueFactory<Game,Integer>("points"));
	tbData.setItems(list);
	
	
	
	
}
}