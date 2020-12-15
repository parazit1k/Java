import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TelegramBot extends TelegramLongPollingBot {
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


        if(text.equals("/start")){
            message.setText("Здравствуй, " + update.getMessage().getFrom().getFirstName() + ". \nЧтобы пользоваться ботом, нажми на нужные кнопки снизу.\nДля большей информации, нажми сюда --> /help\nЧтобы подписаться, нажми сюда --> /subscribe");
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
        else if(text.equals("/help")){
            message.setText("На самом деле хз что тут можно написать, бот слишком простой, чтобы расписывать, "+ update.getMessage().getFrom().getLastName()+" СОБЕРИСЬ и нажми сюда --> /weather!!!");
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
        else if (text.equals("/weather")){
            Document page = null;
            try {
                page = (Parcing.getWeather());
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert page != null;
            Elements g = page.getElementsByAttributeValue("class","temp__value");
            String weather1 = g.get(0).text();
            System.out.println(weather1);

            message.setText("Сейчас в Москве "+weather1+" градусов.");
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if( text.equals("/subscribe")){
            try(FileWriter writer = new FileWriter("usersid.txt", false))
            {
                String user = update.getMessage().getChatId().toString();
                writer.write(user);
                writer.append('\n');

                writer.flush();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
            message.setText("Вы уже зарегистрированы и будете получать сообщения в 11-00!");
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized  void SendMsg(String chatId){
        System.out.println(chatId);
        Document page = null;
        try {
            page = (Parcing.getWeather());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert page != null;
        Elements g = page.getElementsByAttributeValue("class","temp__value");
        String weather1 = g.get(0).text();
        System.out.println(weather1);
        String s = "Сейчас в Москве " +weather1+ " градусов.";
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
