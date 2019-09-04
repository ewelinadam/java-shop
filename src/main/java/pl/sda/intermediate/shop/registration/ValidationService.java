package pl.sda.intermediate.shop.registration;

/**
 * Imię jest wymagane. Przynajmniej 3 znaki oraz pierwsza duża, reszta małe.
 * Nazwisko jest wymagane. Przynajmniej 3 znaki oraz pierwsza duża, reszta małe
 *     + dopuszczenie "-" i drugiego członu z dużej litery
 * Zły format. Kod pocztowy powinien mieć format 12-345.
 * Podanie nazwy kraju jest wymagane.
 * Podanie nazwy ulicy jest wymagane.
 * Zły format. Data urodzin powinna być podana w formacie RRRR-MM-DD
 * Zły format. Numer PESEL powinien składać się z 11 cyfr.
 * Zły format adresu email
 * Hasło jest wymagane. Musi zawierać od 10 do 20 znaków.
 * Minimum jedna duża, jedna mała litera i jedna cyfra.
 * Zły format. Numer telefonu powinien składać się z 9 cyfr.
 *     -> możliwa opcja z "+48" na początku",
 *     xxx xxx xxx (spacje) lub xxx-xxx-xxx
 * +48 123123123
 * +48 123-123-123
 * 123123123
 * 123-123-123
 */

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidationService {

    public Map<String, String> validateUserData(RegistrationDTO registrationDTO) {

        Map<String, String> errorsMap = new HashMap<>();

        if (registrationDTO.getFirstName() == null || !registrationDTO.getFirstName().matches("^[A-Z][a-z]{2,}$")) {
            errorsMap.put("firstNameValRes", "Pole imię ma niepoprawny format.");
        }

        if (registrationDTO.getLastName() == null || !registrationDTO.getLastName().matches("^[A-Z][a-z]{2,}(-[A-Z][a-z]{2,})?$")) {
            errorsMap.put("lastNameValRes", "Pole nazwisko ma niepoprawny format.");
        }

        if (registrationDTO.getZipCode() == null || !registrationDTO.getZipCode().matches("^[0-9]{2}-[0-9]{3}$")) {
            errorsMap.put("zipCodeValRes", "Kod pocztowy ma niepoprawny format.");
        }

        if (StringUtils.isBlank(registrationDTO.getCountry())) {
            errorsMap.put("countryValRes", "Kraj jest wymagany.");
        }

        if (StringUtils.isBlank(registrationDTO.getStreet())) {
            errorsMap.put("streetValRes", "Ulica jest wymagany.");
        }

        if (registrationDTO.getPesel() == null || !registrationDTO.getPesel().matches("^[0-9]{11}$")) {
            errorsMap.put("peselValRes", "PESEL ma niepoprawny format.");
        }

        if (registrationDTO.getEMail() == null || !registrationDTO.getEMail().matches("^([\\w.]{3,})@([a-z0-9]+)(\\.[a-z]{2,}){1,2}$")) {
            errorsMap.put("eMailValRes", "Email ma niepoprawny format.");
        }

        if (registrationDTO.getPassword() == null || !registrationDTO.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{10,20}$")) {
            errorsMap.put("passwordValRes", "Hasło ma niepoprawny format.");
        }

        if (registrationDTO.getPhone() == null || !registrationDTO.getPhone().matches("^(\\+[0-9]{1,3} )?(([0-9]{3})(-?)){2}([0-9]{3})$")) {
            errorsMap.put("phoneValRes", "Nr telefonu ma niepoprawny format.");
        }

        if (StringUtils.isBlank(registrationDTO.getCity())) {
            errorsMap.put("cityValRes", "Miasto jest wymagane.");
        }

        return errorsMap;
    }

}
