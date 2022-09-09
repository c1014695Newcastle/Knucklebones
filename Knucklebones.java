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
    private JLabel[] scorePanel = new JLabel[6];
    private int playerOneRoll;
    private int playerTwoRoll;
    boolean playerOneTurn;

    Random rn = new Random();
    JLabel[] scoreboard = new JLabel[2];
    JLabel diceRoll = new JLabel();
    JFrame fr = new JFrame();
    JPanel buttons = new JPanel();
    JPanel scores = new JPanel();
    JPanel container = new JPanel();
    JButton[][] playerOneButtons = new JButton[3][3];
    JButton[][] playerTwoButtons = new JButton[3][3];

    

    public Knucklebones(){
        playerOneRoll = rn.nextInt(6) + 1;
        playerTwoRoll = rn.nextInt(6) + 1;
        firstGo();

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setTitle("KNUCKLEBONES");
        fr.setMinimumSize(new Dimension(500,900));
        fr.setMaximumSize(new Dimension(500,900));
        fr.getContentPane().setBackground(new Color(252,240,213));
        fr.setLayout(new BorderLayout());
        fr.setVisible(true);

        title.setText("KNUCKLEBONES");
        title.setSize(500,50);
        title.setFont((new Font("Noto Mono", Font.BOLD, 15)));
        title.setBackground(Color.BLACK);
        title.setForeground(new Color(177, 13, 17));
        title.setLayout(new GridLayout(1,1));
        title.setVisible(true);
        fr.add(title,BorderLayout.NORTH);


        scores.setLayout(new GridLayout(1,2));
        scores.setBackground( new Color(25,25,25));
        scores.setBackground(new Color(252,240,213));
        scores.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1),BorderFactory.createEmptyBorder(5,5,5,5)));
        scores.setSize(60,50);
        scores.setFont(new Font("Fira Code", Font.BOLD, 15));
        scores.setVisible(true);
        fr.add(scores, BorderLayout.NORTH);
        for (int a = 0; a < 2; a++) {
            scoreboard[a] = new JLabel("Player " + (a+1) + ": " + calcScore((a + 1)));
            scoreboard[a].setHorizontalAlignment(SwingConstants.LEFT);
            scoreboard[a].setFont(new Font("Noto Mono", Font.BOLD, 15));
            scoreboard[a].setForeground(new Color(177, 13, 17));
            scores.add(scoreboard[a]);
        }

        buttons.setLayout(new GridLayout(9,3));
        buttons.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        buttons.setSize(500,900);
        fr.add(buttons);

        /*
         * Add player One buttons
         */
        for (int i = 0; i < 3; i++){
            for (int j = 0; j< 3; j++) {
                playerOneButtons[i][j] = new JButton();
                playerOneButtons[i][j].setForeground(Color.black);
                playerOneButtons[i][j].setFont(new Font("Noto Mono", Font.BOLD, 50));
                if (playerOneTurn) {
                    playerOneButtons[i][j].setBackground(new Color(177, 13, 17));
                } else {
                    playerOneButtons[i][j].setBackground(new Color(192, 192, 192));
                }
                playerOneButtons[i][j].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createBevelBorder(1)));
                buttons.add(playerOneButtons[i][j], BorderLayout.NORTH);
                buttons.updateUI();
                playerOneButtons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (e.getSource() == playerOneButtons[i][j]) {
                                    if (playerOneButtons[i][j].getText().equals("")) {
                                        playerOneButtons[i][j].setText(Integer.toString(playerOneRoll));
                                        playerOneTurn = false;
                                        playerOneFinalScore += checkRules(i, j,1, playerOneScore);
                                        newRoll(2);
                                        diceRoll.setText(Integer.toString(playerTwoRoll));
                                        //Update JLabels
                                        updateColumnScores();
                                        updateScoreBoard();
                                        for (int x = 0; x < 3; x++) {
                                            for (int y = 0; y < 3; y++) {
                                                playerOneButtons[x][y].setEnabled(false);
                                                playerOneButtons[x][y].setBackground(new Color(192, 192, 192));
                                                playerTwoButtons[x][y].setEnabled(true);
                                                playerTwoButtons[x][y].setBackground(new Color(177, 13, 17));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }

        for (int z = 0; z < 3; z++){
            scorePanel[z] = new JLabel(Integer.toString(playerOneScore[z]));
            scorePanel[z].setHorizontalAlignment(JLabel.CENTER);
            scorePanel[z].setFont(new Font("Fira Code", Font.BOLD, 25));
           buttons.add(scorePanel[z], BorderLayout.NORTH);
        }

        buttons.add(new JSeparator(), BorderLayout.NORTH);
        if (playerOneTurn) {
            diceRoll.setText(Integer.toString(playerOneRoll));
        } else {
            diceRoll.setText(Integer.toString(playerTwoRoll));
        }
        diceRoll.setFont(new Font("Noto Mono", Font.BOLD, 50));
        diceRoll.setHorizontalAlignment(SwingConstants.CENTER);
        buttons.add(diceRoll);
        buttons.add(new JSeparator(), BorderLayout.NORTH);

        for (int n = 3; n < 6; n++){
            scorePanel[n] = new JLabel(Integer.toString(playerTwoScore[n-3]));
            scorePanel[n].setHorizontalAlignment(JLabel.CENTER);
            scorePanel[n].setFont(new Font("Fira Code", Font.BOLD, 25));
            buttons.add(scorePanel[n], BorderLayout.NORTH);
        }

        /*
        Add player Two buttons
         */
        for (int i = 0; i < 3; i++){
            for (int j = 0; j<3; j++) {
                playerTwoButtons[i][j] = new JButton();
                playerTwoButtons[i][j].setForeground(Color.black);
                playerTwoButtons[i][j].setFont(new Font("Noto Mono", Font.BOLD, 50));
                if (!playerOneTurn) {
                    playerTwoButtons[i][j].setBackground(new Color(177, 13, 17));
                } else {
                    playerTwoButtons[i][j].setBackground(new Color(192, 192, 192));
                }
                playerTwoButtons[i][j].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createBevelBorder(1)));
                buttons.add(playerTwoButtons[i][j], BorderLayout.NORTH);
                buttons.updateUI();
                playerTwoButtons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (e.getSource() == playerTwoButtons[i][j]) {
                                    if (playerTwoButtons[i][j].getText().equals("")) {
                                        playerTwoButtons[i][j].setText(Integer.toString(playerTwoRoll));
                                        playerOneTurn = true;
                                        playerTwoFinalScore += checkRules(i,j,2, playerTwoScore);
                                        newRoll(1);
                                        diceRoll.setText(Integer.toString(playerOneRoll));
                                        //Update JLabels
                                        updateColumnScores();
                                        updateScoreBoard();
                                        for (int x = 0; x < 3; x++) {
                                            for (int y = 0; y < 3; y++) {
                                                playerTwoButtons[x][y].setEnabled(false);
                                                playerTwoButtons[x][y].setBackground(new Color(192, 192, 192));
                                                playerOneButtons[x][y].setEnabled(true);
                                                playerOneButtons[x][y].setBackground(new Color(177, 13, 17));
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                });
               
            }
        }
        buttons.setVisible(true);
        buttons.updateUI();
    }
    
    private void addMouseControl(JButton b, int player){
        b.addMouseListener(new java.awt.event.MouseListener() {

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
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (e.getSource() == b) {
                            if ((playerOneTurn && player == 1) || (!playerOneTurn && player == 2)) {
                                b.setBackground(new Color(224, 214, 167));
                            } else {
                                b.setBackground(new Color(192, 192, 192));
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (e.getSource() == b) {
                            if ((playerOneTurn && player == 1) || (!playerOneTurn && player == 2)) {
                                b.setBackground(new Color(177, 13, 17));
                            } else {
                                b.setBackground(new Color(192, 192, 192));
                            }
                        }
                    }
                }
            }
        });
    }
    
    private void switchPlayers(JButton[][] playerButtons, JButton[][] opponentButtons){
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                playerButtons[x][y].setEnabled(false);
                playerButtons[x][y].setBackground(new Color(192, 192, 192));
                opponentButtons[x][y].setEnabled(true);
                opponentButtons[x][y].setBackground(new Color(177, 13, 17));
            }
        }
    }
    
    private void styleButton(JButton b, boolean firstTurn){
        b.setForeground(Color.black);
        b.setFont(new Font("Noto Mono", Font.BOLD, 50));
        if (firstTurn) {
            b.setBackground(new Color(177, 13, 17));
        } else {
            b.setEnabled(false);
            b.setBackground(new Color(192, 192, 192));
        }
        b.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createBevelBorder(1)));
    }

    private void updateScoreBoard(){
        for (int i = 0; i < 2; i++){
            scoreboard[i].setText("Player " + (i+1) + ": " + calcScore((i + 1)));
        }
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

    public int checkRules(int x, int y, int player, int[] playerScore){
        int points = 0;
        //Check if any of the player's rolls are the same
        //check if any if the player's rolls match the opposite player's
        if (player == 1) {
            points = checkDuplicates(x, y, 1, playerScore);
            for (int yCord = 0; yCord < 3; yCord++){
                if (playerOneButtons[x][y].getText().equals(playerTwoButtons[x][yCord].getText())){
                    playerTwoButtons[x][yCord].setText("");
                    playerTwoScore[yCord] -= Integer.parseInt(playerOneButtons[x][y].getText());
                    if ( playerTwoScore[yCord] <= 0){
                        playerTwoScore[yCord] = 0;
                    }
                }
            }
        } else {
            points = checkDuplicates(x, y, 2, playerScore);
            for (int yCord = 0; yCord < 3; yCord++){
                if (playerTwoButtons[x][y].getText().equals(playerOneButtons[x][yCord].getText())){
                    playerOneButtons[x][yCord].setText("");
                    playerOneScore[yCord] -= Integer.parseInt(playerOneButtons[x][y].getText());
                    if ( playerOneScore[yCord] <= 0){
                        playerOneScore[yCord] = 0;
                    }
                }
            }
        }
        return points;
    }

    private int checkDuplicates(int xCord, int yCord, int player, int[]playerScore){
        //check if any of the player's dice in that column are equal
        JButton[][] playerButtons;
        if (player == 1){
             playerButtons = playerOneButtons;
        } else {
            playerButtons = playerTwoButtons;
        }
        for (int x = 0; x <3; x++){
            for (int y = 0; y<3; y++){
                if (playerButtons[x][y].equals(playerButtons[xCord][yCord])){
                    int count = 0;
                    for (int z = 0; z < 3; z++)
                        if (playerButtons[x][z].getText().equals(playerButtons[x][y].getText()) && z != y){
                            count++;
                        }
                    if (count == 0){
                        playerScore[y] += Integer.parseInt(playerButtons[x][y].getText());
                        return Integer.parseInt(playerButtons[x][y].getText());
                    } else {
                        playerScore[y] += count * Integer.parseInt(playerButtons[x][y].getText()) * count;
                        return (count * Integer.parseInt(playerButtons[x][y].getText()) * count);
                    }
                }
            }
        }
        return 0;
    }

    private void updateColumnScores(){
        for(int x = 0; x < 3; x++){
            scorePanel[x].setText(Integer.toString(playerOneScore[x]));
        }
        for(int x = 3; x < 6; x++){
            scorePanel[x].setText(Integer.toString(playerTwoScore[x-3]));
        }
    }

    private void firstGo(){
        playerOneTurn = rn.nextInt(2) == 0;
    }
}
