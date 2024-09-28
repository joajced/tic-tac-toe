public class TicTacToe {
    public static void main(String[] args) {

        Player p1 = new Player("Player 1", 'X');
        Player p2 = new Player("Player 2", 'O');
        Board board = new Board();
        int moves = 1;

        board.printInstructions();

        while (moves < 10) {
            if (moves % 2 == 1) board.setCurrentPlayer(p1);
            else board.setCurrentPlayer(p2);

            String currentName = board.getCurrentPlayer().getName();
            char currentSymbol = board.getCurrentPlayer().getSymbol();
            System.out.println("It's " + currentName + "'s turn (" + currentSymbol + ").");

            board.nextTurn();
            board.printBoard();

            if (board.checkWin()) {
                System.out.println("Game over. " + board.getCurrentPlayer().getName() + " wins!");
                break;
            } else if (!board.checkWin() && moves == 9) {
                System.out.println("It's a draw");
            }

            moves++;
        }
    }
}
