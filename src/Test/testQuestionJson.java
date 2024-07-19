package Test;

import static org.junit.Assert.*;
import org.junit.Test;
import Model.Game;
import Model.SysData;
import Model.question;

public class testQuestionJson {

	//Test for add a hard question
	@Test
	public void testAddQuestion() {
		
		SysData.getInstance().loadQuestions();
		question TestQ= new question("question",1,"3","Tiger");
		SysData.getInstance().addQuestion(TestQ);
		SysData.getInstance().loadQuestions();   // load the question from the json again
		assertTrue(SysData.getInstance().allquestion.contains(TestQ));// check if the question actually is added
		 
		SysData.getInstance().removeQuestion(TestQ);
	
		
	}
	
	
	//Test for remove question
	@Test
	public void testRemoveQ() {
		
		question TestQ= new question("question",2,"3","Tiger");
		SysData.getInstance().addQuestion(TestQ);
		SysData.getInstance().removeQuestion(TestQ);
		SysData.getInstance().loadQuestions();
		
		assertTrue(!SysData.getInstance().allquestion.contains(TestQ));   // check if the question actually is removed 
	}
	
	//Test for edit question
	@Test
	public void testEdit() {
		
		question TestQ= new question("question",1,"3","Tiger");
		SysData.getInstance().addQuestion(TestQ);
		question TestQNew= new question("question2",3,"3","Tiger");
		SysData.getInstance().editQuestion(TestQ, TestQNew);
		assertTrue(SysData.getInstance().allquestion.contains(TestQNew));
		SysData.getInstance().removeQuestion(TestQNew);
		
	}
	

	
	//Test for load question
	@Test
	public void testLoadQ() {
		
		SysData.getInstance().loadQuestions();
		assertNotNull("Successful",SysData.getInstance().allquestion);
		
	}

}

