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

        if(!filePath.endsWith(".txt")) return;

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

    public String ConvertToString(ArrayList<String> stringList){
        String outputString = "";
        for(String str : stringList){
            outputString.concat(str.concat(" "));
        }
        return outputString;
    }

    public void setOutputStringLength(int stringLength){
        stringLength = this.stringLength;
    }

    public String GenerateText(int stringLength){
        ArrayList<String> outputStringList = new ArrayList<>();
        ArrayList<String> possibleTransisions;

        if(!corpus.isEmpty()) {
            if (stringLength >= 3) {
                int randomIndex = ThreadLocalRandom.current().nextInt(0, corpus.size() - 1);
                outputStringList.add(corpus.get(randomIndex));
                outputStringList.add(corpus.get(randomIndex + 1));

                while (outputStringList.size() < stringLength) {
                    possibleTransisions = new ArrayList<>();
                    for (int i = 0; i < corpus.size() - 3; i++) {
                        if (corpus.get(i).equals(outputStringList.get(outputStringList.size() - 2))) {
                            if (corpus.get(i + 1).equals(outputStringList.get(outputStringList.size() - 1))) {
                                possibleTransisions.add(corpus.get(i + 2));
                            }
                        }
                    }

                    if (!possibleTransisions.isEmpty()) {
                        randomIndex = ThreadLocalRandom.current().nextInt(0, possibleTransisions.size());
                        outputStringList.add(possibleTransisions.get(randomIndex));
                    }
                }
            }
        }

        return ConvertToString(outputStringList);
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
