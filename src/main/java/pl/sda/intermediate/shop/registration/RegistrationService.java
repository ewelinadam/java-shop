package pl.sda.intermediate.shop.registration;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    @Qualifier (value = "DBUserDAO")
    private UserDAO userDAO = null;

    public void register(RegistrationDTO registrationDTO) {

        if (userDAO.checkIfUserExistsByEmail(registrationDTO.getEMail())) {
            throw new UserExistsException(registrationDTO.getEMail());
        }

        User user = createUser(registrationDTO);

        user.setPasswordHash(DigestUtils.sha512Hex(registrationDTO.getPassword()));

        userDAO.addNewUser(user);

    }

    private User createUser(RegistrationDTO registrationDTO) {

        UserAddress userAddress = new UserAddress();
        userAddress.setCity(registrationDTO.getCity());
        userAddress.setCountry(registrationDTO.getCountry());
        userAddress.setStreet(registrationDTO.getStreet());
        userAddress.setZipCode(registrationDTO.getZipCode());

        User user = new User();
        user.setFirstName(registrationDTO.getFirstName());
        user.setLastName(registrationDTO.getLastName());
        user.setEMail(registrationDTO.getEMail());
        user.setBirthDate(registrationDTO.getBirthDate());
        user.setPesel(registrationDTO.getPesel());
        user.setPhone(registrationDTO.getPhone());
        user.setPreferEmails(registrationDTO.getPreferEmails());
        user.setAddress(userAddress);
        return user;

    }

}
