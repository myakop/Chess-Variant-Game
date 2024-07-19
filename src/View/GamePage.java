package View;



import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.EmptyStackException;
import java.util.Random;
import java.util.ResourceBundle;

import Model.Game;

import Model.SysData;
import Utils.squareType;
import Controller.Main;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePage implements Initializable {
	
	
	private static GamePage GamePage;
	public static GamePage getInstance() {
		if (GamePage == null) 
			GamePage = new GamePage();  
		return GamePage;
	}

    String HorseStepSound = "src\\Sounds\\MoveHorse.wav";
    String levelUp = "src\\Sounds\\LevelUp.wav";
    String illegal = "src\\Sounds\\illegalMove.wav";
    
    
    
    
	//board panes
	
	@FXML
    private Pane p00,p10,p20,p30,p40,p50,p60,p70;
	@FXML
	private Pane p01,p11,p21,p31,p41,p51,p61,p71;
	@FXML
	private Pane p02,p12,p22,p32,p42,p52,p62,p72;
	@FXML
	private Pane p03,p13,p23,p33,p43,p53,p63,p73;
	@FXML
	private Pane p04,p14,p24,p34,p44,p54,p64,p74;
	@FXML
	private Pane p05,p15,p25,p35,p45,p55,p65,p75;
	@FXML
	private Pane p06,p16,p26,p36,p46,p56,p66,p76;
	@FXML
	private Pane p07,p17,p27,p37,p47,p57,p67,p77;
	@FXML
    private Text text;
	
	@FXML
    private Label hereWeGo, level2, level3, level4, gameover , randomSquare , forgetSquare ,illegalMove ;
	
	int i= 60; //the timer in the game


    @FXML
    private GridPane board;
    @FXML
    private ImageView medquestion;
    @FXML
    private ImageView hardquestion;
    @FXML
    private ImageView easyquestion;
    @FXML
    private Label gamescore;

    @FXML
    private ImageView horse;
    @FXML
    private ImageView queen;
    @FXML
    private Pane substitute;
    @FXML
    private ImageView king;

  

    @FXML
    private Label level;

    @FXML
    private  Label levelscore;

    @FXML
    private Label timerMin;

    @FXML
    private Label timerSec;
   static Timeline timeline;
   static Timeline timeline2;
   static Timeline timeline3;

    @FXML
    private Label username;
    private Stage stage;
    Model.queen queenPlace  = new Model.queen(0,7); //starting place of the queen 
    Model.king KingPlace  = new Model.king(0,7); //starting place of the king
    
    
    Model.board realBoard = new Model.board();
    private Pane[][] helpBoard =  new Pane[8][8];
    private static int correntscore;
    private static int totalscore;
    private int checklevel = 1;
    static int savequestionpoints =0;
    Main main = new Main();
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	SysData.getInstance().LoadGames();
    	helpboard();
    	buildTheRealBoard();
    	setSquareQuestion();
    	setSquareRandom();
		timer();
		correntscore=0;
		totalscore=0;
		checkHorseStep(0,0);
		
		level.setText(String.valueOf(checklevel));
		username.setText(main.nickName);
		try {
			showLabel(hereWeGo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		

		
    }
    
    
    //for showing messages to the player during the game
    public void showLabel(Label label) throws IOException {
    	TranslateTransition translate = new TranslateTransition();
		translate.setNode(label);
		translate.setDuration(Duration.millis(3000));
    		translate.setByX(1300);
    	
		translate.play();
    	
    }
    
    //set 3 question (easy, med, hard) randomly in the board
    public void setSquareQuestion() {
    	Random s = new Random();
		int x = s.nextInt(7);
		int y = s.nextInt(7);
		if(!helpBoard[x][y].getChildren().contains(easyquestion)) {
		helpBoard[x][y].getChildren().add(easyquestion);
		realBoard.getGameBoard()[x][y].setType(squareType.Question);
		}
		int x2 = s.nextInt(7);
		int y2 = s.nextInt(7);
		if(!helpBoard[x2][y2].getChildren().contains(medquestion)) {
		helpBoard[x2][y2].getChildren().add(medquestion);
		realBoard.getGameBoard()[x2][y2].setType(squareType.Question);
		}
		int x3 = s.nextInt(7);
		int y3 = s.nextInt(7);
		if(!helpBoard[x3][y3].getChildren().contains(hardquestion)) {

		helpBoard[x3][y3].getChildren().add(hardquestion);
		realBoard.getGameBoard()[x3][y3].setType(squareType.Question);
		}
    }
    
    //set a random square in a random place according to the level
    public void setSquareRandom() {
    	Random s = new Random();
    	int i=0;
    	if(checklevel == 1) {
    		i=3;
    	}
    	if(checklevel == 2) {
    		i=2;
    	}
    	for(int j=0 ; j<i ; j++) {
		int x = s.nextInt(7);
		int y = s.nextInt(7);
		realBoard.getGameBoard()[x][y].setType(squareType.Random);
    	}
		
    }
    
  //set a forget square in a random place according to the level
    public void setSquareForget() {
    	Random s = new Random();
    	int i=0;
    	if(checklevel == 1) {
    		i=3;
    	}
    	if(checklevel == 2) {
    		i=2;
    	}
    	for(int j=0 ; j<i ; j++) {
		int x = s.nextInt(7);
		int y = s.nextInt(7);
		realBoard.getGameBoard()[x][y].setType(squareType.Forget);
    	}
    }
    
  //set a block square in a random place according to the level
    public void setSquareBlock() {
    	Random s = new Random();
    
    	for(int j=0 ; j<8 ; j++) {
		int x = s.nextInt(7);
		int y = s.nextInt(7);
		if(           ! ((x==0 & y==0)  || helpBoard[x][y].getStyle().contains("-fx-background-color: red;"))               ) {
		realBoard.getGameBoard()[x][y].setType(squareType.block);
		helpBoard[x][y].setStyle("-fx-background-color: red;");
    	}
		else {
			j--;
		}
    	}
    }
    
    //Save the panes in fxml into help board
    public void helpboard () {
    	helpBoard[0][0] = p00 ; helpBoard[1][0] = p10 ; helpBoard[2][0] = p20; helpBoard[3][0] = p30 ; helpBoard[4][0] = p40 ; helpBoard[5][0] = p50; helpBoard[6][0] = p60 ; helpBoard[7][0] = p70;
    	helpBoard[0][1] = p01 ; helpBoard[1][1] = p11 ; helpBoard[2][1] = p21; helpBoard[3][1] = p31 ; helpBoard[4][1] = p41 ; helpBoard[5][1] = p51; helpBoard[6][1] = p61 ; helpBoard[7][1] = p71;
    	helpBoard[0][2] = p02 ; helpBoard[1][2] = p12 ; helpBoard[2][2] = p22; helpBoard[3][2] = p32 ; helpBoard[4][2] = p42 ; helpBoard[5][2] = p52; helpBoard[6][2] = p62 ; helpBoard[7][2] = p72;
    	helpBoard[0][3] = p03 ; helpBoard[1][3] = p13 ; helpBoard[2][3] = p23; helpBoard[3][3] = p33 ; helpBoard[4][3] = p43 ; helpBoard[5][3] = p53; helpBoard[6][3] = p63 ; helpBoard[7][3] = p73;
    	helpBoard[0][4] = p04 ; helpBoard[1][4] = p14 ; helpBoard[2][4] = p24; helpBoard[3][4] = p34 ; helpBoard[4][4] = p44 ; helpBoard[5][4] = p54; helpBoard[6][4] = p64 ; helpBoard[7][4] = p74;
    	helpBoard[0][5] = p05 ; helpBoard[1][5] = p15 ; helpBoard[2][5] = p25; helpBoard[3][5] = p35 ; helpBoard[4][5] = p45 ; helpBoard[5][5] = p55; helpBoard[6][5] = p65 ; helpBoard[7][5] = p75;
    	helpBoard[0][6] = p06 ; helpBoard[1][6] = p16 ; helpBoard[2][6] = p26; helpBoard[3][6] = p36 ; helpBoard[4][6] = p46 ; helpBoard[5][6] = p56; helpBoard[6][6] = p66 ; helpBoard[7][6] = p76;
    	helpBoard[0][7] = p07 ; helpBoard[1][7] = p17 ; helpBoard[2][7] = p27; helpBoard[3][7] = p37 ; helpBoard[4][7] = p47 ; helpBoard[5][7] = p57; helpBoard[6][7] = p67 ; helpBoard[7][7] = p77;
    	
    }
    
    //build the real board with squares
    public void buildTheRealBoard() {
    
    	realBoard.bulidBoardGame();
    }
   
    public Pane returnPaneClicked (Pane x) {
    	return x;
    }
    
    //check if the square is visited and treat accordingly
    public void checkHorseStep(int i , int j) {
    	if(realBoard.getGameBoard()[i][j].isVisited() == false){
			helpBoard[i][j].setStyle("-fx-background-color: #8a9188;");
			realBoard.getGameBoard()[i][j].setVisited(true);
			PlayHitSound(HorseStepSound);
			updatePoints(1);
			
			SavesHorseSteps savestep = new SavesHorseSteps(i,j,savequestionpoints+1,true);
			savequestionpoints=0;
			main.StepsSave.push(savestep);
			
		}
		else if(realBoard.getGameBoard()[i][j].isVisited() == true) {
			updatePoints(-1);
			SavesHorseSteps savestep = new SavesHorseSteps(i,j,savequestionpoints+(-1),true);
			savequestionpoints=0;
			main.StepsSave.push(savestep);
		}
    	
    }
    
    //update the board according to the step of the horse
    public void updateBoard(Pane horseStep) throws IOException {
    	Random s = new Random();
		
    	for(int i=0 ; i<8 ; i++) {
	    	   for(int j=0 ; j<8 ; j++) {
	    		   
	    			if(helpBoard[i][j] == horseStep) {
	    				checkHorseStep(i,j);
	    				
	    				//check if end game 
		    				if(checklevel==1 || checklevel==2 ) {
		    				if(EndLevel1_2(i,j)==true) {
		    					Alert alert = new Alert(AlertType.WARNING);
		    		    		alert.setTitle("end game");
		    		    		alert.setContentText("game over");
		    		    		alert.showAndWait();
		    		    		endgame();
		    		    		return;
		    				} 
		    				else {
		    					setQueenPlace(i,j);
		    				}
		    				}
		    				if(checklevel==3 || checklevel==4 ) {
			    					
			    				
			    				
			    				main.indexx=i;
			    				main.indexy=j;
			    						
			    				
			    				}		 	
		    			}
	    			
	    			//check if the type square is a question square
	    		   if(helpBoard[i][j] == horseStep) {
	    		if(realBoard.getGameBoard()[i][j].getType()==squareType.Question) {
	    			realBoard.getGameBoard()[i][j].setType(squareType.Regular);
	    			if(helpBoard[i][j].getChildren().contains(easyquestion)){
	    				main.imageToSave =  "easy";
	    				int xeasy = s.nextInt(7);
	    				int yeasy = s.nextInt(7);
	    				helpBoard[xeasy][yeasy].getChildren().add(easyquestion);
	    				realBoard.getGameBoard()[xeasy][yeasy].setType(squareType.Question);
	    				
	    	    		Stage primaryStage = new Stage();
	    	    		Parent root = FXMLLoader.load(getClass().getResource("/View/questionPopUp.fxml"));
	    	    		Scene scene = new Scene(root,800,650);
	    	    		primaryStage.setScene(scene);
	    	    		primaryStage.setTitle("Knight's Move");
	    	    		primaryStage.show();
	    	    		timeline.stop();
	    	    		if(checklevel == 3 || checklevel ==4) {
	    	    		timeline2.stop();
	    	    		timeline3.stop();
	    	    		}
	    			}
	    			if(helpBoard[i][j].getChildren().contains(medquestion)){
	    				main.imageToSave = "med";
	    				int xmed = s.nextInt(7);
	    				int ymed = s.nextInt(7);
	    				helpBoard[xmed][ymed].getChildren().add(medquestion);
	    				realBoard.getGameBoard()[xmed][ymed].setType(squareType.Question);
	    				Stage primaryStage = new Stage();
	    	    		Parent root = FXMLLoader.load(getClass().getResource("/View/questionPopUp.fxml"));
	    	    		Scene scene = new Scene(root,800,650);
	    	    		primaryStage.setScene(scene);
	    	    		primaryStage.setTitle("Knight's Move");
	    	    		primaryStage.show();
	    	    		timeline.stop();
	    	    		if(checklevel == 3 || checklevel ==4) {
		    	    		timeline2.stop();
		    	    		timeline3.stop();
		    	    		}
	    			}
	    			if(helpBoard[i][j].getChildren().contains(hardquestion)){
	    				main.imageToSave = "hard";
	    				int xhard = s.nextInt(7);
	    				int yhard = s.nextInt(7);
	    				helpBoard[xhard][yhard].getChildren().add(hardquestion);
	    				realBoard.getGameBoard()[xhard][yhard].setType(squareType.Question);
	    				Stage primaryStage = new Stage();
	    	    		Parent root = FXMLLoader.load(getClass().getResource("/View/questionPopUp.fxml"));
	    	    		Scene scene = new Scene(root,800,650);
	    	    		primaryStage.setScene(scene);
	    	    		primaryStage.setTitle("Knight's Move");
	    	    		primaryStage.show();
	    	    		timeline.stop(); 
	    	    		if(checklevel == 3 || checklevel ==4) {
		    	    		timeline2.stop();
		    	    		timeline3.stop();
		    	    		}
	    			}	
				}  
	    		   }
	    		   
	    		 //check if the type square is a random square
	    		   if(helpBoard[i][j] == horseStep) {
	    			   if(realBoard.getGameBoard()[i][j].getType()==squareType.Random) {
	    					int x = s.nextInt(7);
	    					int y = s.nextInt(7);
	    					showLabel(randomSquare);
	    					
	    					helpBoard[x][y].getChildren().add(horse);
	    					updateBoard(helpBoard[x][y]);   
	    			   }
	    			      
	    		   }
	    		   
	    		 //check if the type square is a forget square
	    		   if(helpBoard[i][j] == horseStep) {
	    			   if(realBoard.getGameBoard()[i][j].getType()==squareType.Forget) {
	    				   showLabel(forgetSquare);
	    		    		try {
	    		    		for (int i2=0 ; i2<3 ; i2++) {
	    		    			
	    		    		if(main.StepsSave.peek() != null) {
	    		    		SavesHorseSteps  step = main.StepsSave.pop();
	    		    		realBoard.getGameBoard()[step.x][step.y].setVisited(!step.isVisited);
	    		    		updatePoints(step.pointsChanges*(-1));
	    		    		}
	    		    		}
	    		    		}catch(EmptyStackException e){
	    		    			
	    		    		}
	    		    			
	    		    		}
	    					   
	    			   
	    			      
	    		   }   
	    		   
	    			
	    		}
	    	   }
	    		}
    
    //update the points of the player
    public void updatePoints(int x) {
    	correntscore = correntscore+x;
    	totalscore = totalscore+x;
   
    	levelscore.setText(String.valueOf(correntscore));
    	gamescore.setText(String.valueOf(totalscore));
    	
    	
    }
    //update the points from the questions
    public void updatePoints2(int x) {
    	correntscore = correntscore+x;
    	totalscore = totalscore+x;
    }


    //The reaction of the game board according to the step of the horse
    public boolean movehorse(MouseEvent Action) throws IOException {
    	if(timeline.getStatus().equals(Status.STOPPED)) {
    		timeline.play();
    	}
    	if(checklevel == 3 || checklevel == 4) {
    	if(timeline2.getStatus().equals(Status.STOPPED)) {
    		timeline2.play();
    	}
    	if(timeline3.getStatus().equals(Status.STOPPED)) {
    		timeline3.play();
    	}
    	}
    	
    	if(returnPaneClicked((Pane) Action.getSource()).getChildren().contains(horse)) {
    		illegalMove.setText("");
    		if(checklevel==1 ) {
    		setHorsePlace1(returnPaneClicked((Pane) Action.getSource()));
    		}
    		if(checklevel==2 || checklevel==3 || checklevel==4) {
    			setHorsePlace2(returnPaneClicked((Pane) Action.getSource()));
    		}
    		
    		
    		return true;
    	}
    	
    	if (returnPaneClicked((Pane) Action.getSource()).getStyle().contains("-fx-background-color: black;")) {
    		illegalMove.setText("");
    		returnPaneClicked((Pane) Action.getSource()).getChildren().add(horse);
    		updateBoard(((Pane) Action.getSource())); //go to update board
    		returnColor();
    		
    		
    		
    		return true;
    	}
    	else {
    		illegalMove.setText("It's illegal move");
    		PlayHitSound(illegal);
    	}
    	return true;
    }
    
    //set the places that the horse can move to them in level 1
    public void setHorsePlace1(Pane pane) {
    	 
  	       for(int i=0 ; i<8 ; i++) {
  	    	   for(int j=0 ; j<8 ; j++) {
  	    		if(helpBoard[i][j] == pane) {
  	    			if(i+2 <8  && j+1<8) 
  	    				changeColor(helpBoard[i+2][j+1]);
  	    			                  if(i+2 > 8  && j+1 <8)
  	    			                	changeColor(helpBoard[1][j+1]);
  	    			                if(i+2 <8  && j+1==8) 
  	    		  	    				changeColor(helpBoard[i+2][0]);
  	    			              if(i+2 ==8  && j+1<8) 
  	    	  	    				changeColor(helpBoard[0][j+1]);
  	    			            if(i+2 >8  && j+1==8) 
  	    	  	    				changeColor(helpBoard[1][0]);
  	    			          if(i+2 ==8  && j+1==8) 
	    	  	    				changeColor(helpBoard[0][0]);
  	    			                	  
  	    			
  	    			if(i+2 <8  && j-1>-1)
  	    				changeColor(helpBoard[i+2][j-1]);
  	    			                     if(i+2 > 8  && j-1 > -1)
		                             	changeColor(helpBoard[1][j-1]);
  	    			                   if(i+2 ==8  && j-1>-1) 
  	   	    	  	    				changeColor(helpBoard[0][j-1]);
  	    			                if(i+2<8 && j-1 <=-1)
  	    				                changeColor(helpBoard[i+2][8-1]);
  	    			              if(i+2 >8  && j-1==-1)
  	    	  	    				changeColor(helpBoard[1][7]);
  	    			                if(i+2 ==8  && j-1==-1)
  	    	  	    				changeColor(helpBoard[0][7]);
  	    				
  	    			if(i+1<8 && j+2<8)
  	    				changeColor(helpBoard[i+1][j+2]);
  	    			                if(i+1==8 && j+2<8)
  	    				             changeColor(helpBoard[0][j+2]);
  	    			              if(i+1<8 && j+2>8)
  	    	  	    				changeColor(helpBoard[i+1][1]);
  	    			            if(i+1<8 && j+2==8)
  	    	  	    				changeColor(helpBoard[i+1][0]);
  	    			          if(i+1==8 && j+2>8)
  	      	    				changeColor(helpBoard[0][1]);
  	    			        if(i+1==8 && j+2==8)
  	      	    				changeColor(helpBoard[0][0]);
  	    			
  	    			
  	    			if(i-1>-1 && j+2<8)
  	    				changeColor(helpBoard[i-1][j+2]);
  	    			                    if(i-1<=-1 && j+2<8)
  	    			                 	changeColor(helpBoard[8-1][j+2]);
  	    			                    if(i-1 >-1 && j+2>8)
    	    			                 	changeColor(helpBoard[i-1][1]);
  	    			                    if(i-1 >-1 && j+2==8)
	    			                 	changeColor(helpBoard[i-1][0]);
  	    			                  if(i-1==-1 && j+2>8)
  	    		  	    				changeColor(helpBoard[7][1]);
  	    			                    if(i-1==-1 && j+2==8)
  	    		  	    				 changeColor(helpBoard[7][0]);
  	    			
  	    			if(i-2>-1 && j-1>-1)
  	    				changeColor(helpBoard[i-2][j-1]);
  	    			                    if(i-2==-1 && j-1>-1)
  	    			                	changeColor(helpBoard[7][j-1]);
  	    			                  if(i-2  <-1 && j-1>-1)
    	    			                	changeColor(helpBoard[6][j-1]);
  	    			                if(i-2<-1 && j-1==-1)
	    			                	changeColor(helpBoard[6][7]);
  	    			                 if(i-2==-1 && j-1==-1)
	    			                 changeColor(helpBoard[7][7]);
  	    			                if(i-2>-1 && j-1==-1)
  	    		  	    				changeColor(helpBoard[i-2][7]);
  	    			            
  	    			
  	    			          
  	    			if(i-2>-1 && j+1<8)
  	    				changeColor(helpBoard[i-2][j+1]);
  	    			                    if(i-2<-1 && j+1<8)
  	    			                	changeColor(helpBoard[8-2][j+1]);
  	    			                    if(i-2>-1 && j+1==8)
  	    		  	    				changeColor(helpBoard[i-2][0]);
  	    			                    if(i-2==-1 && j+1<8)
  	    		  	    				changeColor(helpBoard[7][j+1]);
  	    			                  if(i-2<-1 && j+1==8)
  	    		  	    				changeColor(helpBoard[6][0]);
  	    			                   if(i-2==-1 && j+1==8)
  	    		  	    			   changeColor(helpBoard[7][0]);
  	    			if(i-1>-1 && j-2>-1)
  	    				changeColor(helpBoard[i-1][j-2]);
  	    		                       	if(i-1==-1 && j-2>-1)
  	    				                changeColor(helpBoard[7][j-2]);
  	    		                      if(i-1>-1 && j-2==-1)
    	    				                changeColor(helpBoard[i-1][7]);
  	    		                    if(i-1>-1 && j-2<-1)
	    				                changeColor(helpBoard[i-1][6]);
  	    		                  if(i-1==-1 && j-2<-1)
	    				                changeColor(helpBoard[7][6]);
                                   if(i-1==-1 && j-2==-1)
    		                        changeColor(helpBoard[7][7]);
  	    			
  	    			
  	    			
  	    			if(i+1<8 && j-2>-1)
  	    				changeColor(helpBoard[i+1][j-2]);	
  	    			                    if(i+1<8 && j-2<-1)
  	    				                 changeColor(helpBoard[i+1][8-2]);
  	    			                  if(i+1==8 && j-2>-1)
   	    				                 changeColor(helpBoard[0][j-2]);
  	    			                if(i+1<8 && j-2==-1)
	    				                changeColor(helpBoard[i+1][7]);
  	    			              if(i+1==8 && j-2<-1)
  	    	  	    				changeColor(helpBoard[0][6]);
  	    			              if(i+1==8 && j-2==-1)
  	    	  	    			 changeColor(helpBoard[0][7]);
  	    		}
  	    	   }
  	    }	
    }
    
  //set the places that the horse can move to them in level 2
    public void setHorsePlace2(Pane pane) {
    	
    	for(int i=0 ; i<8 ; i++) {
	    	   for(int j=0 ; j<8 ; j++) {
	    		
	    		if(helpBoard[i][j] == pane) {
	    			if(i+3 <8  && j+1<8) 
	    				changeColor(helpBoard[i+3][j+1]);	
	    			if(i+3 <8  && j-1>-1)
	    				changeColor(helpBoard[i+3][j-1]);
	    			if(i+1<8 && j+3<8)
	    				changeColor(helpBoard[i+1][j+3]);
	    			if(i-1>-1 && j+3<8)
	    				changeColor(helpBoard[i-1][j+3]);
	    			                            if(i-1==-1 && j+3<8)
	    			                         	changeColor(helpBoard[7][j+3]);
	    			                            if(i+3<8 && j-1==-1)
		    			                         	changeColor(helpBoard[i+3][7]);
	    			                            if(i-3<-1 && j-1==-1)
		    			                         	changeColor(helpBoard[5][7]);
	    			                            if(i-3==-3 && j+1<8)
		    			                         	changeColor(helpBoard[5][j+1]);
	    			                            if(i-1==-1 && j-3==-3)
		    			                         	changeColor(helpBoard[7][5]);
	    			                            if(i+1<8 && j-3==-3)
		    			                         	changeColor(helpBoard[i+1][5]);
	    			                            if(i-2<-1 && j-3==-3)
		    			                         	changeColor(helpBoard[6][5]);
	    			                            if(i-3==-3 && j-2<-1)
		    			                         	changeColor(helpBoard[5][6]);
	    			                            
	    			                            if(i+1<8 && j-3==-2)
		    			                        	changeColor(helpBoard[i+1][6]);
	    			                            if(i-1>-1 && j-3==-2)
		    			                         	changeColor(helpBoard[i-1][6]);
	    			                            if(i+1<8 && j-3==-1)
		    			                        	changeColor(helpBoard[i+1][7]);
	    			                            if(i-1>-1 && j-3==-1)
		    			                         	changeColor(helpBoard[i-1][7]);
	    			                            if(i+1<8 && j-3==-3)
		    			                        	changeColor(helpBoard[i+1][5]);
	    			                            if(i-1>-1 && j-3==-3)
		    			                         	changeColor(helpBoard[i-1][5]);
	    			                            
	    			                            if(i-3==-2 && j+1<8)
		    			                        	changeColor(helpBoard[6][j+1]);
	    			                            if(i-3==-2 && j-1>-1)
		    			                         	changeColor(helpBoard[6][j-1]);
	    			                            if(i-3==-1 && j+1<8)
		    			                        	changeColor(helpBoard[7][j+1]);
	    			                            if(i-3==-1 && j-1>-1)
		    			                         	changeColor(helpBoard[7][j-1]);
	    			                            if(i-3==-3 && j+1<8)
		    			                        	changeColor(helpBoard[5][j+1]);
	    			                            if(i-3==-3 && j-1>-1)
		    			                         	changeColor(helpBoard[5][j-1]);
	    			                            
	    			                            
	    			                            if(i-1>-1 && j+3 ==9)
		    			                        	changeColor(helpBoard[i-1][1]);
	    			                            if(i+1<8 && j+3==9)
		    			                         	changeColor(helpBoard[i+1][1]);
	    			                            if(i-1>-1 && j+3 ==8)
		    			                        	changeColor(helpBoard[i-1][0]);
	    			                            if(i+1<8 && j+3==8)
		    			                         	changeColor(helpBoard[i+1][0]);
	    			                            if(i-1>-1 && j+3 ==10)
		    			                        	changeColor(helpBoard[i-1][2]);
	    			                            if(i+1<8 && j+3==10)
		    			                         	changeColor(helpBoard[i+1][2]);
	    			                            
	    			                            
	    			                            
	    			                            if(i+3==9 && j+1<8)
		    			                        	changeColor(helpBoard[1][j+1]);
	    			                            if(i+3==9 && j-1>-1)
		    			                         	changeColor(helpBoard[1][j-1]);
	    			                            if(i+3==8 && j+1<8)
		    			                        	changeColor(helpBoard[0][j+1]);
	    			                            if(i+3==8 && j-1>-1)
		    			                         	changeColor(helpBoard[0][j-1]);
	    			                            if(i+3==10 && j+1<8)
		    			                        	changeColor(helpBoard[2][j+1]);
	    			                            if(i+3==10 && j-1>-1)
		    			                         	changeColor(helpBoard[2][j-1]);
	    			                            
	    			                            
	    			                            
	    			if(i-3>-1 && j-1>-1)
	    				changeColor(helpBoard[i-3][j-1]);
	    			if(i-3>-1 && j+1<8)
	    				changeColor(helpBoard[i-3][j+1]);
	    			if(i-1>-1 && j-3>-1)
	    				changeColor(helpBoard[i-1][j-3]);
	    			if(i+1<8 && j-3>-1)
	    				changeColor(helpBoard[i+1][j-3]);	
	    			if(i+3 <8  && j+2<8) 
	    				changeColor(helpBoard[i+3][j+2]);	
	    			if(i+3 <8  && j-2>-1)
	    				changeColor(helpBoard[i+3][j-2]);
	    			if(i+2<8 && j+3<8)
	    				changeColor(helpBoard[i+2][j+3]);
	    			if(i-2>-1 && j+3<8)
	    				changeColor(helpBoard[i-2][j+3]);
	    			if(i-3>-1 && j-2>-1)
	    				changeColor(helpBoard[i-3][j-2]);
	    			if(i-3>-1 && j+2<8)
	    				changeColor(helpBoard[i-3][j+2]);
	    			if(i-2>-1 && j-3>-1)
	    				changeColor(helpBoard[i-2][j-3]);
	    			if(i+2<8 && j-3>-1)
	    				changeColor(helpBoard[i+2][j-3]);
	    		}
	    	   }
	    	   }
	    
    
    }
    
    //set queen place according to the horse step (the turn of the queen)
    public void setQueenPlace(int x ,int y) {
    	for(int i=0 ; i<8 ; i++) {
    		if(queenPlace.getXindex()+i==x) {
    			helpBoard[x][queenPlace.getYindex()].getChildren().add(queen);
    			queenPlace.setXindex(x);
    			break;
    		}
    		if(queenPlace.getYindex()+i==y) {
    			helpBoard[queenPlace.getXindex()][y].getChildren().add(queen);
    			queenPlace.setYindex(y);
    			break;
    		}
    		if( queenPlace.getYindex()-i==y) {
    			helpBoard[queenPlace.getXindex()][y].getChildren().add(queen);
    			queenPlace.setYindex(y);
    			break;
    		}
    		if(queenPlace.getXindex()-i==x ) {
    			helpBoard[x][queenPlace.getYindex()].getChildren().add(queen);
    			queenPlace.setXindex(x);
    			break;
    		}
    	}
    }
    
  //set king place according to the horse place 
    public void setKingPlace(int x ,int y) throws IOException {
    	int i =1;	
    	
    	    if(KingPlace.getYindex()+1 == 8 &&   y < 7-y) {
    	    	if(helpBoard[KingPlace.getXindex()][0].getChildren().contains(horse)) {
    	    		
    	    	endgame();
    	    	}
    	    	helpBoard[KingPlace.getXindex()][0].getChildren().add(king);
    			KingPlace.setXindex(KingPlace.getXindex());
    			KingPlace.setYindex(0);
    			return;
    	    }
    	    
    	    if(KingPlace.getYindex()-1 == -1 && y >= 7-y) {
    	    	if(helpBoard[KingPlace.getXindex()][7].getChildren().contains(horse)) {
    	    		endgame();
    	    	}
    	    	helpBoard[KingPlace.getXindex()][7].getChildren().add(king);
    			KingPlace.setXindex(KingPlace.getXindex());
    			KingPlace.setYindex(7);
    			return;
    	    }
    	    if(KingPlace.getXindex()-1 == -1 && x >= 7-x) {
    	    	if(helpBoard[7][KingPlace.getYindex()].getChildren().contains(horse)) {
    	    		endgame();
    	    	}
    	    	helpBoard[7][KingPlace.getYindex()].getChildren().add(king);
    			KingPlace.setXindex(7);
    			KingPlace.setYindex(KingPlace.getYindex());
    			return;
    	    }
    	    if(KingPlace.getXindex()+1 == 8 && x < 7-x) {
    	    	if(helpBoard[KingPlace.getXindex()][7].getChildren().contains(horse)) {
    	    		endgame();
    	    	}
    	    	helpBoard[KingPlace.getXindex()][7].getChildren().add(king);
    			KingPlace.setXindex(7);
    			KingPlace.setYindex(KingPlace.getYindex());
    			return;
    	    }
    	
    	
    		if(KingPlace.getXindex() < x && KingPlace.getYindex() > y) {
    			
    			helpBoard[KingPlace.getXindex()+1][KingPlace.getYindex()-1].getChildren().add(king);
    			KingPlace.setXindex(KingPlace.getXindex()+1);
    			KingPlace.setYindex(KingPlace.getYindex()-1);
    			return;
    		}
    		if(KingPlace.getXindex() < x && KingPlace.getYindex() <y ) {
    			helpBoard[KingPlace.getXindex()+1][KingPlace.getYindex()+1].getChildren().add(king);
    			KingPlace.setXindex(KingPlace.getXindex()+1);
    			KingPlace.setYindex(KingPlace.getYindex()+1);
    			return;
    		}
    		if(KingPlace.getXindex() > x && KingPlace.getYindex() > y) {
    			helpBoard[KingPlace.getXindex()-1][KingPlace.getYindex()-1].getChildren().add(king);
    			KingPlace.setXindex(KingPlace.getXindex()-1);
    			KingPlace.setYindex(KingPlace.getYindex()-1);
    			return;
    		}
    		if(KingPlace.getXindex() > x && KingPlace.getYindex() < y) {
    			helpBoard[KingPlace.getXindex()-1][KingPlace.getYindex()+1].getChildren().add(king);
    			KingPlace.setXindex(KingPlace.getXindex()-1);
    			KingPlace.setYindex(KingPlace.getYindex()+1);
    			return;
    		}
    		if(KingPlace.getXindex() == x && KingPlace.getYindex() < y) {
    			helpBoard[KingPlace.getXindex()][KingPlace.getYindex()+1].getChildren().add(king);
    			KingPlace.setXindex(KingPlace.getXindex());
    			KingPlace.setYindex(KingPlace.getYindex()+1);
    			return;
    		}
    		if(KingPlace.getXindex() == x && KingPlace.getYindex() > y) {
    			helpBoard[KingPlace.getXindex()][KingPlace.getYindex()-1].getChildren().add(king);
    			KingPlace.setXindex(KingPlace.getXindex());
    			KingPlace.setYindex(KingPlace.getYindex()-1);
    			return;
    		}
    		if(KingPlace.getXindex() > x && KingPlace.getYindex() == y) {
    			helpBoard[KingPlace.getXindex()-1][KingPlace.getYindex()].getChildren().add(king);
    			KingPlace.setXindex(KingPlace.getXindex()-1);
    			KingPlace.setYindex(KingPlace.getYindex());
    			return;
    		}
    		if(KingPlace.getXindex() < x && KingPlace.getYindex() == y) {
    			helpBoard[KingPlace.getXindex()+1][KingPlace.getYindex()].getChildren().add(king);
    			KingPlace.setXindex(KingPlace.getXindex()+1);
    			KingPlace.setYindex(KingPlace.getYindex());
    			return;
    		}
    		
    		
    		
    		
    		
    	
    	
    		
    	
    	}
    
   
    //check if the player got on 15+ points
    public boolean checkIfUpLevel() throws IOException {
    	
    	if(correntscore> 15) {
    		
    		return true;
    	}
    	else {
    		
    		endgame();
    		
    	}
    	return false;
    }
    
    //end game and save it
    public void  endgame() throws IOException {
    	
    	timeline.stop();
		if(checklevel != 1 &&  checklevel != 2 ) {
			timeline2.stop();
    		timeline3.stop();
		}
		main.message = "You got "+totalscore+" points";
		Game game = new Game(username.getText(),totalscore);
		SysData.getInstance().addGame(game);
		((Stage) substitute.getScene().getWindow()).close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/View/youLosePage.fxml"));
		Scene scene = new Scene(root,  800,650);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Knight Move");
		primaryStage.show();
    	
    }
    
    //check if the queen can eat the horse
    public Boolean EndLevel1_2(int x , int y) {
    	try {
    	for(int i=0 ; i<8 ; i++) {
    		if(queenPlace.getXindex()+i==x & queenPlace.getYindex()+i==y) {
    			
    			helpBoard[x][y].getChildren().add(queen);
    			return true;
    		}
    		if(queenPlace.getXindex()+i==x & queenPlace.getYindex()-i==y) {
    			helpBoard[x][y].getChildren().add(queen);
    			return true;
    		}
    		if(queenPlace.getXindex()-i==x & queenPlace.getYindex()+i==y) {
    			helpBoard[x][y].getChildren().add(queen);
    			return true;
    		}
    		if(queenPlace.getXindex()-i==x & queenPlace.getYindex()-i==y) {
    			helpBoard[x][y].getChildren().add(queen);
    			return true;
    		}
    		if(queenPlace.getXindex()-i==x & queenPlace.getYindex()==y) {
    			helpBoard[x][y].getChildren().add(queen);
    			return true;
    		}
    		if(queenPlace.getXindex()==x & queenPlace.getYindex()-i==y) {
    			helpBoard[x][y].getChildren().add(queen);
    			return true;
    		}
    		if(queenPlace.getXindex()+i==x & queenPlace.getYindex()==y) {
    			helpBoard[x][y].getChildren().add(queen);
    			return true;
    		}
    		if(queenPlace.getXindex()==x & queenPlace.getYindex()+i==y) {
    			helpBoard[x][y].getChildren().add(queen);
    			return true;
    		}	
    	}
    	
    	}catch ( IllegalArgumentException e) {
    		
    	}
    	return false;
    	
    }
    
  //check if the king can eat the horse
    public Boolean EndLevel3_4(int x , int y) {
    	int i=1;
    		if(KingPlace.getXindex()+i==x & KingPlace.getYindex()+i==y) {
    			helpBoard[x][y].getChildren().add(king);
    			return true;
    		}
    		if(KingPlace.getXindex()+i==x & KingPlace.getYindex()-i==y) {
    			helpBoard[x][y].getChildren().add(king);
    			return true;
    		}
    		if(KingPlace.getXindex()-i==x & KingPlace.getYindex()+i==y) {
    			helpBoard[x][y].getChildren().add(king);
    			return true;
    		}
    		if(KingPlace.getXindex()-i==x & KingPlace.getYindex()-i==y) {
    			helpBoard[x][y].getChildren().add(king);
    			return true;
    		}
    		if(KingPlace.getXindex()-i==x & KingPlace.getYindex()==y) {
    			helpBoard[x][y].getChildren().add(king);
    			return true;
    		}
    		if(KingPlace.getXindex()==x & KingPlace.getYindex()-i==y) {
    			helpBoard[x][y].getChildren().add(king);
    			return true;
    		}
    		if(KingPlace.getXindex()+i==x & KingPlace.getYindex()==y) {
    			helpBoard[x][y].getChildren().add(king);
    			return true;
    		}
    		if(KingPlace.getXindex()==x & KingPlace.getYindex()+i==y) {
    			helpBoard[x][y].getChildren().add(king);
    			return true;
    		}	
    	
    	return false;
    }
    
    //change the color of the square to black(can move to it)
    public void changeColor(Pane p) {	
    	if(!p.getStyle().contains("-fx-background-color: red;"))
    	p.setStyle("-fx-background-color: black;");
    }
    
  //change the color of the square to the true color(visited, not visited)
    public void returnColor() {
    	for(int i=0 ; i<8 ; i++) {
	    	   for(int j=0 ; j<8 ; j++) {
	    		   if(helpBoard[i][j].getStyle().contains("-fx-background-color: black;")) {
	    			   if(!realBoard.getGameBoard()[i][j].isVisited())
	    		helpBoard[i][j].setStyle("-fx-background-color: #a2c988;");	//board color
	    		   }
	    		   if(realBoard.getGameBoard()[i][j].isVisited()) {
	    			   helpBoard[i][j].setStyle("-fx-background-color:#8a9188;");   //visited
	    		   }
	    	   }
	    		}
    }
    
    //reset the squares color
    public void returnColor2() {
    	for(int i=0 ; i<8 ; i++) {
	    	   for(int j=0 ; j<8 ; j++) {
	    		   
	    		helpBoard[i][j].setStyle("-fx-background-color: #a2c988;");	//board color
	    		   }
	    		   
	    	   
	    		}
    }
    
    //timer for the levels of the game
    public void timer() {
    	 timeline = new Timeline(new KeyFrame(Duration.seconds(1),e ->{
			if(i>-1) {
			text.setText(String.valueOf(i));
			i--;
			}
			if(i==-1) {
				try {
					if(checklevel ==3) {
                  timeline2.stop();
                  timeline3.stop();
					}
					if(checkIfUpLevel()){
						
						try {
							uplevel();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(main.score==5) {
				levelscore.setText(String.valueOf(correntscore));
				gamescore.setText(String.valueOf(totalscore));
				main.score=0;
				if(checklevel == 3 || checklevel == 4) {
					timeline2.play();
					timeline3.play();
				}
				
			}
				
			
			
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		}
    
   
    
    int time=10; //after this time(seconds) the king will change speed
    int m=6; //the starting speed of the king(Move every 5 seconds)
    
    //move the king after specific time
    public void timerKing( int j ) {
    
   	 timeline2 = new Timeline(new KeyFrame(Duration.seconds(j),e ->{
   		  if(EndLevel3_4(main.indexx,main.indexy)==true) {
			Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("end game");
    		alert.setContentText("game over");
    		alert.show();
    		
				try {
					endgame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
   		  }
    		else {
    			try {
					setKingPlace(main.indexx,main.indexy);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		}
    		
			
		}));
		timeline2.setCycleCount(Animation.INDEFINITE);
		timeline2.play();
		}
    
    //change the speed of the king movement every 10 seconds
    public void timer10() {
        
      	 timeline3 = new Timeline(new KeyFrame(Duration.seconds(1),e ->{
   			if(time>-1) {
   				time--;
   			}
   			if(time==-1) {
   				timeline2.stop();
   				timerKing(m--);
   				time=10;
   			}
   			
   		}));
   		timeline3.setCycleCount(Animation.INDEFINITE);
   		timeline3.play();
   		
   		}
    
    //reset the board according the level that the player up to it
    public void uplevel() throws IOException {
    	if(checklevel == 2) {
        PlayHitSound(levelUp);
    	showLabel(level3);
    	buildTheRealBoard();
    	substitute.getChildren().remove(king);
    	substitute.getChildren().add(queen);
    	p07.getChildren().add(king);
    	if(!p00.getChildren().contains(horse))
    	          p00.getChildren().add(horse);
    	KingPlace.setXindex(0);
    	KingPlace.setYindex(7);
    	returnColor2();
    	setSquareForget();
    	setSquareRandom();
    	main.StepsSave.clear();
    	timerKing(m);
    	timer10();
        correntscore= 0; 
    	levelscore.setText(String.valueOf(correntscore));
    	checkHorseStep(0,0);
    	i=60;
    	}
    	
    	if(checklevel == 1) {
    		PlayHitSound(levelUp);
    		showLabel(level2);
    	buildTheRealBoard();
    	if(!p07.getChildren().contains(queen))
    	p07.getChildren().add(queen);
    	if(!p00.getChildren().contains(horse))
    	p00.getChildren().add(horse);
    	
    	queenPlace.setXindex(0);
    	queenPlace.setYindex(7);
    	returnColor2();
    	setSquareForget();
    	main.StepsSave.clear();
        correntscore= 0; 
    	levelscore.setText(String.valueOf(correntscore));
    	checkHorseStep(0,0);
    	i=60;
    	}
    	if(checklevel == 3) {
    		
    		PlayHitSound(levelUp);
    		showLabel(level4);
        	buildTheRealBoard();
        	if(!p07.getChildren().contains(king))
        	p07.getChildren().add(king);
        	if(!p00.getChildren().contains(horse))
        	p00.getChildren().add(horse);
        	KingPlace.setXindex(0);
        	KingPlace.setYindex(7);
        	returnColor2();
        	setSquareBlock();
        	main.StepsSave.clear();
        	m=6;
        	main.indexx=0;
        	main.indexy=0;
        	correntscore= 0; 
        	levelscore.setText(String.valueOf(correntscore));
        	checkHorseStep(0,0);
        	i=60;
        	timeline3.play();
    		//timeline2.play();
        	timerKing(m);
        	
        	}
    	
    	if(checklevel == 4) {
    		timeline.stop();
    		timeline2.stop();
    		timeline3.stop();
    		Game game = new Game(username.getText(),totalscore);
    		SysData.getInstance().addGame(game);
    		main.message = "You got "+totalscore+" points";
    		main.finalScore = totalscore;
    		((Stage) substitute.getScene().getWindow()).close();
    		Stage primaryStage = new Stage();
    		Parent root = FXMLLoader.load(getClass().getResource("/View/YouWinPage.fxml"));
    		Scene scene = new Scene(root,800,650);
    		primaryStage.setScene(scene);
    		primaryStage.setTitle("Knight's Move");
    		primaryStage.show();
    		
    	}
    
    	checklevel++;
    	setSquareQuestion();
    	level.setText(String.valueOf(checklevel));
    	
    }
    

@FXML
void back(ActionEvent event) throws IOException {
	timeline.stop();
	correntscore=0;
	totalscore=0;
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/homepage.fxml"));
      Parent root = loader.load();
      stage.setTitle("gamehistory");
      stage.setScene(new Scene(root,800,650));
      stage.show();
      root.requestFocus();

}
@FXML
void pause(ActionEvent event) throws IOException {	
	timeline.stop();
	if(checklevel ==3 || checklevel ==4) {
		timeline2.stop();
		timeline3.stop();
	}
	
}
private void PlayHitSound(String Path) {
	
	Media media = new Media(new File(Path).toURI().toString());
	MediaPlayer m  = new MediaPlayer(media);
	m.setCycleCount(1);
	m.play();
	
}

}
