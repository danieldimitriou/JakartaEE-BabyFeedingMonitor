package gr.athtech.backend.service;

import gr.athtech.backend.model.LoginData;
import gr.athtech.backend.model.LoginResponseData;
import gr.athtech.backend.model.User;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(Long id) throws SQLException;
    boolean createUser(User user);
    Optional<User> updateUser(User user);
    boolean deleteUser(Long id);
    LoginResponseData login(LoginData loginData) throws LoginException;
    Optional<User> getUserByEmail(String email);
}
