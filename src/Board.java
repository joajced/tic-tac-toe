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

    void printInstructions() {
        System.out.println("To play, input the number that corresponds to the square you want to mark. Have fun!\n");
        System.out.println("|1|2|3|\n|4|5|6|\n|7|8|9|\n");
    }

    void printBoard() {
        System.out.println("\n|" + gameState[0][0] + "|" + gameState[0][1] + "|" + gameState[0][2] + "|");
        System.out.println("|" + gameState[1][0] + "|" + gameState[1][1] + "|" + gameState[1][2] + "|");
        System.out.println("|" + gameState[2][0] + "|" + gameState[2][1] + "|" + gameState[2][2] + "|\n");
    }

    void nextTurn() {
        Scanner scanner = new Scanner(System.in);
        byte inputRow, inputCol;

        System.out.print("Select square: ");
        byte inputNum = scanner.nextByte();

        switch (inputNum) {

            case 2 -> {
                inputRow = 0; inputCol = 1;
            }
            case 3 -> {
                inputRow = 0; inputCol = 2;
            }
            case 4 -> {
                inputRow = 1; inputCol = 0;
            }
            case 5 -> {
                inputRow = 1; inputCol = 1;
            }
            case 6 -> {
                inputRow = 1; inputCol = 2;
            }
            case 7 -> {
                inputRow = 2; inputCol = 0;
            }
            case 8 -> {
                inputRow = 2; inputCol = 1;
            }
            case 9 -> {
                inputRow = 2; inputCol = 2;
            }
            default -> {
                inputRow = 0; inputCol = 0;
            }
        }

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
