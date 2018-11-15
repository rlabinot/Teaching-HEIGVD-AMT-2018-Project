package ch.heigvd.amt.txejb.services;

import javax.ejb.Local;

@Local
public interface TransactionalContextLocal {
    public void deleteUser(String email);
}
