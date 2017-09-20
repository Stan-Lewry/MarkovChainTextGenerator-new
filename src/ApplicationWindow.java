import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationWindow extends JFrame {
    JFrame frame;
    private TextGenerator textGenerator;
    public ApplicationWindow(){

        textGenerator = new TextGenerator();


        JTextArea textArea = new JTextArea("Output string goes here.");
        textArea.setBounds(10, 30, 200, 200);


        JButton generateTextButton = new JButton("Generate Text");
        generateTextButton.setBounds(130, 100, 100, 40);

        JButton buildCorpusButton = new JButton("Build Corpus");
        buildCorpusButton.setBounds(20,100, 100, 40);


        buildCorpusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textGenerator.BuildCorpus("Corpus01.txt");
            }
        });

        generateTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //textArea.setText(textGenerator.GenerateText(20));
//                if((String generatedText = textGenerator.GenerateText(10)) != ""){
//
//                }
                String generatedString = textGenerator.GenerateText(20);
                if(generatedString.equals("")){
                    textArea.setText("Please select a corpus first");
                }else{
                    textArea.setText(generatedString);
                }
            }
        });

        add(buildCorpusButton);
        add(generateTextButton);
        add(textArea);
        setSize(400, 500);
        setLayout(null);
        setVisible(true);
    }

}
