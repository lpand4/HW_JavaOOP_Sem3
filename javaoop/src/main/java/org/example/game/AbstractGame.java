package org.example.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractGame implements Game {
    Integer sizeWord;
    String word;
    Integer maxTry;

    static int count = 0;
    static int countTry;
    GameStatus gameStatus = GameStatus.INIT;

    private String generateWord() {
        List<String> charList = generateCharList();
        SecureRandom random = new SecureRandom();
        String res = "";
        for (int i = 0; i < sizeWord; i++) {
            int randomeIndex = random.nextInt(charList.size());
            res += charList.get(randomeIndex);
            charList.remove(randomeIndex);
        }
        return res;
    }

    @Override
    public void start(Integer sizeWord, Integer maxTry) {
        this.maxTry = maxTry;
        this.sizeWord = sizeWord;
        word = generateWord();
        this.gameStatus = GameStatus.START;
    }

    @Override
    public Answer inputAnswer(String value) {
        int bull = 0;
        int cow = 0;
        if(value.length() <= word.length()){
            for (int i = 0; i < value.length(); i++) {
                if (word.contains(Character.toString(value.charAt(i)))) {
                    cow++;
                }
                if (word.charAt(i) == value.charAt(i)) {
                    bull++;
                }
            }
        }
        else{
            System.out.println("Введенное слово больше загаданного, но");
            for (int i = 0; i < word.length(); i++) {
                if (word.contains(Character.toString(value.charAt(i)))) {
                    cow++;
                }
                if (word.charAt(i) == value.charAt(i)) {
                    bull++;
                }
            }
        }
        countTry++;
        Answer answer = new Answer(cow, bull, value);
        count++;
        gameStatus = checkState(value);
        return answer;
    }

    private GameStatus checkState(String value) {
        if (value.equals(word)) {
            System.out.println(String.format("Вы смогли угадать за %d попыток",count));
            return gameStatus = GameStatus.WIN;

        } else {
            if (countTry >= maxTry) {
                return gameStatus = GameStatus.LOSE;
            } else {
                return gameStatus = GameStatus.START;
            }
        }

    }

    abstract List<String> generateCharList();
}

