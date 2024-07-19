package Test;


import static org.junit.Assert.*;
import org.junit.Test;
import Model.Game;
import Model.SysData;
import Model.question; 
public class testGameJson {
	
	
	//Test for saving a new game
	@Test
	public void testSaveGame(){
		Game g=new Game(100,"solyma");
		SysData.getInstance().LoadGames();
		SysData.getInstance().addGame(g);
		assertTrue(SysData.getInstance().games.contains(g)); // check if the game actually is added
		SysData.getInstance().removeGame(g);
		
	}
	
	//Test for load game
	@Test
	public void testLoadGame() {
		
		SysData.getInstance().LoadGames();
		assertNotNull("Successful",SysData.getInstance().games);
		
	}

}
