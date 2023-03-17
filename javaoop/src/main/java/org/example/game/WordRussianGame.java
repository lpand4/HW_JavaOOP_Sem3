package org.example.game;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class WordRussianGame extends AbstractGame{
    @SneakyThrows
    @Override
    List<String> generateCharList() {
        List<String> charList = new ArrayList<String>();
        for (int i = 224; i <= 255; i++) {
            charList.add(new String(String.valueOf((char) i).
                    getBytes("windows-1252"),"windows-1251"));
        }
        System.out.println(charList);
        return charList;
    }

}
