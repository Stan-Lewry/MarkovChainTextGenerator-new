import javax.swing.*;

//a comment

public class Main {

    //private TextGenerator textGenerator;

    public static void main(String [] args){

        //ApplicationWindow applicationWindow = new ApplicationWindow();
        //MainWindow mw = new MainWindow();


        JFrame frame = new JFrame("Markov Chain Text Generator");
        frame.setContentPane(new MainWindow().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //1System.out.println("Hopeful ly my tests will run");
        /*
      TextGenerator textGenerator = new TextGenerator();
      textGenerator.BuildCorpus("testCorpus");
      textGenerator.PrintCorpus();

      System.out.println("Attempting to Generate a string.... \n\n\n");

      textGenerator.GenerateTextLargeCorpus();


      System.out.println("Generated string:");
      System.out.println(textGenerator.getOutputString());

        ApplicationWindow applicationWindow = new ApplicationWindow();
        */
    }
}
