package me.roxo.tag.tagger;

import me.roxo.tag.manager.Manger;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Tagger {

    private final Manger manger;

    private Player tagger;
    private ArrayList<Player> taggers = new ArrayList<>();

    private ArrayList<Player> frozenTaggers = new ArrayList<>();

    public Tagger(Manger manger) {

        this.manger = manger;
    }


    public Player getTagger() {
        return tagger;
    }

    public void setTagger(Player player){
        tagger = player;

    }

    public ArrayList<Player> getInfectionTaggers() {
        return taggers;
    }

    public void setInfectionTaggers(Player player){
        taggers.add(player);

    }

    public ArrayList<Player> getFozenPlayers(){return frozenTaggers; }

    public void setFrzonPlayers(Player player){
        frozenTaggers.add(player);
    }
    public void removeFrzenPlayer(Player player){

     frozenTaggers.remove(player);
    }


}
