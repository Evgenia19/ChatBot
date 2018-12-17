package bot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.lang.String;
import java.util.Scanner;

public class Weather {


    private static Logger logger = LogManager.getLogger(Weather.class.getName());

    public static String getWeather(String message, Model model) {
        StringBuilder result = new StringBuilder();;
        try(Scanner in = new Scanner((InputStream) new URL("http://api.openweathermap.org/data/2.5/weather?q="
                + message
                + "&units=metric&appid=6fff53a641b9b9a799cfd6b079f5cd4e").getContent())) {
            while (in.hasNext()) {
                result.append(in.nextLine());
            }
        }
        catch (IOException e) {
            logger.warn("warn error message: " + e.getMessage());
            return "I have a problem";
        }

        String weather = result.toString();
        JSONObject town = new JSONObject(weather);
        model.setName(town.getString("name"));

        JSONObject main = town.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = town.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon((String) obj.get("icon"));
            model.setMain((String) obj.get("main"));
        }

        return "City: " + model.getName() + "\n" +
                "Temperature: " + model.getTemp() + "C" + "\n" +
                "Humidity:" + model.getHumidity() + "%" + "\n" +
                "Main: " + model.getMain() + "\n" +
                "http://openweathermap.org/img/w/" + model.getIcon() + ".png";
    }
}
