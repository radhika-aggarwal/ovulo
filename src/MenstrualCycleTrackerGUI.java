import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MenstrualCycleTrackerGUI extends JFrame {
    private JLabel startDateLabel, endDateLabel, lengthLabel, nextCycleStartDateLabel, symptomLabel;
    private JTextField startDateField, endDateField, lengthField, nextCycleStartDateField, symptomField;
    private JButton calculateButton, notifyButton;
    private JTextArea symptomTextArea;
    private JTable calendarTable;

    public MenstrualCycleTrackerGUI() {
        super("Menstrual Cycle Tracker");

        // create labels and text fields
        startDateLabel = new JLabel("Start Date (YYYY-MM-DD):");
        startDateField = new JTextField(10);
        endDateLabel = new JLabel("End Date (YYYY-MM-DD):");
        endDateField = new JTextField(10);
        lengthLabel = new JLabel("Length:");
        lengthField = new JTextField(10);
        nextCycleStartDateLabel = new JLabel("Next Cycle Start Date:");
        nextCycleStartDateField = new JTextField(10);
        symptomLabel = new JLabel("Symptoms:");
        symptomField = new JTextField(10);
        symptomTextArea = new JTextArea(5, 20);

        // create buttons
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate startDate = LocalDate.parse(startDateField.getText());
                LocalDate endDate = LocalDate.parse(endDateField.getText());
                MenstrualCycle menstrualCycle = new MenstrualCycle(startDate, endDate);
                lengthField.setText(String.valueOf(menstrualCycle.getLength()));
                nextCycleStartDateField.setText(menstrualCycle.getNextCycleStartDate().toString());
                updateCalendarTable();
            }
        });

        notifyButton = new JButton("Notify");
        notifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = JOptionPane.showInputDialog("Enter your email:");
                String phone = JOptionPane.showInputDialog("Enter your phone number:");
                Notification notification = new Notification(LocalDate.parse(nextCycleStartDateField.getText()));
                if (notification.isUpcoming()) {
                    notification.sendNotification(email, phone);
                }
            }
        });

        // create calendar table
        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        DefaultTableModel model = new DefaultTableModel(null, daysOfWeek);
        calendarTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(calendarTable);
        JPanel calendarPanel = new JPanel(new BorderLayout());
        calendarPanel.add(scrollPane, BorderLayout.CENTER);

        // add components to the panel
        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(startDateLabel);
        panel.add(startDateField);
        panel.add(endDateLabel);
        panel.add(endDateField);
        panel.add(lengthLabel);
        panel.add(lengthField);
        panel.add(nextCycleStartDateLabel);
        panel.add(nextCycleStartDateField);
        panel.add(symptomLabel);
        panel.add(symptomField);

        // add text area to the panel
        JScrollPane scrollPane2 = new JScrollPane(symptomTextArea);
        panel.add(scrollPane2);

        // add buttons to the panel
        panel.add(calculateButton);
        panel.add(notifyButton);

        // add panel and calendar panel to the frame
        add(panel, BorderLayout.NORTH);
        add(calendarPanel, BorderLayout.CENTER);

        // set frame properties
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void updateCalendarTable() {
        LocalDate startDate = LocalDate.parse(startDateField.getText());
        LocalDate endDate = LocalDate.parse(endDateField.getText());
        ArrayList<LocalDate> menstrualCycleDates = new ArrayList<>();
        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            menstrualCycleDates.add(date);
        }
        DefaultTableModel model = (DefaultTableModel) calendarTable.getModel();
        model.setRowCount(0);
        for (int i = 0; i < menstrualCycleDates.size(); i += 7) {
            Object[] row = new Object[7];
            for (int j = 0; j < 7 && i + j < menstrualCycleDates.size(); j++) {
                row[j] = menstrualCycleDates.get(i + j).format(DateTimeFormatter.ofPattern("MM/dd"));
            }
            model.addRow(row);
        }
    }

    public static void main(String[] args) {
        new MenstrualCycleTrackerGUI();
    }
}