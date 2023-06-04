package gr.athtech.backend.repository;

import gr.athtech.backend.model.Account;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.sql.SQLException;
import java.util.Optional;


public interface AccountRepository {


    Account getAccountById(Long id) throws SQLException;
    void createAccount(Account account);
    Optional<Account> updateAccount(Account account);
    boolean deleteAccount(Long id);
}
