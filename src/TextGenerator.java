import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TextGenerator {

    private ArrayList<String> corpus;

    public TextGenerator(){
        corpus = new ArrayList<>();
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

        for(int i = 0; i < stringList.size(); i++){
            outputString += stringList.get(i);

            if(i < stringList.size() - 1){
                outputString += " ";
            }
        }
        return outputString;
    }


    public String GenerateTextSmallCorpus(int stringLength){

        ArrayList<String> outputStringList = new ArrayList<>();
        ArrayList<String> possibleTransitions;

        if(!corpus.isEmpty()){
            if(stringLength >= 2){
                int randomIndex = ThreadLocalRandom.current().nextInt(0, corpus.size() - 1);
                outputStringList.add(corpus.get(randomIndex));

                while(outputStringList.size() < stringLength){
                    possibleTransitions = new ArrayList<>();
                    for(int i = 0; i < corpus.size() - 2; i++){
                        if(corpus.get(i).equals(outputStringList.get(outputStringList.size() - 1))){
                            possibleTransitions.add(corpus.get(i + 1));
                        }
                    }

                    if(!possibleTransitions.isEmpty()){
                        randomIndex = ThreadLocalRandom.current().nextInt(0, possibleTransitions.size());
                        outputStringList.add(possibleTransitions.get(randomIndex));
                    }
                }
            }
        }

        return ConvertToString(outputStringList);
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


    public ArrayList<String> getCorpus(){
        return corpus;
    }
}
