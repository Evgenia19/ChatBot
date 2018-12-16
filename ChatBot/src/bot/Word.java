package bot;

import java.util.HashSet;
import java.util.Set;

public class Word {

    final int length;
    final String word;
    final Set<Character> symbols = new HashSet<>();
    private int newLength;
    private StringBuilder hideWord = new StringBuilder();

    Word(String word) {
        this.word = word;
        length = word.length();
        newLength = length;
        for(Character e: word.toCharArray())
            symbols.add(e);
        setHideWord();
    }

    private boolean checkSymbol(Character smb){
        if(symbols.contains(smb)) {
            for (int i = 0; i < length; i++) {
                if (smb == word.charAt(i)) {
                    addSymbol(smb, i);
                }
            }
            return true;
        }
        return false;
    }

    private void setHideWord() {
        for(int i = 0; i < length; i++)
            hideWord.append(" _");
    }

    public void addSymbol(Character smb, int pos) {
        hideWord.setCharAt(pos * 2 + 1, smb);
        newLength -= 1;
    }

    public int getNewLength(){
        return newLength;
    }

    public String getHideWord() {
        return hideWord.toString();
    }

    public boolean getCheckedSymbol(Character smb) {
        return checkSymbol(smb);
    }
}
