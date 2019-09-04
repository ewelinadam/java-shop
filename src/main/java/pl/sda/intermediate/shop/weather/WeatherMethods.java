package pl.sda.intermediate.shop.weather;

import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.concurrent.CompletableFuture;

public interface WeatherMethods {

    @GET("data/2.5/weather")
    CompletableFuture<WeatherWrapper> byCity(
            @Query("q") String city,
            @Query("appid") String apiKey,
            @Query("units") String units,
            @Query("lang") String language
            );

}
