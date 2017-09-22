import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TextGenerator {

    private ArrayList<String> corpus;

    public TextGenerator(){
        corpus = new ArrayList<>();
    }



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

    public String GenerateTextSmallCorpusInsertPeriod(int stringLength){
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
                outputStringList.get(outputStringList.size() - 1).concat(".");
            }
        }

        return ConvertToString(outputStringList);
    }

    // This function will do the same as the others, except once it reaches the desired length it will continue until it finds a period/
    // Will produce strings of a length at least as long as desired.
    // Strings produced this way will make more sense than strings with a period inserted at the end
    // This dosent work properly at the moment
    public String GenerateTextSmallCorpusUntillPeriod(int stringLength){
        ArrayList<String> outputStringList = new ArrayList<>();
        ArrayList<String> possibleTransitions;

        if(!corpus.isEmpty()){
            if(stringLength >= 2){
                int randomIndex = ThreadLocalRandom.current().nextInt(0, corpus.size() - 1);
                outputStringList.add(corpus.get(randomIndex));

                // continue until the desired length is surpassed and the string ends with a period
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
                    else{  //if there are no possible transitions, grab another word from the corpus at random.
                        if(outputStringList.get(outputStringList.size() - 1).endsWith("."))
                            outputStringList.get(outputStringList.size()-1).concat(".");    // if the string so far doesnt end with a period, put one
                        outputStringList.add(corpus.get(
                                ThreadLocalRandom.current().nextInt(0, corpus.size() - 1)
                        ));
                    }

                    // if we've reached the desired length but it still dosent end with a period, increase the length by one
                    if(outputStringList.size() >= stringLength && !outputStringList.get(outputStringList.size() - 1).endsWith(".")){
                        stringLength += 1;
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
