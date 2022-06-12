package me.roxo.tag.lazertag;

import me.roxo.tag.Tag;
import me.roxo.tag.manager.State;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LazerTag {



    private final Tag plugin;

    private StatesOfLazer statesOfLazer;

    private ArrayList<LazerPlayerData> lazerPlayerData;

    private List<Player> playerList;


    public LazerTag(Tag plugin) {
        this.plugin = plugin;
        this.lazerPlayerData = new ArrayList<>();
    }

    public void setState(StatesOfLazer state){
        this.statesOfLazer = state;
        this.playerList = new ArrayList<>();


        switch (state){

            case SETINGTEAMS:
                playerList.addAll(Bukkit.getServer().getOnlinePlayers());

                int teamSize = playerList.size() / 2;
                for(int i = 0; i < playerList.size(); i++){
                    if(i >= teamSize){
                        LazerPlayerData lazerPlayerData1 = new LazerPlayerData(this, playerList.get(i), true);
                        lazerPlayerData.add(lazerPlayerData1);
                    }else {
                        LazerPlayerData lazerPlayerData1 = new LazerPlayerData(this, playerList.get(i), false);
                        lazerPlayerData.add(lazerPlayerData1);
                    }
                }



                break;
            case STARTING:






                break;
            case ACTIVE:

                break;


        }
    }

    public LazerTag getLaxerTag(){
        return this;
    }
}
