package ch.heig.amt.gamification.business;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.*;

@Stateless
public class EmailSender implements EmailSenderLocal {

    @Resource(name = "java/mailapp")
    private Session session;

    private MimeMessage message;

    public EmailSender(String to, String subject, String body) throws MessagingException {
        message = new MimeMessage(session);
        message.setSubject(subject);
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setText(body);
    }

    public void send() {
        try {
            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}