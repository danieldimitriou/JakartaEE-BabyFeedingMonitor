package gr.athtech.backend.repository;

import gr.athtech.backend.model.User;

import java.sql.SQLException;
import java.util.Optional;


public interface UserRepository {


    Optional<User> getUserById(Long id) throws SQLException;
    void createUser(User user);
    Optional<User> updateUser(User user);
    boolean deleteUser(Long id);
}
