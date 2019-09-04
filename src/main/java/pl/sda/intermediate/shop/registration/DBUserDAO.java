package pl.sda.intermediate.shop.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
//@Primary --> dodana adnotacja
public class DBUserDAO implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void addNewUser(User user) {
        entityManager.persist(user.getAddress());
        entityManager.persist(user);
    }

    @Override
    public boolean checkIfUserExistsByEmail(String eMail) {
        List<User> listUsers = entityManager
                .createQuery("FROM User where eMail = :eMail", User.class)
                .setParameter("eMail", eMail)
                .getResultList();
        return !listUsers.isEmpty();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        List<User> users = entityManager
                .createQuery("FROM User WHERE eMail = :eMail", User.class)
                .setParameter("eMail", email).getResultList();
        if(users.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(users.get(0));
    }
}
