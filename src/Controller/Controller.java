package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.application.Platform;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import Model.SysData;
import Model.question;



public class Controller implements EventHandler<KeyEvent> {
	
	 @FXML private Label scoreLabel;
	    @FXML private Label levelLabel;
	    @FXML private Label gameOverLabel;
	    private static Controller instance;
	    private SysData sysData;


	    public static Timer timer;
	    public static boolean isTimerOn = false;
	    private static boolean paused;
	    

	    public Controller() {
	        paused = false;
	    }
	    public static Controller getInstance() {
			if(instance == null) {
				instance = new Controller();
			}
			return instance;
		}
		@Override
		public void handle(KeyEvent event) {
			// TODO Auto-generated method stub
			
		}
		
		public void addQuestion(question q) {
			sysData.getInstance().addQuestion(q); 
		}
		
		  
	
}


