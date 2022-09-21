import javax.swing.*;
import java.awt.*;

public class WinnerPanel {
    JFrame frame;

    public WinnerPanel(int player){
        frame = new JFrame();
        frame.setTitle("KNUCKLEBONES");
        frame.setMinimumSize(new Dimension(500,200));
        frame.setMaximumSize(new Dimension(500,200));
        frame.getContentPane().setBackground(new Color(252,240,213));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
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
        frame.add(winner);
    }

    public static void main(String[] args) {
        WinnerPanel wp = new WinnerPanel(1);
        wp.frame.setVisible(true);
    }
}
