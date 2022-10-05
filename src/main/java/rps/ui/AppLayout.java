package rps.ui;

import core.Guesture;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AppLayout {
    private final JFrame frame;
    private final ArrayList<JButton> playerGuestureOptions = new ArrayList<>();
    private final ArrayList<JLabel> opponentGuestureOptions = new ArrayList<>();

    private final JLabel opponentScoreLabel;

    private final JLabel playerScoreLabel;

    public AppLayout(int width, int height, ArrayList<Guesture> guestures) {
        this.frame = this.initializeFrame();
        GridLayout layout = (GridLayout) this.initializeLayout("grid", 4, 2);
        Container contentPane = this.frame.getContentPane();

        contentPane.setLayout(layout);

        guestures.forEach(guesture -> {
            JButton button = new JButton(guesture.name);
            JLabel label = new JLabel(guesture.name, SwingConstants.CENTER);
            label.setOpaque(true);

            this.playerGuestureOptions.add(button);
            this.opponentGuestureOptions.add(label);

            contentPane.add(button);
            contentPane.add(label);
        });

        this.opponentScoreLabel = new JLabel("0", SwingConstants.CENTER);
        opponentScoreLabel.setOpaque(true);
        this.playerScoreLabel = new JLabel("0", SwingConstants.CENTER);
        playerScoreLabel.setOpaque(true);

        contentPane.add(playerScoreLabel);
        contentPane.add(opponentScoreLabel);

        this.frame.setSize(width, height);
        this.frame.setVisible(true);
    }

    public ArrayList<JButton> getPlayerGuestureOptions() {
        return this.playerGuestureOptions;
    }

    public ArrayList<JLabel> getOpponentGuestureOptions() {
        return this.opponentGuestureOptions;
    }

    private JFrame initializeFrame() {
        return new JFrame();
    }

    private LayoutManager initializeLayout(String layout, int rows, int cols) {
        if (layout.equals("spring")) {
            return new SpringLayout();
        }

        if (layout.equals("grid")) {
            return new GridLayout(rows, cols);
        }

        return null;
    }

    public void showDialogBox(String message) {
        JOptionPane.showMessageDialog(this.frame, message, message, JOptionPane.INFORMATION_MESSAGE);
    }

    public void setOpponentMarks (int marks) {
        this.opponentScoreLabel.setText(String.valueOf(marks));
    }

    public void setPlayerMarks (int marks) {
        this.playerScoreLabel.setText(String.valueOf(marks));
    }

    public void resetMarkCounters (){
        this.opponentScoreLabel.setText("0");
        this.playerScoreLabel.setText("0");
    }
}
