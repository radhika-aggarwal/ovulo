import javax.swing.*;
import java.awt.*;

public class login extends JFrame{
    private JPanel panel1;
    private JTextField usertext;
    private JTextField textField2;
    private JButton loginbutton;
    private JFrame frame;
    public login(){
        frame=new JFrame("Login Frame");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(250,200));
        frame.setResizable(false);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
