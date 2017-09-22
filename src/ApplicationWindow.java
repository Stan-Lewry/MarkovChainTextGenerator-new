import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.sun.glass.ui.Cursor.setVisible;

public class ApplicationWindow extends JFrame {
    private TextGenerator textGenerator;

    public ApplicationWindow(){
        setTitle("Markov Chain Text Generator");
        initializeWindow();
    }

    public void initializeWindow(){

        ImageIcon icon = createImageIcon("icon.png", "Icon");
        JLabel titleLabel = new JLabel("Markov Chain Text Generator\nVersion 0", icon, JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.BOTTOM);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);

        add(titleLabel);



        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(null);
        setSize(400, 500);

        setVisible(true);
    }

    private ImageIcon createImageIcon(String path, String description){
        java.net.URL imgURL = getClass().getResource(path);
        if(imgURL != null){
            System.out.println("Loaded resource");
            return new ImageIcon(imgURL, description);
        }else{
            System.out.println("Failed to load resource");
            return null;
        }
    }
}

/*
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
                String generatedString = textGenerator.GenerateTextSmallCorpusUntillPeriod(20);
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
*/