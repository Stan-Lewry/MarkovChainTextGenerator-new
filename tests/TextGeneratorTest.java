import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TextGeneratorTest {

    private TextGenerator textGenerator;

    @Before
    public void setUp() throws Exception {
        textGenerator = new TextGenerator();
    }

     /*
    The following tests test the building of the corpus.
    The class has a function BuildCorpus(String filePath) that:
        Takes a path to a pre-processed corpus file as a string,
        if it can find and open the file then it will convert the contents of the corpus to an ArrayList
        of Strings. For example:
        Input File:
            The quick brown fox jumps over the lazy dog.
            The quick brown fox jumps over the lazy dog.
        Output Array:
            {"The", "quick", "brown", "fox", "jumps", "over, "the", "lazy", "dog.", "The", "quick", "brown", "fox", "jumps", "over, "the", "lazy", "dog.",}

     If the file cant be found the corpus should be empty
     If the text file is empty the corpus should be empty
     The corpus should be emptied every time the function is called
     If the file is not a dot txt the corpus will remain empty
     */

    @Test
    public void OpeningEmptyFileShouldLeaveCorpusEmpty(){
        textGenerator.BuildCorpus("TestCorpusEmpty.txt");
        assertEquals(true, textGenerator.getCorpus().isEmpty());
    }

    @Test
    public void AttemptingToOpenNonExistantFileShouldLeaveCorpusEmpty(){
        textGenerator.BuildCorpus("ThisFileDoesNotExist.txt");
        assertEquals(true, textGenerator.getCorpus().isEmpty());
    }

    @Test
    public void AttemptingToOpenNonTxtFileShouldLeaveCorpusEmpty(){
        textGenerator.BuildCorpus("TestCorpusNonTxt.html");
        assertEquals(true, textGenerator.getCorpus().isEmpty());
    }

    @Test
    public void OpeningAValidCorpusFileShouldGenerateAnArrayListOfStrings(){
        ArrayList<String> correctList = new ArrayList<>();
        correctList.add("This");
        correctList.add("is");
        correctList.add("a");
        correctList.add("test");
        correctList.add("corpus.");
        correctList.add("It");
        correctList.add("contains");
        correctList.add("nine");
        correctList.add("words.");

        textGenerator.BuildCorpus("testCorpus.txt");

        for(int i = 0; i < textGenerator.getCorpus().size(); i++){
            assertEquals(correctList.get(i), textGenerator.getCorpus().get(i));
        }

    }

    @Test
    public void CorpusShouldBeClearedEachBuildCorpusIsCalled(){
        textGenerator.BuildCorpus("testCorpus.txt");
        textGenerator.BuildCorpus("ThisFileDoesNotExist.php");
        assertEquals(true, textGenerator.getCorpus().isEmpty());
    }

    @After
    public void tearDown() throws Exception {
    }

}