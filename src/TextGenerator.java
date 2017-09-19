import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TextGenerator {

    private ArrayList<String> outputString;
    private int stringLength;
    private ArrayList<String> corpus;

    public TextGenerator(){
        // Constructor, currently does nothing.
        corpus = new ArrayList<>();
        outputString = new ArrayList<>();
        stringLength = 10;
    }


    // This needs testing
    public void BuildCorpus(String filePath){

        ClearCorpus();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;

            try{
                while((line = br.readLine()) != null){

                    corpus.addAll(Arrays.asList(line.split("\\s+")));

                }
            }catch(IOException ioException){

                System.out.println(ioException.getMessage());
            }


        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void ClearCorpus(){
        corpus = new ArrayList<>();
    }

    public void setOutputStringLength(int stringLength){
        stringLength = this.stringLength;
    }

    public void GenerateText(){

        if(outputString.size() == 0){
            // grab the first two from the corpus
            // recurse
            int randomNum = ThreadLocalRandom.current().nextInt(0, corpus.size() - 1);
            outputString.add(corpus.get(randomNum));
            outputString.add(corpus.get(randomNum + 1));
            GenerateText();
        }
        else if(outputString.size() < stringLength){
            // find every word that comes after our two words.
            // look up first word, if our second word comes after it, grab the word that comes after that word.
            ArrayList<String> setOfPossibleNextWords = new ArrayList<String>();
            for(int i = 0; i < corpus.size() - 3; i++){    // IF WE GET INDEX OUT OF BOUNDS, ITS LIKELY CAUSED BY THIS LINE
                if(corpus.get(i).equals(outputString.get(outputString.size() - 2))){
                    if(corpus.get(i + 1).equals(outputString.get(outputString.size() - 1))){
                        setOfPossibleNextWords.add((corpus.get(i + 2)));
                    }
                }
            }

            if(setOfPossibleNextWords.size() > 0){
                Random ran = new Random();
                //int randomNum = 0 + (int)(Math.random() * setOfPossibleNextWords.size());
                int randomNum = ThreadLocalRandom.current().nextInt(0, setOfPossibleNextWords.size());
                outputString.add(setOfPossibleNextWords.get(randomNum));
                GenerateText();
            }
        }else{
            return;
        }
    }

    public String getOutputString(){
        String finalString = new String();
        for(String str : outputString){
            finalString += str;
            finalString += " ";
        }
        return  finalString;
    }

    public void PrintCorpus(){
        System.out.println("Printing the contents of the current corpus...");
        for(String line : corpus){
            System.out.println(line);
        }
    }

    public ArrayList<String> getCorpus(){
        return corpus;
    }
}
