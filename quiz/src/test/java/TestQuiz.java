/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import app.Quiz;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * @since 4/8/09
 * @author levi
 * JUnit test for the TestQuiz class.  Basic verification tests that all pass as of 4/8/09.
 */
public class TestQuiz {
    Quiz quizUnderTest = new Quiz();

    public TestQuiz() {
        
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testIsCorrect() {
        /* should be at first question, answer should be "9" */
        assertTrue("first question answer should be '9'", quizUnderTest.isCorrect("9"));
    }
    
    @Test
    public void testGetNumberQuestions() {
        /* should be at first question, answer should be 5 */
        assertEquals(5, quizUnderTest.getNumQuestions());
    }    
    
    @Test
    public void testGetNumberCorrect() {
        /* should be at first question, answer should be 0 */
        assertEquals(0, quizUnderTest.getNumCorrect());  
        
        quizUnderTest.scoreAnswer(); //should increment the score and the current question number
        assertEquals(1, quizUnderTest.getNumCorrect());
        assertEquals(1, quizUnderTest.getCurrentQuestionIndex());
        
    }        
    
    

}