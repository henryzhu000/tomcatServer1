package javaxMail.javaxMain;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaxMainApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JavaxMainApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        sendEmailRelay();
    }

    private void sendEmailRelay() throws MessagingException {
        System.out.println("Starting mail send via relay (port 25, no auth)...");

        String host = "192.168.2.119";
        String from = "admin@offlinehoetnuheoasuheotshneo.space";
        String to = "henryzhu23@hotmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "false");

        Session session = Session.getInstance(props);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("Test via Exchange 2019 Relay");
        message.setText("This is a test email sent through Exchange relay on port 25 (no login).");

        Transport.send(message);

        System.out.println("Email sent successfully via relay (25)!");
    }
}
