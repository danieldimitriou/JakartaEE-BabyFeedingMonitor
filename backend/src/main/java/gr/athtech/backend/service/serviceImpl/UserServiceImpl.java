package gr.athtech.backend.service.serviceImpl;

import gr.athtech.backend.model.LoginData;
import gr.athtech.backend.model.LoginResponseData;
import gr.athtech.backend.model.User;
import gr.athtech.backend.repository.UserRepository;
import gr.athtech.backend.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = (Logger) LogManager.getLogger(UserServiceImpl.class);

    @Inject
    private UserRepository userRepository;

    @Override
    public Optional<User> getUserById(Long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public boolean createUser(User user) {
        return false;
    }

    @Override
    public Optional<User> updateUser(User user) {
        return Optional.empty();
    }

    @Override
    public boolean deleteUser(Long id) {
        return false;
    }

    @Override
    public LoginResponseData login(LoginData loginData) throws LoginException {
        logger.error("Service login");
        return this.userRepository.login(loginData.getEmail(), loginData.getPassword());
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        logger.error(email);
        return this.userRepository.getUserByEmail(email);
    }
}