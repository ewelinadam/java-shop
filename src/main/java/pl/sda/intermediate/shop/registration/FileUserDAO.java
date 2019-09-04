package pl.sda.intermediate.shop.registration;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class FileUserDAO implements UserDAO {

    private Map<String, User> usersByEmail = new HashMap<>();
    private String filePath = //"D:\\work\\sda\\javaldz19\\intermediate\\src\\main\\resources\\pageDatabase";
    "C:\\Users\\Piotrek i Ewelina\\java-ldz-19-shop-project\\src\\main\\resources\\pageDatabase";
    {
        this.readFromFile();
    }

    public void addNewUser(User user) {

        usersByEmail.put(user.getEMail(), user);

        try (
                FileOutputStream fos = new FileOutputStream(filePath);
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {

            oos.writeObject(usersByEmail);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean checkIfUserExistsByEmail(String email) {

//        return usersByEmail.stream().anyMatch(u -> u.getEMail().equals(email));
        return usersByEmail.containsKey(email);

    }

    public void readFromFile() {
        try (
                FileInputStream fis = new FileInputStream(filePath);
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {

            Object o = ois.readObject();
            if (o instanceof List) {
                List<User> users = (List<User>) o;
                users.stream()
                        .collect(Collectors.toMap(u -> u.getEMail(), u -> u));
            } else {
                usersByEmail = (Map<String, User>) o;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Optional<User> findUserByEmail(String email) {
        return Optional.ofNullable(usersByEmail.get(email));
    }

    public String findUsercity(String email) {
        return "Lodz";
    }

}
