import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Knucklebones {
    private int[] playerOneScore = new int[3];
    private int[] playerTwoScore = new int[3];
    private int playerOneRoll;
    private int playerTwoRoll;
    boolean playerOneTurn;

    Random rn = new Random();
    JFrame fr = new JFrame();
    JPanel title = new JPanel();
    JPanel buttons = new JPanel();
    TextField scores = new TextField();
    JButton[] playerOneButtons = new JButton[9];
    JButton[] playerTwoButtons = new JButton[9];

    

    public Knucklebones(){
        playerOneRoll = rn.nextInt(6) + 1;
        playerTwoRoll = rn.nextInt(6) + 1;

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setMinimumSize(new Dimension(500,600));
        fr.getContentPane().setBackground(new Color(50,50,50));
        fr.setLayout(new BorderLayout());
        fr.setVisible(true);

        scores.setBackground( new Color(25,25,25));
        scores.setForeground(Color.red);
        scores.setSize(60,50);
        scores.setFont(new Font("Fira Code", Font.BOLD, 15));
        scores.setText("Player One: " + Arrays.toString(playerOneScore) + "\nPlayer Two: " + playerTwoScore);
        scores.setVisible(true);
        fr.add(scores, BorderLayout.NORTH);

        buttons.setLayout(new GridLayout(12,3));
        buttons.setSize(500, 600);
        fr.add(buttons);
        for (int i = 0; i < 9; i++){
            playerOneButtons[i] = new JButton();
            playerOneButtons[i].setPreferredSize(new Dimension(100, 100));
            playerOneButtons[i].setForeground(Color.black);
            playerOneButtons[i].setBorder(new LineBorder(Color.BLACK));
            playerOneButtons[i].setMargin(new Insets(50,50,50,50));
            buttons.add(playerOneButtons[i], BorderLayout.NORTH);
            playerOneButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < 9; i++) {
                        if (e.getSource() == playerOneButtons[i]) {
                            if (playerOneButtons[i].getText().equals("")) {
                                playerOneButtons[i].setText(Integer.toString(playerOneRoll));
                                playerOneTurn = false;
                                //playerOneScore += checkRules(i, 1);
                                newRoll(2);
                                scores.setText("Player One: " + playerOneScore + "\nPlayer Two: " + playerTwoScore);
                                //Update JLabels
                            }
                        }
                    }
                    for (int y = 0; y < 9; y++) {
                        playerOneButtons[y].setEnabled(false);
                        playerTwoButtons[y].setEnabled(true);
                    }
                }
            });
        }
        for (int z = 0; z < 3; z++){
           JLabel label = new JLabel(Integer.toString(playerOneScore[z]));
           label.setHorizontalAlignment(JLabel.CENTER);
           label.setFont(new Font("Fira Code", Font.BOLD, 25));
           buttons.add(label, BorderLayout.NORTH);
        }
        for (int i = 0; i < 3; i++){
            buttons.add(new JSeparator(), BorderLayout.NORTH);
        }
        for (int n = 0; n < 3; n++){
            JLabel label = new JLabel(Integer.toString(playerTwoScore[n]));
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setFont(new Font("Fira Code", Font.BOLD, 25));
            buttons.add(label, BorderLayout.NORTH);
        }
        for (int i = 0; i < 9; i++){
            playerTwoButtons[i] = new JButton();
            playerTwoButtons[i].setPreferredSize(new Dimension(200,200));
            buttons.add(playerTwoButtons[i], BorderLayout.NORTH);
            playerTwoButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < 9; i++) {
                        if (e.getSource() == playerTwoButtons[i]) {
                            if (playerTwoButtons[i].getText().equals("")) {
                                playerTwoButtons[i].setText(Integer.toString(playerTwoRoll));
                                playerOneTurn = false;
                                //playerTwoScore +=  checkRules(i, 2);
                                newRoll(1);
                                scores.setText("Player One: " + playerOneScore + "\nPlayer Two: " + playerTwoScore);
                                for (int y = 0; y < 9; y++) {
                                    playerTwoButtons[y].setEnabled(false);
                                    playerOneButtons[y].setEnabled(true);
                                }
                            }
                        }
                    }
                }
            });
        }
        buttons.setVisible(true);
        buttons.updateUI();
    }

    private void newRoll(int player){
        if (player == 1){
            playerOneRoll = rn.nextInt(6) + 1;
        } else {
            playerTwoRoll = rn.nextInt(6) + 1;
        }
    }

    public int checkRules(int position, int player){
        if (player == 1){
            if (Objects.equals(playerOneButtons[position + 3].getText(), playerOneButtons[position].getText())) {
                if (Objects.equals(playerOneButtons[position + 6].getText(), playerOneButtons[position].getText())) {
                    return Integer.parseInt(playerOneButtons[position].getText()) * 2;
                } else {
                    return Integer.parseInt(playerOneButtons[position].getText()) * 2;
                }
            } else {
                return Integer.parseInt(playerOneButtons[position].getText());
            }
        } else {
                if (Objects.equals(playerTwoButtons[position + 3].getText(), playerTwoButtons[position].getText())) {
                    if (Objects.equals(playerTwoButtons[position + 6].getText(), playerTwoButtons[position].getText())) {
                        return Integer.parseInt(playerTwoButtons[position].getText()) * 2;
                    } else {
                        return Integer.parseInt(playerTwoButtons[position].getText()) * 2;
                    }
                } else {
                    return Integer.parseInt(playerTwoButtons[position].getText());
                }
        }
    }

    private void firstGo(){
        playerOneTurn = rn.nextInt(2) == 0;
    }
}
