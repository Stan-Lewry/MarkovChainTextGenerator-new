import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

enum CorpusMode{CORPUSMODE_NONE, CORPUSMODE_LARGE, CORPUSMODE_SMALL};
enum GenerationTechnique{TECHNIQUE_NONE, TECHNIQUE_RANDOM, TECHNIQUE_MARKOV, TECHNIQUE_UNTILPERIOD, TECHNIQUE_INSERTPERIOD};

public class MainWindow {

    private TextGenerator textGenerator;

    private CorpusMode corpusMode;
    private GenerationTechnique generationTechnique;

    private int desiredOutputSize;

    public JPanel mainPanel;
    private JComboBox comboBox1;
    private JButton buildCorpusButton;
    private JRadioButton largeRadioButton;
    private JRadioButton smallRadioButton;
    private JSlider slider1;
    private JRadioButton randomGenerationRadioButton;
    private JRadioButton markovChainRadioButton;
    private JRadioButton markovChainUntilPeriodRadioButton;
    private JRadioButton markovChainInsertPeriodRadioButton;
    private JLabel sliderValueLabel;
    private JButton generateTextButton;
    private JTextPane textPane1;
    private JTextPane outputPane;
    private JLabel headingLabel;


    public MainWindow() {


        textGenerator = new TextGenerator();

        corpusMode = CorpusMode.CORPUSMODE_NONE;
        generationTechnique = GenerationTechnique.TECHNIQUE_NONE;


        desiredOutputSize = slider1.getValue();

        sliderValueLabel.setText(Integer.toString(slider1.getValue()));

        textPane1.setEditable(false);
        outputPane.setEditable(false);

        textPane1.setText("Please selected a generation technique.");

        largeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectLargeCorpusMode();
            }
        });

        smallRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectSmallCorpusMode();
            }
        });

        buildCorpusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectBuildCorpus();
            }
        });

        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                SliderUpdate();
            }
        });

        randomGenerationRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetTechniqueRandom();
            }
        });

        markovChainRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetTechniqueMarkovChain();
            }
        });

        markovChainUntilPeriodRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetTechniqueMarkovChainUntillPeriod();
            }
        });

        markovChainInsertPeriodRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetTechniqueMarkovChainInsertPeriod();
            }
        });

        generateTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectGenerateTextButton();
            }
        });
    }

    public void SelectLargeCorpusMode(){
        smallRadioButton.setSelected(false);
        corpusMode = CorpusMode.CORPUSMODE_LARGE;

        slider1.setMinimum(3);
    }

    public void SelectSmallCorpusMode(){
        largeRadioButton.setSelected(false);
        corpusMode = CorpusMode.CORPUSMODE_SMALL;
        slider1.setMinimum(2);
    }

    public void SelectBuildCorpus(){
        if(comboBox1.getSelectedItem() != "Select Corpus..."){
            System.out.println("SelectBuildCorpus Method");
            textGenerator.BuildCorpus((String)comboBox1.getSelectedItem());
        }
    }

    public void SliderUpdate(){
        sliderValueLabel.setText(Integer.toString(slider1.getValue()));
        desiredOutputSize = slider1.getValue();
    }

    public void SetTechniqueRandom(){
        markovChainRadioButton.setSelected(false);
        markovChainUntilPeriodRadioButton.setSelected(false);
        markovChainInsertPeriodRadioButton.setSelected(false);

        generationTechnique = GenerationTechnique.TECHNIQUE_RANDOM;

        textPane1.setText("This technique will return a purely randomly generated string of the desired length.");
    }

    public void SetTechniqueMarkovChain(){
        randomGenerationRadioButton.setSelected(false);
        markovChainUntilPeriodRadioButton.setSelected(false);
        markovChainInsertPeriodRadioButton.setSelected(false);

        generationTechnique = GenerationTechnique.TECHNIQUE_MARKOV;

        textPane1.setText("Generate a string of the desired length following a simple Markov chain process. This technique has no punctuation rules so outputs may be quite messy.");
    }

    public void SetTechniqueMarkovChainUntillPeriod(){
        randomGenerationRadioButton.setSelected(false);
        markovChainRadioButton.setSelected(false);
        markovChainInsertPeriodRadioButton.setSelected(false);

        generationTechnique = GenerationTechnique.TECHNIQUE_UNTILPERIOD;

        textPane1.setText("Generate a string at least as long as the desired length. This technique will continue to add words until the generated string ends with a period. Sentances produced like this may be substantially longer than desired but should make slightly more gramatical sense.");
    }

    public void SetTechniqueMarkovChainInsertPeriod(){
        randomGenerationRadioButton.setSelected(false);
        markovChainRadioButton.setSelected(false);
        markovChainUntilPeriodRadioButton.setSelected(false);

        generationTechnique = GenerationTechnique.TECHNIQUE_INSERTPERIOD;

        textPane1.setText("Generate a string of the desired lenght and insert a period at the end. This will always produce correct-length strings ending with a period but results may not make gramatical sense.");
    }

    public void SelectGenerateTextButton(){
        if(!textGenerator.getCorpus().isEmpty()){
            if(corpusMode != CorpusMode.CORPUSMODE_NONE){
                if(generationTechnique != GenerationTechnique.TECHNIQUE_NONE){
                    // generate some text
                    switch(generationTechnique){
                        case TECHNIQUE_RANDOM:
                            //generate random text
                            if(corpusMode == CorpusMode.CORPUSMODE_SMALL){
                                outputPane.setText(
                                        textGenerator.GenerateTextRandom(desiredOutputSize)
                                );
                            }else if(corpusMode == CorpusMode.CORPUSMODE_LARGE){
                                outputPane.setText(
                                        textGenerator.GenerateTextRandom(desiredOutputSize)
                                );
                            }
                            break;
                        case TECHNIQUE_MARKOV:
                            //markov technique
                            if(corpusMode == CorpusMode.CORPUSMODE_SMALL){
                                outputPane.setText(
                                        textGenerator.GenerateTextSmallCorpus(desiredOutputSize)
                                );
                            }else if(corpusMode == CorpusMode.CORPUSMODE_LARGE){
                                outputPane.setText(
                                        textGenerator.GenerateTextLargeCorpus(desiredOutputSize)
                                );
                            }
                            break;
                        case TECHNIQUE_UNTILPERIOD:
                            // until period
                            if(corpusMode == CorpusMode.CORPUSMODE_SMALL){
                                outputPane.setText(
                                        textGenerator.GenerateTextSmallCorpusUntilPeriod(desiredOutputSize)
                                );
                            }else if(corpusMode == CorpusMode.CORPUSMODE_LARGE){
                                outputPane.setText(
                                        textGenerator.GenerateTextLargeCorpusUntilPeriod(desiredOutputSize)
                                );
                            }
                            break;
                        case TECHNIQUE_INSERTPERIOD:
                            //insert period
                            if(corpusMode == CorpusMode.CORPUSMODE_SMALL){
                                outputPane.setText(
                                        textGenerator.GenerateTextSmallCorpusInsertPeriod(desiredOutputSize)
                                );
                            }else if(corpusMode == CorpusMode.CORPUSMODE_LARGE){
                                outputPane.setText(
                                        textGenerator.GenerateTextLargeCorpusInsertPeriod(desiredOutputSize)
                                );
                            }
                            break;
                        default:
                            break;
                    }

                }else{
                    outputPane.setText("Plase select a generation technique.");
                }
            }else{
                outputPane.setText("Please select the corpus mode.");
            }
        }else{
            outputPane.setText("Please select and build a corpus first.");
        }
    }
}