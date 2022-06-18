package me.roxo.tag.manager;

import me.roxo.tag.Tag;
import me.roxo.tag.gamemodemanager.*;
import me.roxo.tag.tagger.Tagger;
import me.roxo.tag.tasks.DoTask;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Manger {

    private final Tag plugin;

    private final List<Player> playerArrayList = new ArrayList<>();
    private final DoTask doTask;



    private boolean set0;
    private boolean set1;
    private boolean set2;
    private boolean set3;
    private boolean set4;
    private boolean set5;

    private double X;
    private double Z;


    private final Tagger tagger;
    private State state;

    private NormalGameMode normalGameMode;
    private FreezeTag freezeTag;
    private Infection infection;
    private PowerPowerupsGameMode powerPowerupsGameMode;
    private TwoMinutesGameMode twoMinutesGameMode;
    private boolean breakBlocks;


    public Manger(Tag plugin) {
        this.normalGameMode = new NormalGameMode(this);
        this.freezeTag = new FreezeTag(this);
        this.infection = new Infection(this);
        this.powerPowerupsGameMode = new PowerPowerupsGameMode(this);
        this.twoMinutesGameMode = new TwoMinutesGameMode(this);
        this.tagger = new Tagger(this);
        this.plugin = plugin;
        this.doTask = new DoTask(this);
    }


    public void setState(State state){
        this.state = state;

        switch (state){

            case START:

                if(isSet0()){
                    normalGameMode.start();
                } else if (isSet1()){

                } else if (isSet2()){

                } else if (isSet3()){
                    infection.start();

                } else if (isSet4()){

                    freezeTag.start();

                } else if (isSet5()){
                    powerPowerupsGameMode.start();

                }
                break;
            case STARTING:
                for (Player player : getPlayerArrayList()){
                    player.removePotionEffect((PotionEffectType.SLOW));
                }
                if(isSet0()){
                    normalGameMode.Starting();
                } else if (isSet1()){

                } else if (isSet2()){

                } else if (isSet3()){
                    infection.Starting();

                } else if (isSet4()){

                    freezeTag.Starting();

                } else if (isSet5()){
                    powerPowerupsGameMode.Starting();

                }

                break;
            case ACTIVE:
                if(isSet0()){
                    normalGameMode.Active();
                } else if (isSet1()){

                } else if (isSet2()){

                } else if (isSet3()){
                    infection.Active();

                } else if (isSet4()){

                    freezeTag.Active();

                } else if (isSet5()){
                    powerPowerupsGameMode.Active();

                }

                break;
            case WON:

                World world1 = Bukkit.getWorld("world");
                WorldBorder worldBorder1 = Objects.requireNonNull(world1).getWorldBorder();
                worldBorder1.setCenter(0,0);
                worldBorder1.setSize(1000);

                break;
            case END:


                break;

        }


    }


    public List<Player> getPlayerArrayList(){
        return playerArrayList;
    }


    public Tagger getTagger(){
        return tagger;
    }

    public Tag getPlugin(){
        return plugin;
    }

    public State getState() {
        return state;
    }

    public boolean isSet0() {
        return set0;
    }

    public void Set0(boolean set0) {
        this.set0 = set0;
    }

    public boolean isSet1() {
        return set1;
    }

    public void Set1(boolean set1) {
        this.set1 = set1;
    }

    public boolean isSet2() {
        return set2;
    }

    public void Set2(boolean set2) {
        this.set2 = set2;
    }

    public boolean isSet3() {
        return set3;
    }

    public void Set3(boolean set3) {
        this.set3 = set3;
    }

    public boolean isSet4() {
        return set4;
    }

    public void Set4(boolean set4) {
        this.set4 = set4;
    }

    public boolean isSet5() {
        return set5;
    }

    public void Set5(boolean set5) {
        this.set5 = set5;
    }

    public double getX() {
        return X;
    }

    public double getZ() {
        return Z;
    }

    public void setX(double x) {
        X = x;
    }

    public void setZ(double z) {
        Z = z;
    }

    public void setBreakBlocks(boolean b) {
        this.breakBlocks = b;
    }

    public boolean isBreakBlocks() {
        return breakBlocks;
    }
}
