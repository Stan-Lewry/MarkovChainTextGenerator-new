import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

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

public class TextGeneratorTest {

    private TextGenerator textGenerator;

    @Before
    public void setUp() throws Exception {
        textGenerator = new TextGenerator();
    }

    @Test
    public void InstantiatingClassTest(){

    }

    /*
    Testing criteria for BuildCorpus method:
        passing the method an empty file should result in an empty corpus
        passing a non existant file should result in an empty corpus
        passing a non-txt file should result in an empty corpus
        passing a valid corpus should result in a list of strings matching contents of the file
        the corpus should be cleared every time the method is called
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

    /*
    Testing criteria for ConvertToString method:
        Should return a string representing to contents of the given array list delimeted by spaces
        If given an empty arraylist it should return an empty string
     */

    @Test
    public void ConvertToStringShouldReturnSpaceDelimetedString(){
        ArrayList<String> methodInput = new ArrayList<>();
        methodInput.add("The");
        methodInput.add("quick");
        methodInput.add("brown");
        methodInput.add("fox");
        methodInput.add("jumps");
        methodInput.add("over");
        methodInput.add("the");
        methodInput.add("lazy");
        methodInput.add("dog.");

        String expectedOutput = "The quick brown fox jumps over the lazy dog.";

        String actualOutput = textGenerator.ConvertToString(methodInput);

        assertEquals(expectedOutput, actualOutput);
        //assertTrue(actualOutput.equals(expectedOutput));
    }

    @Test
    public void ConvertToStringShouldReturnEmptyStringForEmptyInput(){
        ArrayList<String> methodInput = new ArrayList<>();
        String expectedOutput = "";
        String actualOutput = textGenerator.ConvertToString(methodInput);
        assertEquals(expectedOutput, actualOutput);
    }

    /*
    It is more difficult to make assertions about the output behaviour of the GenerateTextLargeCorpus method as it makes use of RNG
    Testing criteria for GenerateTextLargeCorpus method:
        should return empty string if string length is less than 3
        should return empty string if corpus is not initialised
        should return a string of the length of given value
        returned string should be comprised of individual words that exist in the corpus, delimited by spaces
        what is the maximum length? - what happens if we exceed that length
        also needs to be tested a whole bunch of times as this uses random values
     */

    @Test
    public void GenerateTextShouldReturnEmptyStringForInputsLessThan3(){
        textGenerator.BuildCorpus("Corpus01.txt");

        String expectedOutput = "";
        String actualOutput = textGenerator.GenerateTextLargeCorpus(1);
        assertTrue(actualOutput.equals(expectedOutput));
    }

    @Test
    public void GenerateTextShouldReturnEmptyIfCorpusIsNotInitialized(){
        String expectedOutput = "";
        String actualOutput = textGenerator.GenerateTextLargeCorpus(10);
        assertTrue(actualOutput.equals(expectedOutput));
    }

    @Test
    public void GenerateTextShouldReturnAStringOfEqualLengthToInputValue(){
        textGenerator.BuildCorpus("Corpus01.txt");

        int methodInput = 22;
        String methodOutput = textGenerator.GenerateTextLargeCorpus(methodInput);

        int outputLength = methodOutput.split("\\s+").length;

        assertEquals(methodInput, outputLength);
    }

    @Test
    public void OutputStringShouldBeComprisedOfWordsFromTheCorpusDelimetedBySpaces(){
        //textGenerator = new TextGenerator();

        textGenerator.BuildCorpus("Corpus01.txt");

        ArrayList<String> generatedWords = new ArrayList<>();
        generatedWords.addAll(Arrays.asList(textGenerator.GenerateTextLargeCorpus(10).split("\\s+")));

        boolean foundWord = true;
        for(int i = 0; i < generatedWords.size(); i++){
            foundWord = false;
            int corpusIndex = 0;
            while(!foundWord){
                if(corpusIndex > textGenerator.getCorpus().size()) break;
                if(generatedWords.get(i).equals(textGenerator.getCorpus().get(corpusIndex))) foundWord = true;
                corpusIndex += 1;
            }
            assertTrue(foundWord);
        }
    }

    /*
    Testing criteria for GenerateTextSmallCorpus
        should return empty string if string length is less than 2
        should return empty string if corpus is not initialised
        should return a string of the length of given value
        returned string should be comprised of individual words that exist in the corpus, delimited by spaces
     */
    @Test
    public void GenerateTextSmallCorpusShouldReturnEmptyStringForInputsLessThan3(){
        textGenerator.BuildCorpus("Corpus01.txt");

        String expectedOutput = "";
        String actualOutput = textGenerator.GenerateTextSmallCorpus(1);
        assertTrue(actualOutput.equals(expectedOutput));
    }

    @Test
    public void GenerateTextSmallCorpusShouldReturnEmptyIfCorpusIsNotInitialized(){
        String expectedOutput = "";
        String actualOutput = textGenerator.GenerateTextSmallCorpus(10);
        assertTrue(actualOutput.equals(expectedOutput));
    }

    @Test
    public void GenerateTextSmallCorpusShouldReturnAStringOfEqualLengthToInputValue(){
        textGenerator.BuildCorpus("Corpus01.txt");

        int methodInput = 22;
        String methodOutput = textGenerator.GenerateTextSmallCorpus(methodInput);

        int outputLength = methodOutput.split("\\s+").length;

        assertEquals(methodInput, outputLength);
    }

    @Test
    public void OutputStringShouldBeComprisedOfWordsFromTheCorpusDelimetedBySpacesSmallCorpus(){
        //textGenerator = new TextGenerator();

        textGenerator.BuildCorpus("Corpus01.txt");

        ArrayList<String> generatedWords = new ArrayList<>();
        generatedWords.addAll(Arrays.asList(textGenerator.GenerateTextSmallCorpus(10).split("\\s+")));

        boolean foundWord = true;
        for(int i = 0; i < generatedWords.size(); i++){
            foundWord = false;
            int corpusIndex = 0;
            while(!foundWord){
                if(corpusIndex > textGenerator.getCorpus().size()) break;
                if(generatedWords.get(i).equals(textGenerator.getCorpus().get(corpusIndex))) foundWord = true;
                corpusIndex += 1;
            }
            assertTrue(foundWord);
        }
    }

    /*
    Testing criteria for ClearCorpus:
        When a corpus has been built, calling this function should reset it to empty
     */

    @Test
    public void ClearCorpusShouldResetTheCorpusToEmpty(){
        textGenerator.BuildCorpus("Corpus01.txt");
        textGenerator.ClearCorpus();

        assertTrue(textGenerator.getCorpus().isEmpty());
    }

    @After
    public void tearDown() throws Exception {
    }

}