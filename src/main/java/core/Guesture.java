package core;

import java.util.List;

public class Guesture {
    public String name;
    public List<Guesture> beaters;

    public Guesture(String name){
        this.name = name;
    }

    public void setBeaters(List<Guesture> beaters) {
        this.beaters = beaters;
    }
}
