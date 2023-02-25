import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registration extends JFrame{
    private JPanel tpt;
    private JTextField nt;
    private JTextField et;
    private JTextField pt;
    private JTextField pat;
    private JButton submit;
    private JTextField at;
public registration() {
    submit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(submit,"Registration successful dear, "+nt.getText()+" !");
        }
    });
}

    public static void main(String[] args) {
        registration r= new registration();
        r.setContentPane(r.tpt);
        r.setTitle("Registration Form");
        r.setSize(400,400);
        r.setVisible(true);
        r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
