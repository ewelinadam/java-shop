package pl.sda.intermediate.json;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonExample {

    @Test
    public void serializeJson() {

        SomeObject someObject = new SomeObject();
        someObject.setName("Adam");
        someObject.setAge(25);
        someObject.setSalary(BigDecimal.valueOf(3000.2));
        List<OtherObject> otherObjects = new ArrayList<>();
        OtherObject otherObject = new OtherObject();
        otherObject.setId(1);
        otherObject.setText("Text");
        otherObjects.add(otherObject);
        OtherObject otherObject2 = new OtherObject();
        otherObject2.setId(2);
        otherObject2.setText("Text2");
        otherObjects.add(otherObject2);
        someObject.setList(otherObjects);
        Map<Integer, String> newMap = new HashMap<>();
        newMap.put(2, "b");
        newMap.put(4, "test");
        someObject.setMap(newMap);

        String json = new Gson().toJson(someObject);
        System.out.println(json);

        SomeObject json2 = new Gson().fromJson(json, SomeObject.class);
        System.out.println(json2);

    }

    @NoArgsConstructor
    @Setter
    private class SomeObject {

        private String name;
        private int age;
        private BigDecimal salary;
        private List<OtherObject> list;
        private Map<Integer, String> map;
        private boolean isItTrue;

    }

    @Setter
    @NoArgsConstructor
    private class OtherObject {

        private int id;
        private String text;

    }

    @Test
    public void nbpTest() throws Exception {

        URL url = new URL("http://api.nbp.pl/api/exchangerates/tables/A/last?format=json");
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String result = "";
        String inputLine;

        while ((inputLine = br.readLine()) != null) {
            result = result + inputLine;
        }

        RatesWrapper[] rw = new Gson().fromJson(result, RatesWrapper[].class);

    }

    @Getter
    @Setter
    private class RatesWrapper {

        private String table;
        private String no;
        private String effectiveDate;
        private List<Rate> rates;

    }

    @Getter
    @Setter
    private class Rate {

        private String currency;
        private String code;
        private Double mid;

    }

}
