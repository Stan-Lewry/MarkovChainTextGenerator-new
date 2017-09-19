import javax.swing.*;

public class ApplicationWindow extends JFrame {
    JFrame frame;

    ApplicationWindow(){
        JButton button = new JButton("click");
        button.setBounds(130, 100, 100, 40);
        add(button);
        setSize(400, 500);
        setLayout(null);
        setVisible(true);
    }
}
