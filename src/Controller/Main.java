package Controller;


import Model.SysData;
import Model.question;
import View.SavesHorseSteps;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;


import java.io.IOException;
import java.util.Stack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;




public class Main extends Application {	
	

	@Override
	public void start(Stage primaryStage) throws IOException {
		

	try {

		Parent root = FXMLLoader.load(getClass().getResource("/View/homepage.fxml"));
		Scene scene = new Scene(root,800,650);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Image icon = new Image("img/logo.png");
	    primaryStage.getIcons().add(icon);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	} catch(Exception e) {
		e.printStackTrace();
	}
	
	
}

	// static variables for saving elements into them during the game
	
      public static  String imageToSave;
      public static  question questionToSave;
      public static  int score;
      public static Label lavelscore2;
      public static Stack<SavesHorseSteps> StepsSave = new Stack<SavesHorseSteps>();
      public static int indexx =0;
      public static int indexy=0;
      public static String nickName;
      public static String message;
      public static int finalScore;
      
      
      
	public static void main(String args[]) {
		
		  launch(args);
		  
		}
	

	

}
