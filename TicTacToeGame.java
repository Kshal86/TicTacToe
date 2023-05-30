import java.util.Scanner;

public class TicTacToeGame{

    static int[][] board = new int[3][3];
    static Scanner scanner = new Scanner(System.in);

    public static void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }
    }

    public static void displayBoard() {
        System.out.println("  1 2 3");
        for (int i = 0; i < 3; i++) {
            System.out.print((i+1) + " ");
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    System.out.print(" ");
                } else if (board[i][j] == 1) {
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }
                if (j < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("  -----");
            }
        }
    }

    public static boolean makeMove(int player, int row, int col) {
        if (board[row-1][col-1] == 0) {
            board[row-1][col-1] = player;
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkWin(int player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        // No winner
        return false;
    }

    public static boolean checkTie() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        initBoard();
        displayBoard();

        int currentPlayer = 1;
        boolean gameEnded = false;

        while (!gameEnded) {
            // Prompt player to make a move and update the game board
            System.out.println("Player " + currentPlayer + ", enter your move (row column):");
            boolean validMove = false;
            while (!validMove) {
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                validMove = makeMove(currentPlayer, row, col);
                if (!validMove) {
                    System.out.println("Invalid move. Try again.");
                }
            }
            
            displayBoard();

            if (checkWin(currentPlayer)) {
                // Declare the current player as the winner and end the game
                System.out.println("Player " + currentPlayer + " wins!");
                gameEnded = true;
            } else if (checkTie()) {
                // Declare a tie and end the game
                System.out.println("It's a tie!");
                gameEnded = true;
            } else {
                // Switch to the other player
                currentPlayer = currentPlayer == 1 ? 2 : 1;
            }
        }
    }
}