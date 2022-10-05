package rps.app;

import core.Guesture;
import rps.engine.RpsEngine;
import rps.ui.AppLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class RpsApplication {
    private int counter = 0;

    RpsApplication() {
        ArrayList<Guesture> guestures = new ArrayList<>();
        ScoreBoard scoreBoard = new ScoreBoard();

        Guesture rock = new Guesture("rock");
        Guesture paper = new Guesture("paper");
        Guesture scissor = new Guesture("scissor");

        guestures.add(rock);
        guestures.add(paper);
        guestures.add(scissor);

        rock.setBeaters(
                guestures.stream().filter(
                        guesture -> guesture.name.equals("paper")
                ).collect(Collectors.toList())
        );

        paper.setBeaters(
                guestures.stream().filter(
                        guesture -> guesture.name.equals("scissor")
                ).collect(Collectors.toList())
        );

        scissor.setBeaters(
                guestures.stream().filter(
                        guesture -> guesture.name.equals("rock")
                ).collect(Collectors.toList())
        );

        AppLayout appLayout = new AppLayout(400, 400, guestures);

        ArrayList<JButton> playerGuestureOptionButtons = appLayout.getPlayerGuestureOptions();
        ArrayList<JLabel> opponentGuestureOptionLables = appLayout.getOpponentGuestureOptions();

        RpsEngine engine = new RpsEngine();
        engine.InitializeEngine(guestures);

        playerGuestureOptionButtons.forEach(playerGuestureOptionButton->{
            playerGuestureOptionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    counter++;

                    Guesture guestureByOpponent = engine.play();
                    JButton playerGuestureOptionButton = (JButton) event.getSource();

                    opponentGuestureOptionLables.forEach(opponentGuestureOption->{
                        if (opponentGuestureOption.getText().equals(guestureByOpponent.name)){
                            opponentGuestureOption.setBackground(Color.orange);

                            return;
                        }

                        opponentGuestureOption.setBackground(null);
                    });

                    Guesture guestureForPlayerGuestureOption = guestures.stream().filter(
                            guesture -> guesture.name.equals(playerGuestureOptionButton.getText())
                    ).findFirst().orElse(null);

                    assert guestureForPlayerGuestureOption != null;
                    String result = engine.hasPlayerWon(guestureForPlayerGuestureOption, guestureByOpponent);

                    if (result.equals("failed")){
                        scoreBoard.increaseOpponentScore(1);
                        appLayout.setOpponentMarks(scoreBoard.getOpponentScore());
                    }

                    if (result.equals("won")){
                        scoreBoard.increasePlayerScore(1);
                        appLayout.setPlayerMarks(scoreBoard.getPlayerScore());
                    }

                    if (counter == 3 && scoreBoard.getPlayerScore() > scoreBoard.getOpponentScore()){
                        appLayout.showDialogBox("Winner");
                        counter = 0;
                        scoreBoard.resetScores();
                        appLayout.resetMarkCounters();

                        return;
                    }

                    if (counter == 3 && scoreBoard.getPlayerScore() < scoreBoard.getOpponentScore()){
                        appLayout.showDialogBox("Looser");
                        counter = 0;
                        scoreBoard.resetScores();
                        appLayout.resetMarkCounters();

                        return;
                    }

                    if (counter == 3 && scoreBoard.getPlayerScore() == scoreBoard.getOpponentScore()){
                        appLayout.showDialogBox("Tied");
                        counter = 0;
                        scoreBoard.resetScores();
                        appLayout.resetMarkCounters();
                    }
                }
            });
        });
    }
}
