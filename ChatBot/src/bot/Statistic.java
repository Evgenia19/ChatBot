package bot;

public class Statistic {
    public int score21 = 0;
    public int scoreQuiz = 0;
    public int scoreHang = 0;
    public int game21 = 0;
    public int gameQuiz = 0;
    public  int gameHang = 0;

    public String getStatistic() {
        return String.format("Statistic: \nBlackJack: %s from %s \nQuiz: %s from %s \nHang: %s from %s",
                score21, game21, scoreQuiz, gameQuiz, scoreHang, gameHang);
    }

    public void getStatistic21(int game, int result) {
        game21 = game;
        score21 = result;
    }

    public void getStatisticHang(int game, int result) {
        gameHang = game;
        scoreHang = result;
    }

    public void getStatisticQuiz(int game, int result) {
        gameQuiz = game;
        scoreQuiz = result;
    }
}
