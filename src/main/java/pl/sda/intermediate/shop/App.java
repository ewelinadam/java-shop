package pl.sda.intermediate.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Group project from java course by SDA.
 * Aplicattion that enables to register a user, login & logout. It creates categories from database.
 * Users data are saved in database. It shows current weather.
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class);

    }

}
