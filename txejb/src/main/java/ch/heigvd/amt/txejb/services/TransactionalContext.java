package ch.heigvd.amt.txejb.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class TransactionalContext implements TransactionalContextLocal {

    @EJB
    ApplicationDAOLocal applicationDAOLocal;

    @EJB
    OldPasswordDAOLocal oldPasswordDAOLocal;

    @EJB
    UserDAOLocal userDAOLocal;

    @Override
    public void deleteUser(String email) {
        applicationDAOLocal.deleteAllApplicationFromUser(email);
        oldPasswordDAOLocal.deleteAllOldPasswordFromUser(email);
        userDAOLocal.deleteUser(email);

    }
}
