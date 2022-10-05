package engines;

import core.Guesture;

import java.util.ArrayList;
import java.util.Random;

public class RandomGuestureEngine {
    public ArrayList<Guesture> playerGuestures;
    public ArrayList<Guesture> opponentGuestures;
    public Random randomizer;

    public void InitializeEngine(ArrayList<Guesture> guestures) {
        this.playerGuestures = guestures;
        this.opponentGuestures = guestures;
        this.randomizer = new Random();
    }

    public Guesture play(){
        int randomOpponentGuesturesIndex = this.randomizer.nextInt(this.opponentGuestures.size());

        return this.opponentGuestures.get(randomOpponentGuesturesIndex);
    }

    public String hasPlayerWon(Guesture playerGuesture, Guesture opponentGuesture){
        if (playerGuesture.name.equals(opponentGuesture.name)){
            return "tied";
        }

        if (playerGuesture.beaters.contains(opponentGuesture)){
            return "failed";
        }

        return "won";
    }
}
