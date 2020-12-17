import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.*;
import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) throws IOException {
        LocalDateTime previoustime = LocalDateTime.now();
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        TelegramBot send = new TelegramBot();
        while(true){
            LocalDateTime timePoint = LocalDateTime.now();
            if(timePoint.getHour() == 11 && timePoint.getMinute() == 0 && timePoint.getSecond() == 0 && previoustime.getSecond() != 0) {
                try {
                    File file = new File("usersid.txt");
                    FileReader fr = new FileReader(file);
                    BufferedReader reader = new BufferedReader(fr);
                    String line = reader.readLine();
                    while (line != null) {
                        send.SendMsg(line);
                        line = reader.readLine();
                    }
                } catch (FileNotFoundException | TelegramApiException e) {
                    e.printStackTrace();
                }
                previoustime = LocalDateTime.now();
            }
        }
    }
}
