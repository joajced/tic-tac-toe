import java.util.Scanner;

public class Board {

    private final char[][] gameState;
    private Player currentPlayer;
    private byte moves;

    public Board() {
        gameState = new char[][] {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        moves = 0;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public byte getMoves() {
        return this.moves;
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
        moves++;
    }
}