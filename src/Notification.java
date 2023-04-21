import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class Notification {
    private LocalDate periodStartDate;

    public Notification(LocalDate periodStartDate) {
        this.periodStartDate = periodStartDate;
    }

    public boolean isUpcoming() {
        return true;
//        LocalDate now = LocalDate.now();
//        long daysUntilPeriod = ChronoUnit.DAYS.between(now, periodStartDate);
//        return daysUntilPeriod <= 3;
    }

    public void sendNotification(String email, String phone) {
            // mail properties
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            // mail session
            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("aggradhika23@gmail.com", "pjn");
                }
            });

            // email message
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("aggradhika23@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                message.setSubject("Upcoming Period Notification");
                message.setText("Your period is coming up in 3 days. Be prepared!");

                // send email message
                Transport.send(message);
                System.out.println("Notification sent to " + email);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
    }
}