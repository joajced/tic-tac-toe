import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Board {

    private final char[][] gameState;
    private final int[] fullSquares;
    private Player currentPlayer;

    public Board() {
        gameState = new char[3][3];
        for (char[] row : gameState) {
            Arrays.fill(row, ' ');
        }

        fullSquares = new int[9];
        Arrays.fill(fullSquares, -1);
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
        int inputRow, inputCol;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int inputNum;
            System.out.print("Select square: ");

            try {
                inputNum = scanner.nextInt();
            }
            catch (InputMismatchException ime) {
                System.out.println("Your input is not a valid number. Try again!\n");
                scanner.nextLine();
                continue;
            }

            // Check if square is full
            if (inputNum == -1) {
                System.out.println("Your input is not a valid number. Try again!\n");
                scanner.nextLine();
                continue;
            }
            boolean foundSquare = Arrays.stream(fullSquares).anyMatch(i -> i == inputNum);

            if (!foundSquare) {
                try {
                    fullSquares[inputNum - 1] = inputNum;
                }
                catch (IndexOutOfBoundsException iobe) {
                    System.out.println("You input a wrong number. Try again!\n");
                    scanner.nextLine();
                    continue;
                }

                // Translate input to rows and columns
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

                // Mark the square
                char currentSymbol = currentPlayer.getSymbol();
                gameState[inputRow][inputCol] = currentSymbol;

                break;
            }

            else System.out.println("That square has already been marked. Try again!\n");
        }
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
        int diagSeries = 0;
        for (int r = 0; r < 3; r++) {
            if (gameState[r][r] == currentSymbol) diagSeries++;
        }
        if (diagSeries == 3) return true;

        diagSeries = 0;
        for (int r = 0; r < 3; r++) {
            if (gameState[r][2 - r] == currentSymbol) diagSeries++;
        }
        return diagSeries == 3;
    }
}
