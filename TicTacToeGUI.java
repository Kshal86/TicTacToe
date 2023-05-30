import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame implements ActionListener {

    private JButton[][] cells;
    private JLabel playerLabel, resultLabel;
    private int currentPlayer;
    private boolean gameEnded;

    public TicTacToeGUI() {
        super("Tic Tac Toe");

        // Initialize the cells
        cells = new JButton[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                cells[row][col] = new JButton("");
                cells[row][col].addActionListener(this);
            }
        }

        // Initialize the labels
        playerLabel = new JLabel("Player 1's turn");
        resultLabel = new JLabel("");

        // Set the layout of the content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // Add the cells to the board panel
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                boardPanel.add(cells[row][col]);
            }
        }

        // Add the components to the content pane
        contentPane.add(playerLabel, BorderLayout.NORTH);
        contentPane.add(boardPanel, BorderLayout.CENTER);
        contentPane.add(resultLabel, BorderLayout.SOUTH);

        // Initialize the game
        currentPlayer = 1;
        gameEnded = false;
    }

    public void actionPerformed(ActionEvent e) {
        if (!gameEnded) {
            JButton cell = (JButton)e.getSource();
            int row = -1, col = -1;

            // Find the row and column of the cell
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (cells[i][j] == cell) {
                        row = i;
                        col = j;
                        break;
                    }
                }
            }

            // Make the move
            if (row != -1 && col != -1 && cells[row][col].getText().isEmpty()) {
                cells[row][col].setText(currentPlayer == 1 ? "X" : "O");
                currentPlayer = currentPlayer == 1 ? 2 : 1;
                playerLabel.setText("Player " + currentPlayer + "'s turn");
                if (checkWin()) {
                    resultLabel.setText("Player " + (currentPlayer == 1 ? 2 : 1) + " wins!");
                    gameEnded = true;
                } else if (checkTie()) {
                    resultLabel.setText("It's a tie!");
                    gameEnded = true;
                }
            }
        }
    }

    private boolean checkWin() {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (!cells[row][0].getText().isEmpty() &&
                    cells[row][0].getText(). equals(cells[row][1].getText()) &&
                    cells[row][0].getText().equals(cells[row][2].getText())) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (!cells[0][col].getText().isEmpty() &&
                    cells[0][col].getText().equals(cells[1][col].getText()) &&
                    cells[0][col].getText().equals(cells[2][col].getText())) {
                return true;
            }
        }

        // Check diagonals
        if (!cells[0][0].getText().isEmpty() &&
                cells[0][0].getText().equals(cells[1][1].getText()) &&
                cells[0][0].getText().equals(cells[2][2].getText())) {
            return true;
        }

        if (!cells[0][2].getText().isEmpty() &&
                cells[0][2].getText().equals(cells[1][1].getText()) &&
                cells[0][2].getText().equals(cells[2][0].getText())) {
            return true;
        }

        return false;
    }

    private boolean checkTie() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (cells[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TicTacToeGUI frame = new TicTacToeGUI();
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}