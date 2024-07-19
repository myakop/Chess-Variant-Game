package View;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Model.SysData;
import Model.question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class QuestionViewHard implements Initializable {

    @FXML
    private ListView<question> Hardquestionlistview;

    @FXML
    private Button addbutton;

    @FXML
    private Button bakButton;

    @FXML
    private Button editbutton;

    @FXML
    private Button removebutton;
    
    
	private ArrayList<question> questions;
	SysData sysData = SysData.getInstance();
	private ArrayList<question> Hard = new ArrayList<question>();
	static question editQ3;
	

	public void closeWindow() {
		((Stage) bakButton.getScene().getWindow()).close();
	}
    //go to page add easy question
    @FXML
    void add(ActionEvent event) throws IOException {
    	closeWindow();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/addQuestionHard.fxml"));
		Scene scene = new Scene(root,  800,650);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Knight Move");
		primaryStage.show();
		closeWindow();
    }

    //back
    @FXML
    void back(ActionEvent event) throws IOException {
    	
    	closeWindow();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/questionManagment.fxml"));
		Scene scene = new Scene(root,  800,650);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Knight Move");
		primaryStage.show();
		closeWindow();

    }

    //go to the edit easy questions
    @FXML
    void edit(ActionEvent event) throws IOException {
    	try {
    	   editQ3 = Hardquestionlistview.getSelectionModel().getSelectedItem();
    	   if(editQ3 == null) {
    		   throw new IndexOutOfBoundsException();
    	   }
    	   closeWindow();
   		   Stage primaryStage = new Stage();
   		   Parent root = FXMLLoader.load(getClass().getResource("/View/editQuestionhard.fxml"));
   		   Scene scene = new Scene(root, 800,650);
   		   primaryStage.setScene(scene);
   	    	primaryStage.setTitle("Knight Move");
   	     	primaryStage.show();
    	}catch(IndexOutOfBoundsException e) {
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("no selected thing");
			aler.showAndWait();
    	}
    }

    @FXML
    void remove(ActionEvent event) {
    	
    	try {

		question q = Hardquestionlistview.getSelectionModel().getSelectedItem();
		if(q== null) {
			throw new NullPointerException();
		}
		if(SysData.getInstance().removeQuestion(q)) {
			Hardquestionlistview.getItems().remove(q);
		}
    	}catch(NullPointerException e) {
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("no selected thing");
			aler.showAndWait();
    	}
		
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		SysData.getInstance().loadQuestions();

		questions = sysData.getHardquestions();{
			for (question q : questions) {
				if (!Hard.contains(q)) {
					Hard.add(q);
				}

			}
		}
		ObservableList<question> question = FXCollections.observableArrayList(Hard);
		Hardquestionlistview.setItems(question);
		
	}

}

