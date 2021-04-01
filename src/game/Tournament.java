package game;

import java.util.Arrays;
import java.util.Map;

public class Tournament {
    private final int m, n, k, c, win;
    private final Player[] players;
    private final int[] points;

    public Tournament(int m, int n, int k, int win, int c, Player[] players) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.c = c;
        this.win = win;
        this.players = players;
        points = new int[players.length];
    }

    public int firstVSSecond(Board board, Player player1, Player player2) {
        while (true) {
            final int result1 = move(board, player1, 1);
            if (result1 != -1) {
                return result1;
            }
            final int result2 = move(board, player2, 2);
            if (result2 != -1) {
                return result2;
            }
        }
    }

    private int match (Player player1, Player player2) {
        int point1 = 0, point2 = 0;
        while (point1 < win && point2 < win) {
            int result = firstVSSecond(new TicTacToeBoard(n, m, k), player1, player2);
            if (result == 1) {
                point1++;
            } else if (result == 2) {
                point2++;
            }
        }
        return point1 < point2 ? 2 : 1;
    }

    public void play() {
        for (int j = 0; j < c; j++) {
            System.out.println((j + 1) + " circle");
            for (int i = 0; i < players.length; i++) {
                for (int q = i + 1; q < players.length; q++) {
                    System.out.println((i + 1) + " player vs. " + (q + 1) + " player");
                    int res = match (players[i], players[q]);
                    if (res == 1) {
                        System.out.println((i + 1) + " player won");
                        points[i] += 3;
                    } else if (res == 2) {
                        System.out.println((q + 1) + " player won");
                        points[q] += 3;
                    } else if (res == 0) {
                        System.out.println("Draw");
                        points[i]++;
                        points[q]++;
                    }
                }
            }
        }
        for (int i = 0; i < players.length; i++) {
            System.out.println("player №" + (i + 1) + ": " + points[i]);
        }
    }

    private int move(final Board board, final Player player, final int no) {
        final Move move = player.move(board.getPosition(), board.getCell());
        final Result result = board.makeMove(move);
        if (result == Result.WIN) {
            return no;
        } else if (result == Result.LOSE) {
            return 3 - no;
        } else if (result == Result.DRAW) {
            return 0;
        } else {
            return -1;
        }
    }

}
