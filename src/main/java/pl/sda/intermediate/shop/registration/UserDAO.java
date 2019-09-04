package pl.sda.intermediate.shop.registration;

import java.util.Optional;

public interface UserDAO {

    public void addNewUser(User user);

    public boolean checkIfUserExistsByEmail(String eMail);

    public Optional<User> findUserByEmail(String email);
}
