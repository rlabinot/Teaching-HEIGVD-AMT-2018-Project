package ch.heig.amt.gamification.business;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.*;

@Stateless
public class EmailSender implements EmailSenderLocal {

    @Resource(name = "java/mailapp")
    private Session session;

    public void send(String to, String subject, String body) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setSubject(subject);
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setText(body);
        Transport.send(message);
    }
}