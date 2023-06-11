package gr.athtech.backend.service.serviceImpl;

import gr.athtech.backend.JWTGenerator;
import gr.athtech.backend.model.LoginData;
import gr.athtech.backend.model.LoginResponseData;
import gr.athtech.backend.model.Role;
import gr.athtech.backend.model.User;
import gr.athtech.backend.repository.UserRepository;
import gr.athtech.backend.service.AuthenticationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import java.util.Optional;

public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger logger = (Logger) LogManager.getLogger(AuthenticationServiceImpl.class);

    @Inject
    private UserRepository userRepository;
    @Override
    public boolean isUserAuthorized(Role role, User user) {
        Role userRole = this.userRepository.getUserByEmail(user.getEmail()).get().getRole();
        return userRole.equals(role);
    }

    @Override
    public LoginResponseData login(String email, String password) throws LoginException {
        return null;
    }

    @Override
    public Optional<User> authenticateUser(String email, String password) throws LoginException {
        return this.userRepository.authenticateUser(email,password);
    }
}
