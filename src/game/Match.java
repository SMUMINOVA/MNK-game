package game;

import java.util.Arrays;
import java.util.Map;

public class Match {
    private final int m, n, k, win;
    private final Player player1, player2;
    private int point1, point2;

    public Match(int m, int n, int k, int win, final Player player1, final Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.m = m;
        this.n = n;
        this.k = k;
        this.win = win;
        point1 = 0;
        point2 = 0;
    }

    public int firstVSSecond(Board board) {
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

    public void play() {
        while (point1 < win && point2 < win) {
            int result = firstVSSecond(new TicTacToeBoard(n, m, k));
            if (result == 1) {
                point1++;
            } else if (result == 2) {
                point2++;
            }
        }
        System.out.println(point1 < point2 ? 2 : 1);
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
