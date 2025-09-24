package javaxMail.javaxMain;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaxAuthApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JavaxAuthApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        sendEmailAuth();
    }

    private void sendEmailAuth() throws MessagingException {
        System.out.println("Starting mail send via authenticated connector (port 587)...");

        String host = "192.168.2.119";                // Exchange server IP
        String username = "henry@corp.local";         // mailbox-enabled user UPN
        String password = "_";             // Henry's AD password
        String from = "henry@offlinehoetnuheoasuheotshneo.space"; 
        String to = "henryzhu23@hotmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // STARTTLS required
        props.put("mail.smtp.ssl.trust", host);         // trust local Exchange cert
        props.put("mail.smtp.auth.mechanisms", "LOGIN"); // force LOGIN
        props.put("mail.debug", "true");

        Session session = Session.getInstance(props,
            new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("Test via Exchange 2019 Authenticated SMTP");
        message.setText("This is a test email sent with login over port 587 using Henry's mailbox.");

        Transport.send(message);

        System.out.println("Email sent successfully via authenticated connector (587)!");
    }
}
