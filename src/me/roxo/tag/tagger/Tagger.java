package me.roxo.tag.tagger;

import me.roxo.tag.manager.Manger;
import org.bukkit.entity.Player;

public class Tagger {

    private final Manger manger;

    private Player tagger;

    public Tagger(Manger manger) {

        this.manger = manger;
    }


    public Player getTagger() {
        return tagger;
    }

    public void setTagger(Player player){
        tagger = player;
    }


}
