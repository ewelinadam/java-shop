package pl.sda.intermediate.shop.login;

import org.springframework.stereotype.Service;

@Service
public class UserContextHolder {

    private static String login;

    public static void addLoggedUser(LoginDTO loginDTO) {
        login = loginDTO.getLogin();
    }

    public static void delLoggedUser() {
        login = null;
    }

    public static String getUserLoggedIn() {
        return login;
    }

}
