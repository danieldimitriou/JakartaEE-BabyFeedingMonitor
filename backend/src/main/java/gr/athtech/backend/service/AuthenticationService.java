package gr.athtech.backend.service;

import gr.athtech.backend.model.LoginResponseData;
import gr.athtech.backend.model.Role;
import gr.athtech.backend.model.User;

import javax.security.auth.login.LoginException;
import java.util.Optional;

public interface AuthenticationService {
    boolean isUserAuthorized(Role role, User user);
    LoginResponseData login(String email, String password) throws LoginException;

    Optional<User> authenticateUser(String email, String password) throws LoginException;
}
