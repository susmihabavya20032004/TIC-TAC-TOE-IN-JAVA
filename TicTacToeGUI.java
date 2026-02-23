import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI implements ActionListener {
    JFrame frame = new JFrame("Tic Tac Toe");
    JButton[] buttons = new JButton[9];
    boolean xTurn = true;

    public TicTacToeGUI() {
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            frame.add(buttons[i]);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (!clicked.getText().equals("")) {
            return; // already clicked
        }

        clicked.setText(xTurn ? "X" : "O");
        clicked.setForeground(xTurn ? Color.RED : Color.BLUE);

        if (checkWinner()) {
            JOptionPane.showMessageDialog(frame, (xTurn ? "X" : "O") + " wins!");
            resetBoard();
        } else if (isDraw()) {
            JOptionPane.showMessageDialog(frame, "It's a draw!");
            resetBoard();
        } else {
            xTurn = !xTurn;
        }
    }

    public boolean checkWinner() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 9; i++) {
            board[i / 3][i % 3] = buttons[i].getText();
        }

        // Check rows, columns, diagonals
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].equals("") && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) return true;
            if (!board[0][i].equals("") && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) return true;
        }

        if (!board[0][0].equals("") && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) return true;
        if (!board[0][2].equals("") && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) return true;

        return false;
    }

    public boolean isDraw() {
        for (JButton b : buttons) {
            if (b.getText().equals("")) return false;
        }
        return true;
    }

    public void resetBoard() {
        for (JButton b : buttons) {
            b.setText("");
        }
        xTurn = true;
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
