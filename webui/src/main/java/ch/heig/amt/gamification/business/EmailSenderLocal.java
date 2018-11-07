package ch.heig.amt.gamification.business;

import javax.ejb.Local;
import javax.mail.MessagingException;

@Local
public interface EmailSenderLocal {
    public void send(String to, String subject, String body) throws MessagingException;
}
