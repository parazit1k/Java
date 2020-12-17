import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TelegramBot extends TelegramLongPollingBot {
    String previousmessage = "";

    public static Map<String, Object> jsonToMap(String str){
        return new Gson().fromJson(
                str, new TypeToken<HashMap<String, Object>>() {}.getType()
        );
    }

    public String getBotUsername() {
        return "WeatherBot";
    }

    public String getBotToken() {
        return "1349927461:AAFmaXxsJo40H5do-wEWbrjQlEifOeDImsY";
    }

    public void onUpdateReceived(Update update) {
        // Принимаемое сообщение

        String text = update.getMessage().getText();
        // Отправляемое сообщение
        SendMessage message = new SendMessage();


        switch (text) {
            case "/start":
                message.setText("Здравствуй, " + update.getMessage().getFrom().getFirstName() + ". \nЧтобы пользоваться ботом, напиши город откуда ты.\nДля большей информации, нажми сюда --> /help\nЧтобы подписаться, нажми сюда --> /subscribe");
                message.setChatId(String.valueOf(update.getMessage().getChatId()));
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                int choose = 0;
                try {
                    String chatId = update.getMessage().getChatId().toString();
                    File file = new File("userstownid.txt");
                    FileReader fr = new FileReader(file);
                    BufferedReader reader = new BufferedReader(fr);
                    String line = reader.readLine();
                    while (line != null) {
                        String[] hz = line.split(" ");
                        if (hz[1].equals(chatId)) {
                            choose = 1;
                        }
                        line = reader.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (choose == 0) {
                    message.setText("А теперь напиши в каком городе ты живёшь:)");
                    message.setChatId(String.valueOf(update.getMessage().getChatId()));
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    previousmessage = "/start";
                } else {
                    message.setText("Братик, так ты уже зареган)");
                    message.setChatId(String.valueOf(update.getMessage().getChatId()));
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "/help":
                message.setText("На самом деле хз что тут можно написать, бот слишком простой, чтобы расписывать, " + update.getMessage().getFrom().getLastName() + " СОБЕРИСЬ и нажми сюда --> /weather!!!");
                message.setChatId(String.valueOf(update.getMessage().getChatId()));
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                previousmessage = "/help";

                break;
            case "/weather":
                Document page = null;
                try {
                    page = (Parcing.getWeather());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert page != null;
                Elements g = page.getElementsByAttributeValue("class", "temp__value");
                String weather1 = g.get(0).text();
                System.out.println(weather1);
                String town = "";
                try {
                    File file = new File("userstownid.txt");
                    FileReader fr = new FileReader(file);
                    BufferedReader reader = new BufferedReader(fr);
                    String line = reader.readLine();
                    while (line != null) {
                        String[] newline = line.split(" ");
                        if (update.getMessage().getChatId().toString().equals(newline[1])) {
                            town = newline[0];
                        }
                        line = reader.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                StringBuilder content = new StringBuilder();
                System.out.println(town);
                String urladress = "https://api.openweathermap.org/data/2.5/weather?q=" + town + "&appid=28cc1aa2c5d38b78ac6d41ab6326279c&units=metric";
                try {
                    URL url = new URL(urladress);
                    URLConnection urlConn = url.openConnection();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((urlConn.getInputStream())));
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    bufferedReader.close();
                } catch (Exception e) {
                    System.out.println("Не найден город");
                }
                Map<String, Object> respMap = jsonToMap(content.toString());
                Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());

                message.setText("Время: " + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + "\nСейчас в городе " + town + " " + mainMap.get("temp") + " градусов." + "\nОщущается, как " + mainMap.get("feels_like") + ".\nМинимальная температура сегодня: " + mainMap.get("temp_min") + ".\nМаксимальная температура сегодня: " + mainMap.get("temp_max") + ".");

                message.setChatId(String.valueOf(update.getMessage().getChatId()));
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                previousmessage = "/weather";
                break;
            case "/subscribe": {
                int k = 0;
                try {
                    File file = new File("usersid.txt");
                    FileReader fr = new FileReader(file);
                    BufferedReader reader = new BufferedReader(fr);
                    String line = reader.readLine();
                    while (line != null) {
                        if (update.getMessage().getChatId().toString().equals(line)) {
                            k++;
                        }
                        line = reader.readLine();
                    }
                } catch (IOException ex) {

                    System.out.println(ex.getMessage());
                }
                if (k == 0) {
                    try (FileWriter writer = new FileWriter("usersid.txt", true)) {
                        String user = update.getMessage().getChatId().toString();
                        writer.write(user);
                        writer.append('\n');

                        writer.flush();
                    } catch (IOException ex) {

                        System.out.println(ex.getMessage());
                    }
                    message.setText("Вы успешно зарегистрированы и будете получать сообщения в 11-00!");
                    message.setChatId(String.valueOf(update.getMessage().getChatId()));
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    message.setText("Вы уже зарегистрированы и будете получать сообщения в 11-00!");
                    message.setChatId(String.valueOf(update.getMessage().getChatId()));
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                previousmessage = "/subscribe";
                break;
            }
            case "/unsubscribe": {
                int k = 0;
                try {
                    File file = new File("usersid.txt");
                    FileReader fr = new FileReader(file);
                    BufferedReader reader = new BufferedReader(fr);
                    String line = reader.readLine();
                    while (line != null) {
                        if (update.getMessage().getChatId().toString().equals(line)) {
                            k++;
                        }
                        line = reader.readLine();
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                if (k > 0) {
                    StringBuilder newfile = new StringBuilder();
                    try {
                        File file = new File("usersid.txt");
                        FileReader fr = new FileReader(file);
                        BufferedReader reader = new BufferedReader(fr);
                        String line = reader.readLine();
                        while (line != null) {
                            if (!update.getMessage().getChatId().toString().equals(line)) {
                                newfile.append(line);
                            }
                            line = reader.readLine();
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    try (FileWriter writer = new FileWriter("usersid.txt", false)) {
                        newfile.append("\n");
                        writer.write(String.valueOf(newfile));
                        writer.flush();
                    } catch (IOException ex) {

                        System.out.println(ex.getMessage());
                    }
                    message.setText("Вы успешно отписались от рассылки, возвращайся, " + update.getMessage().getFrom().getFirstName() + "!");
                    message.setChatId(String.valueOf(update.getMessage().getChatId()));
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    message.setText("Так ты ещё не подписался, так что сначала подпишись, а потом отписывайся!");
                    message.setChatId(String.valueOf(update.getMessage().getChatId()));
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            case "/change": {
                int k = 0;
                try {
                    File file = new File("userstownid.txt");
                    FileReader fr = new FileReader(file);
                    BufferedReader reader = new BufferedReader(fr);
                    String line = reader.readLine();
                    while (line != null) {
                        if (update.getMessage().getChatId().toString().equals(line.split(" ")[1])) {
                            k++;
                        }
                        line = reader.readLine();
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                if (k > 0) {
                    StringBuilder newfile = new StringBuilder();
                    try {
                        File file = new File("userstownid.txt");
                        FileReader fr = new FileReader(file);
                        BufferedReader reader = new BufferedReader(fr);
                        String line = reader.readLine();
                        while (line != null) {
                            if (!update.getMessage().getChatId().toString().equals(line.split(" ")[1])) {
                                newfile.append(line);
                            }
                            line = reader.readLine();
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    try (FileWriter writer = new FileWriter("userstownid.txt", false)) {
                        newfile.append("\n");
                        writer.write(String.valueOf(newfile));
                        writer.flush();
                    } catch (IOException ex) {

                        System.out.println(ex.getMessage());
                    }
                    message.setText("Теперь введи город!");
                    message.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    previousmessage = "/change";
                } else {
                    message.setText("Так ты ещё не ввёл город!\nНапиши --> /start");
                    message.setChatId(String.valueOf(update.getMessage().getChatId()));
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
        if (previousmessage.equals("/start") || previousmessage.equals("/change")){
            if(!text.equals("/start") && !text.equals("/change")){
                try (FileWriter writer = new FileWriter("userstownid.txt", true)) {
                    String town = update.getMessage().getText() + " " + update.getMessage().getChatId();
                    writer.write(town);
                    writer.append('\n');
                    writer.flush();
                    message.setText("Ты успешно изменил город на " + text);
                    message.setChatId(String.valueOf(update.getMessage().getChatId()));
                    execute(message);
                } catch (IOException | TelegramApiException ex) {
                    System.out.println(ex.getMessage());
                }
                previousmessage = "";
            }
        }
        System.out.println(text + " " + previousmessage);
    }

    public synchronized  void SendMsg(String chatId) throws TelegramApiException {
        String town = "";
        try
        {
            File file = new File("userstownid.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] newline = line.split(" ");
                if(chatId.equals(newline[1])){
                    town = newline[0];
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder content = new StringBuilder();
        System.out.println(town);
        String urladress = "https://api.openweathermap.org/data/2.5/weather?q=" + town + "&appid=28cc1aa2c5d38b78ac6d41ab6326279c&units=metric";
        try{
            URL url = new URL(urladress);
            URLConnection urlConn = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((urlConn.getInputStream())));
            String line;

            while((line = bufferedReader.readLine())!= null){
                content.append(line).append("\n");
            }
            bufferedReader.close();
        }catch(Exception e){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("Такого города нет:(");

            sendMessage.setChatId(chatId);
            execute(sendMessage);
            System.out.println("Не найден город");
        }
        Map<String, Object> respMap = jsonToMap(content.toString());
        Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Время: " + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + "\nСейчас в городе "+ town + " " +mainMap.get("temp")+" градусов." +"\nОщущается, как " + mainMap.get("feels_like") + ".\nМинимальная температура сегодня: " + mainMap.get("temp_min") + ".\nМаксимальная температура сегодня: " + mainMap.get("temp_max") + ".");

        sendMessage.setChatId(chatId);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
