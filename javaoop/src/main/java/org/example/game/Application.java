package org.example.game;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        do {
            System.out.println("1 - Цифры");
            System.out.println("2 - Буквы EN");
            System.out.println("3 - Буквы RU");
            Scanner in = new Scanner(System.in);
            System.out.println("Выберите игру");
            Game game = null;
            int num = in.nextInt();
            switch (num) {
                case 1:
                    game = new NumberGame();
                    break;
                case 2:
                    game = new WordGame();
                    break;
                case 3:
                    game = new WordRussianGame();
                    break;
                default:
                    System.out.println("Такой игры еще не существует");
            }
            System.out.println("Введите длину угадываемого слова: ");
            int size = in.nextInt();
            System.out.println(String.format("На угадывание слова такой длины Вам будет дано %d попыток",(int) (Math.pow(2,size) + 3)));
            game.start(size, (int) (Math.pow(2,size) + 3));
            while (game.getGameStatus().equals(GameStatus.START)) {
                System.out.println("ваш ход");
                String answer = in.next();
                Answer answerGame = game.inputAnswer(answer);
                if(game.getGameStatus() != GameStatus.WIN)
                    System.out.println(String.format("Найдено %d коров и %d быков", answerGame.getCows(), answerGame.getBulls()));
            }
            System.out.println(String.format("Статус игры: %s\n", game.getGameStatus()));
            AbstractGame.countTry = 0;
            AbstractGame.count = 0;
            System.out.println("1 - Cыграть ещё раз");
            System.out.println("2 - Выход");
            int restart = in.nextInt();
            if (restart == 2) break;
            else continue;
        } while (true);
    }
}
