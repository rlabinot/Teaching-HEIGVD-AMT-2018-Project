package ch.heig.amt.gamification.business;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.*;

@Stateless
public class EmailSender implements EmailSenderLocal {

    @Resource(name = "java/mailapp")
    private Session session;

    private Message message;

    public void send(String to, String subject, String body) throws MessagingException {
        this.message = new MimeMessage(session);
        this.message.setSubject(subject);
        this.message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        this.message.setText(body);
        Transport.send(this.message);
    }
}