package pl.sda.intermediate.shop.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.sda.intermediate.shop.login.UserContextHolder;
import pl.sda.intermediate.shop.registration.FileUserDAO;
import retrofit2.Retrofit;
import retrofit2.adapter.java8.Java8CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.CompletableFuture;
import pl.sda.intermediate.shop.registration.UserDAO;


@Service
public class WeatherService {

    private String baseUrl = "http://api.openweathermap.org/";
    private String apiKey = "ea900b66f547fd7b23625544873a4200";
    @Autowired
    @Qualifier (value = "DBUserDAO")
    private UserDAO userDAO;

    public WeatherWrapper downloadWeather() {
        String userLoggedIn = UserContextHolder.getUserLoggedIn();
        String cityOfUser = userDAO.findUserByEmail(userLoggedIn)
//                .map(n -> n.getAddress().getCity()).orElseThrow(() -> new RuntimeException("City not found"));
                .map(n -> n.getAddress().getCity()).orElse("Lodz");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(Java8CallAdapterFactory.create())
                .build();

        WeatherMethods weatherMethods = retrofit.create(WeatherMethods.class);
        CompletableFuture<WeatherWrapper> cf = weatherMethods.byCity(cityOfUser, apiKey, "metric", "pl");

        WeatherWrapper weatherWrapper = cf.join();
        return weatherWrapper;

    }

}
