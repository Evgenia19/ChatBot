package bot;
import java.util.ArrayList;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Telegram extends TelegramLongPollingBot{
    final String token = "762263340:AAFoaj9m76NXSHuzlvmAuPECW9thZktu86s";
    final String botUserName = "Chat19Bot";
    private MultiUserBot multiUser = new MultiUserBot();

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String msg = update.getMessage().getText();
        String id = update.getMessage().getChatId().toString();
        if(multiUser.addUser(id)) {
            String c = "";
        }
        UserMessage message = multiUser.users(new UserMessage(id, msg));
        SendMessage sendMsg = new SendMessage();
        sendMsg.setChatId(id);
        sendMsg.setText(message.content);
        if(message.commands != null) {
                ReplyKeyboardMarkup keyBoard = setButtons(message.commands);
                sendMsg.setReplyMarkup(keyBoard);
        }
        try {
            execute(sendMsg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getBotToken() {
        return token;
    }

    public synchronized ReplyKeyboardMarkup setButtons(ArrayList<String> comands) {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        for (String comand: comands) {
            KeyboardRow key = new KeyboardRow();
            key.add(comand);
            keyboard.add(key);
        }
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botApi = new TelegramBotsApi();
        try {
            botApi.registerBot(new Telegram());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
