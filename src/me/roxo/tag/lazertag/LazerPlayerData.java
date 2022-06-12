package me.roxo.tag.lazertag;

import org.bukkit.entity.Player;

public class LazerPlayerData {

    private final LazerTag lazerTag;

    private Player player;

    private boolean isOnGreenTeam;

    private boolean isOut;


    public LazerPlayerData(LazerTag lazerTag, Player player ,boolean isOnGreenTeam) {
        this.lazerTag = lazerTag;
        this.player = player;
        this.isOnGreenTeam = isOnGreenTeam;
    }


    public Player getPlayer() {
        return player;
    }


    public boolean isOnGreenTeam() {
        return isOnGreenTeam;
    }

    public boolean isOut() {
        return isOut;
    }

    public void setOut(boolean out) {
        isOut = out;
    }




}
