import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;

    int turns = 0;

    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null); //opens windows at the center of the screen
        frame.setResizable(false); // can not change size of the windows
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ends program on window close
        frame.setLayout(new BorderLayout());

        // setting up text
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial",Font.BOLD,50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        // adding text to text panel and to the frame
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel,BorderLayout.NORTH);

        // setting up actual game panel
        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.GRAY);
        frame.add(boardPanel);

        // adding buttons to the panel
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton tile = new JButton();
                board[i][j]= tile;
                boardPanel.add(tile);

                tile.setBackground(Color.GRAY);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD,120));
                tile.setFocusable(false);
                // tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if(tile.getText() == "") {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                        }
                    }
                });
            }
        }

    }
    void checkWinner(){
        // horizontal check
        for (int i = 0; i < 3; i++) {
            if(board[i][0].getText() == "") continue;

            if(board[i][0].getText() == board[i][1].getText() && board[i][1].getText() == board[i][2].getText()){
                gameOver=true;
                for (int j = 0; j < 3; j++) {
                    setWinner(board[i][j]);

                }
                return;
            }
        }

        // vertical check
        for (int i = 0; i < 3; i++) {
            if(board[0][i].getText() == "") continue;

            if(board[0][i].getText() == board[1][i].getText() && board[1][i].getText() == board[2][i].getText()){
                for (int j = 0; j < 3; j++) {
                    setWinner(board[j][i]);
                }
                gameOver=true;
                return;
            }

        }

        // diagonal check
        if(board[0][0].getText() == board[1][1].getText() && board[1][1].getText()==board[2][2].getText() && board[0][0].getText()!=""){
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }

        // diagonal different side
        if(board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText() && board[2][0].getText() != ""){
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }

        if(turns == 9) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    setTie(board[i][j]);
                }
            }
            gameOver = true;
        }
    }

    void setWinner(JButton tile){
        tile.setForeground(Color.GREEN);
        tile.setBackground(Color.GRAY);
        textLabel.setText(currentPlayer + " is the winner!");

    }

    void setTie(JButton tile){
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!");
    }
}
