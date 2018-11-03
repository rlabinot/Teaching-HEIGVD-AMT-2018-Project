package ch.heig.amt.gamification.business;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface EmailSenderLocal {
    public void send(String to, String subject, String body) throws MessagingException;
}
