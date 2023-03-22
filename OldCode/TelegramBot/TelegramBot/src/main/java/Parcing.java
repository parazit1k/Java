import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class Parcing {
    public static Document getWeather() throws IOException {
        return(Jsoup.connect("https://yandex.ru/pogoda/moscow").userAgent("Chrome/4.0.249.0 Safari/532.5").referrer("https://yandex.ru").get());
    }
}

