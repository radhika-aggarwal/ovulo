import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class signup_ {
    private JPanel TPT;
    private JTextField OVULOLOGINTextField;
    private JTextField ET;
    private JTextField PAT;
    private JButton LT;


public signup_() {
    LT.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(LT,"Registration successful dear !");
        }
    });
}
    public static void main(String[] args) {
//        signup_ s= new signup_();
//        s.setContentPane(s.TPT);
//        s.setTitle("Login Form");
//        s.setSize(400,400);
//        s.setVisible(true);
//        s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
