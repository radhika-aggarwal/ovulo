import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class LoginForm extends JFrame{
    private JPanel panel1;
    private JTextField usertext;
    private JPasswordField textField2;
    private JButton loginbutton;
    private JFrame frame;
    static Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;
    public LoginForm() {
        loginbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usertext.getText();
                String password = new String(textField2.getPassword());

                try {
                    // Prepare the SQL statement
                    statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
                    statement.setString(1, username);
                    statement.setString(2, password);

                    // Execute the SQL statement
                    resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        // Open the Main frame
                        MenstrualCycleTrackerGUI mainFrame = new MenstrualCycleTrackerGUI();
                        mainFrame.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(loginbutton, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(loginbutton, "Failed to login", "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    try {
                        resultSet.close();
                        statement.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/menstrual?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true", "root", "Radhika@123");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
            System.exit(1);
        }
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
