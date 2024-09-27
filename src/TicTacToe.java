public class TicTacToe {
    public static void main(String[] args) {

        Player p1 = new Player("Player 1", 'X');
        Player p2 = new Player("Player 2", 'O');
        Board board = new Board();
        byte moves = 0;

        // Create method to print initial board
        board.printBoard();

        while (moves < 9) {
            if (moves % 2 == 0) board.setCurrentPlayer(p1);
            else board.setCurrentPlayer(p2);

            String currentName = board.getCurrentPlayer().getName();
            System.out.println("It's " + currentName + "'s turn. ");

            board.nextTurn();
            moves++;
            board.printBoard();
        }

        System.out.println("Game over. Player wins!");
    }
}