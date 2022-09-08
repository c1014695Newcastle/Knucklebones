import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Knucklebones {

    private int playerOneFinalScore = 0;
    private int playerTwoFinalScore = 0;
    private int[] playerOneScore = new int[3];
    private int[] playerTwoScore = new int[3];
    private int playerOneRoll;
    private int playerTwoRoll;
    boolean playerOneTurn;

    Random rn = new Random();
    JFrame fr = new JFrame();
    JPanel buttons = new JPanel();
    JPanel scores = new JPanel();
    JPanel container = new JPanel();
    JButton[] playerOneButtons = new JButton[9];
    JButton[] playerTwoButtons = new JButton[9];

    

    public Knucklebones(){
        playerOneRoll = rn.nextInt(6) + 1;
        playerTwoRoll = rn.nextInt(6) + 1;
        firstGo();

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setMinimumSize(new Dimension(500,900));
        fr.setMaximumSize(new Dimension(500,900));
        fr.getContentPane().setBackground(new Color(252,240,213));
        fr.setLayout(new BorderLayout());
        fr.setVisible(true);



        scores.setLayout(new GridLayout(1,2));
        scores.setBackground( new Color(25,25,25));
        scores.setBackground(new Color(252,240,213));
        scores.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1),BorderFactory.createEmptyBorder(5,5,5,5)));
        scores.setSize(60,50);
        scores.setFont(new Font("Fira Code", Font.BOLD, 15));
        scores.setVisible(true);
        fr.add(scores, BorderLayout.NORTH);
        for (int a = 1; a < 3; a++) {
            JLabel player = new JLabel("Player " + a + ": " + calcScore(1));
            player.setHorizontalAlignment(SwingConstants.LEFT);
            player.setFont(new Font("Noto Mono", Font.BOLD, 15));
            player.setForeground(new Color(177, 13, 17));
            scores.add(player);
        }

        buttons.setLayout(new GridLayout(9,3));
        buttons.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        buttons.setSize(500,900);
        fr.add(buttons);

        /*
         * Add player One buttons
         */
        for (int i = 0; i < 9; i++){
            playerOneButtons[i] = new JButton();
            playerOneButtons[i].setForeground(Color.black);
            playerOneButtons[i].setFont(new Font("Noto Mono", Font.BOLD, 50));
            if (playerOneTurn) {
                playerOneButtons[i].setBackground(new Color(177, 13, 17));
            } else {
                playerOneButtons[i].setBackground(new Color(192,192,192));
            }
            playerOneButtons[i].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5,5,5,5),BorderFactory.createBevelBorder(1)));
            buttons.add(playerOneButtons[i], BorderLayout.NORTH);
            buttons.updateUI();
            playerOneButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < 9; i++) {
                        if (e.getSource() == playerOneButtons[i]) {
                            if (playerOneButtons[i].getText().equals("")) {
                                playerOneButtons[i].setText(Integer.toString(playerOneRoll));
                                playerOneTurn = false;
                                //playerOneFinalScore += checkRules(i, 1);
                                newRoll(2);
                                //Update JLabels
                                for (int y = 0; y < 9; y++) {
                                    playerOneButtons[y].setEnabled(false);
                                    playerOneButtons[y].setBackground(new Color(192,192,192));
                                    playerTwoButtons[y].setEnabled(true);
                                    playerTwoButtons[y].setBackground(new Color(177,13,17));
                                }
                            }
                        }
                    }

                }
            });
            playerOneButtons[i].addMouseListener(new java.awt.event.MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    for (int i = 0; i < 9; i++) {
                        if (e.getSource() == playerOneButtons[i]) {
                            if (playerOneTurn) {
                                playerOneButtons[i].setBackground(new Color(224, 214, 167));
                            } else {
                                playerOneButtons[i].setBackground(new Color(192,192,192));
                            }
                        }
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    for (int i = 0; i < 9; i++) {
                        if (e.getSource() == playerOneButtons[i]) {
                            if (playerOneTurn) {
                                playerOneButtons[i].setBackground(new Color(177, 13, 17));
                            } else {
                                playerOneButtons[i].setBackground(new Color(192,192,192));
                            }
                        }
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

        /*
        Add player Two buttons
         */
        for (int i = 0; i < 9; i++){
            playerTwoButtons[i] = new JButton();
            playerTwoButtons[i].setForeground(Color.black);
            playerTwoButtons[i].setFont(new Font("Noto Mono", Font.BOLD, 50));
            if (!playerOneTurn) {
                playerTwoButtons[i].setBackground(new Color(177, 13, 17));
            } else {
                playerTwoButtons[i].setBackground(new Color(192,192,192));
            }
            playerTwoButtons[i].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5,5,5,5),BorderFactory.createBevelBorder(1)));
            buttons.add(playerTwoButtons[i], BorderLayout.NORTH);
            buttons.updateUI();
            playerTwoButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < 9; i++) {
                        if (e.getSource() == playerTwoButtons[i]) {
                            if (playerTwoButtons[i].getText().equals("")) {
                                playerTwoButtons[i].setText(Integer.toString(playerOneRoll));
                                playerOneTurn = true;
                                //playerTwoFinalScore += checkRules(i, 2);
                                newRoll(1);
                                //Update JLabels
                                for (int y = 0; y < 9; y++) {
                                    playerTwoButtons[y].setEnabled(false);
                                    playerTwoButtons[y].setBackground(new Color(192,192,192));
                                    playerOneButtons[y].setEnabled(true);
                                    playerOneButtons[y].setBackground(new Color(177,13,17));
                                }
                            }
                        }
                    }

                }
            });
            playerTwoButtons[i].addMouseListener(new java.awt.event.MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    for (int i = 0; i < 9; i++) {
                        if (e.getSource() == playerTwoButtons[i]) {
                            if (!playerOneTurn) {
                                playerTwoButtons[i].setBackground(new Color(224, 214, 167));
                            } else {
                                playerTwoButtons[i].setBackground(new Color(192,192,192));
                            }
                        }
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    for (int i = 0; i < 9; i++) {
                        if (e.getSource() == playerTwoButtons[i]) {
                            if (!playerOneTurn) {
                                playerTwoButtons[i].setBackground(new Color(177, 13, 17));
                            } else {
                                playerTwoButtons[i].setBackground(new Color(192,192,192));
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

    private int calcScore(int player){
        if (player == 1){
            return Arrays.stream(playerOneScore).sum();
        } else {
            return Arrays.stream(playerTwoScore).sum();
        }
    }

    public int checkRules(int position, int player){
        if (player == 1){
            checkDuplicates(position,1);
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
            checkDuplicates(position,2);
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

    private void checkDuplicates(int position, int player){
        if (player == 1) {
            if (position == 0 || position == 3 || position == 6 ){
                if (Integer.parseInt(playerOneButtons[0].getText() + playerOneButtons[3].getText() + playerOneButtons[6].getText()) % playerOneRoll == 0){

                }
            }
            if (position == 1 || position == 4 || position == 7 ){
                if (Integer.parseInt(playerOneButtons[1].getText() + playerOneButtons[4].getText() + playerOneButtons[7].getText()) % playerOneRoll == 0){

                }
            }
            if (position == 2 || position == 5 || position == 8 ){
                if (Integer.parseInt(playerOneButtons[2].getText() + playerOneButtons[5].getText() + playerOneButtons[8].getText()) % playerOneRoll == 0){

                }
            }
        } else {
            for (int x = 0; x < 3; x++) {
                if (Objects.equals(playerTwoButtons[position].getText(), playerOneButtons[position + (x * 3) - 1].getText())) {
                    playerOneButtons[position].setText("");
                    playerOneFinalScore += Integer.parseInt(playerTwoButtons[position].getText());
                }
            }
        }
    }

    private void updateScores(){

    }

    private void firstGo(){
        playerOneTurn = rn.nextInt(2) == 0;
    }
}
