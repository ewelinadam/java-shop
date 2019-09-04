package pl.sda.intermediate.shop.registration;

public class UserExistsException extends RuntimeException {

    public UserExistsException(String message) {
        super("User already exists - " + message + "!");
    }

}
