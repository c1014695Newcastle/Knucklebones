import javax.swing.*;
import java.awt.*;

public class WinnerPanel {
    JFrame fr = new JFrame();

    public WinnerPanel(int player){
        fr.setTitle("KNUCKLEBONES");
        fr.setMinimumSize(new Dimension(500,200));
        fr.setMaximumSize(new Dimension(500,200));
        fr.getContentPane().setBackground(new Color(252,240,213));
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLayout(new BorderLayout());
        fr.setVisible(true);
        JLabel winner = new JLabel();
        winner.setForeground(new Color(177, 13, 17));
        winner.setHorizontalAlignment(SwingConstants.CENTER);
        winner.setFont(new Font("Noto Mono", Font.BOLD, 50));
        if (player == 1){
            winner.setText("PLAYER ONE WINS");
        } else if (player == 2){
            winner.setText("PLAYER TWO WINS");
        } else {
            winner.setText("TIE");
        }
        winner.setVisible(true);
        fr.add(winner);
    }

    public static void main(String[] args) {
        WinnerPanel wp = new WinnerPanel(1);
    }
}
