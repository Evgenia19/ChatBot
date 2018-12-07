package bot;


import java.util.ArrayList;

interface Game {
    ArrayList<String> getCommands();
    void start();
    String end();
    String getHelp();
}
