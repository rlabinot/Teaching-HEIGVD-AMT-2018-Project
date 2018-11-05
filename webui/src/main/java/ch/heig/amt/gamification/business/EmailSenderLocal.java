package ch.heig.amt.gamification.business;

import javax.mail.MessagingException;

public interface EmailSenderLocal {
    public void send(String to, String subject, String body) throws MessagingException;
}
