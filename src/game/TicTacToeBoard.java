package game;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class TicTacToeBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;
    private final int k;

    public TicTacToeBoard(int n, int m, int k) {
        this.k = k;
        this.cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        int empty = getColCount() * getRowCount();
        if (!isValid(move)) {
            return Result.LOSE;
        }
        empty--;
        int row = move.getRow();
        int col = move.getColumn();
        cells[row][col] = move.getValue();
        for (int i = -1; i <= 0; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i < 0 || j < 0) {
                    if (checkCells(i, j, move) + checkCells(-i, -j, move) - 1 >= k) {
                        return Result.WIN;
                    }
                }
            }
        }
        if (empty == 0) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    private int checkCells(int iMove, int jMove, Move move) {
        int count = 0;
        int i = move.getRow();
        int j = move.getColumn();

        while (i >= 0 && i < getRowCount() && j >= 0 && j < getColCount() && cells[i][j] == move.getValue()) {
            i += iMove;
            j += jMove;
            count++;
        }
        return count;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < getRowCount()
                && 0 <= move.getColumn() && move.getColumn() < getColCount()
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public int getRowCount() {
        return cells.length;
    }

    @Override
    public int getColCount() {
        return cells[0].length;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int i = 0; i < getColCount(); i++) {
            sb.append(i);
        }
        for (int r = 0; r < getRowCount(); r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < getColCount(); c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}
