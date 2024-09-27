import java.util.Scanner;

public class Board {

    private final char[][] gameState;
    private Player currentPlayer;

    public Board() {
        gameState = new char[][] {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    void printBoard() {
        System.out.println("|" + gameState[0][0] + "|" + gameState[0][1] + "|" + gameState[0][2] + "|");
        System.out.println("|" + gameState[1][0] + "|" + gameState[1][1] + "|" + gameState[1][2] + "|");
        System.out.println("|" + gameState[2][0] + "|" + gameState[2][1] + "|" + gameState[2][2] + "|");
        System.out.println();
    }

    void nextTurn() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter row: ");
        byte inputRow = scanner.nextByte();
        System.out.print("Enter column: ");
        byte inputCol = scanner.nextByte();
        System.out.println();

        char currentSymbol = currentPlayer.getSymbol();
        gameState[inputRow][inputCol] = currentSymbol;
    }

    boolean checkWin() {
        char currentSymbol = currentPlayer.getSymbol();

        // Check horizontally
        for (int r = 0; r < 3; r++) {
            int series = 0;
            for (int c = 0; c < 3; c++) {
                if (gameState[r][c] != currentSymbol) break;
                else series++;
            }
            if (series == 3) return true;
        }

        // Check vertically
        for (int c = 0; c < 3; c++) {
            int series = 0;
            for (int r = 0; r < 3; r++) {
                if (gameState[r][c] != currentSymbol) break;
                else series++;
            }
            if (series == 3) return true;
        }

        // Check diagonally
        int diag_series = 0;
        for (int r = 0; r < 3; r++) {
            if (gameState[r][r] == currentSymbol) diag_series++;
        }
        if (diag_series == 3) return true;

        diag_series = 0;
        for (int r = 0; r < 3; r++) {
            if (gameState[r][2 - r] == currentSymbol) diag_series++;
        }
        return diag_series == 3;
    }
}