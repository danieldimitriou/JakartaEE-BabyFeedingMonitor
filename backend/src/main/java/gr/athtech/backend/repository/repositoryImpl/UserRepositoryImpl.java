package gr.athtech.backend.repository.repositoryImpl;
import gr.athtech.backend.model.User;
import gr.athtech.backend.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;
    private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);
    @Override

    public Optional<User> getUserById(Long id) {
        User user = entityManager.find(User.class, id);
        logger.error(user);
        return Optional.ofNullable(user);
    }

    @Override
    public void createUser(User user) {
        try{
            entityManager.persist(user);
        }catch (Exception e){
            logger.error(e);
        }

    }


    @Override
    public Optional<User> updateUser(User user) {
        return Optional.empty();
    }

    @Override
    public boolean deleteUser(Long id) {
        return false;
    }
}
