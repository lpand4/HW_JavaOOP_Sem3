package org.example.game;

import java.util.ArrayList;
import java.util.List;

public class WordGame extends AbstractGame{
    @Override
    List<String> generateCharList() {
        List<String> charList = new ArrayList<String>();
        for (int i = 97; i <= 122; i++) {
            charList.add(String.valueOf(((char) i)));
        }
        return charList;
    }
}
