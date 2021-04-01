package game;

import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Добро пожаловать в турнир по крестикам-ноликам!\nВведите пожалуйста количество строк поля: ");
        int m = ServiceClass.InputProcessing("строк поля", in, false);
        System.out.println("Введите пожалуйста количество столбцов поля: ");
        int n = ServiceClass.InputProcessing("столбцов поля", in, false);
        System.out.println("Введите пожалуйста количество крестиков и ноликов для выигрыша: ");
        int k = ServiceClass.InputProcessing("крестиков и ноликов для выигрыша", in, false);
        System.out.println("Введите пожалуйста количество побед для выигрыша в матче: ");
        int win = ServiceClass.InputProcessing("побед для выигрыша в матче", in, false);
        System.out.println("Введите пожалуйста количество кругов в турнире: ");
        int c = ServiceClass.InputProcessing("кругов в турнире", in, false);
        System.out.println("Введите пожалуйста количество игроков турнира: ");
        int count = ServiceClass.InputProcessing("игроков турнира", in, true);
        System.out.println("В турнире могут быть рандомный игрок, последовательный игрок и человек\nВведите пожалуйста количество рандомных игроков: ");
        int random = ServiceClass.PlayerProcessing("рандомных игроков", in, count);
        int seq = 0, hum = 0;
        if (count > random) {
            System.out.println("Введите пожалуйста количество последовательных игроков: ");
            seq = ServiceClass.PlayerProcessing("последовательных игроков", in, count - random);
            if (count > random + seq) {
                System.out.println("Количество людей в игре: " + (count - random - seq));
                hum = count - random - seq;
            }
        }
        Player[] players = new Player[count];
        for (int i = 0; i < count; i++) {
            while (random > 0) {
                players[i] = new RandomPlayer();
                random--;
                i++;
            }
            while (seq > 0) {
                players[i] = new SequentialPlayer();
                seq--;
                i++;
            }
            while (hum > 0) {
                players[i] = new HumanPlayer();
                hum--;
                i++;
            }
        }
        final Tournament game = new Tournament(m, n, k, win, c, players);
        //Match game = new Match(3, 3, 3, 3, new RandomPlayer(), new RandomPlayer());
        game.play();
    }
}
