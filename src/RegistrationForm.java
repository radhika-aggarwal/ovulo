import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class RegistrationForm extends JFrame{
    private JPanel tpt;
    private JTextField nt;
    private JTextField et;
    private JPasswordField pt;
    private JButton submit;
    static Connection connection;
    PreparedStatement statement;


    public RegistrationForm() {
    submit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
//            JOptionPane.showMessageDialog(submit,"Registration successful dear, "+nt.getText()+" !");
            String username = et.getText();
            String password = new String(pt.getText());

            try {
                // Prepare the SQL statement
                statement = connection.prepareStatement("INSERT INTO users (name, username, password) VALUES (?, ?, ?)");
                statement.setString(1, nt.getText());
                statement.setString(2, username);
                statement.setString(3, password);

                // Execute the SQL statement
                int result = statement.executeUpdate();

                if (result > 0) {
                    // Open the Login frame
                    LoginForm loginFrame = new LoginForm();
                    loginFrame.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(submit, "Failed to create user account", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(submit, "Failed to create user account", "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            LoginForm lgnForm = new LoginForm();
            lgnForm.setVisible(true);
            dispose();
        }
    });
}

    public static void main(String[] args) {
        RegistrationForm r= new RegistrationForm();
        r.setContentPane(r.tpt);
        r.setTitle("Registration Form");
        r.setSize(400,400);
        r.setVisible(true);
        r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/menstrual?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true", "root", "Radhika@123");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(r, "Failed to connect to the database", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
            System.exit(1);
        }
    }
}
