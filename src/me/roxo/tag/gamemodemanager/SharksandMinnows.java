package me.roxo.tag.gamemodemanager;

import me.roxo.tag.manager.Manger;
import me.roxo.tag.manager.State;
import me.roxo.tag.tasks.GameStartingTask;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SharksandMinnows {

    private final Manger manger;

    public SharksandMinnows(Manger manger) {
        this.manger = manger;
    }

    public void start(){

        manger.getPlayerArrayList().addAll(Bukkit.getServer().getOnlinePlayers());
        Objects.requireNonNull(Bukkit.getServer().getWorld(manger.getTagger().getTagger().getLocation().getWorld().getName())).setDifficulty(Difficulty.PEACEFUL);
        try {
            if (manger.getTagger().getInfectionTaggers().size() > 1 || manger.getTagger().getSharks().size() > 1) {
                manger.getTagger().getInfectionTaggers().clear();
                manger.getTagger().getInfectionTaggers().clear();
            }
        }catch (Exception ignored){}
        manger.setState(State.STARTING);

    }
    public void Starting(){
        for (Player player : manger.getPlayerArrayList()){
            player.setInvulnerable(true);
            player.getActivePotionEffects().clear();
            player.setGlowing(false);
            player.getInventory().clear();
        }
        int max,min;
        min = 0;
        max = manger.getPlayerArrayList().size()-1;
        int b = (int)(Math.random()*(max-min+1)+min);

        Player player = manger.getPlayerArrayList().get(b);

        manger.getTagger().setSharks(player);

        player.setGlowing(true);

        Objects.requireNonNull(Bukkit.getServer().getWorld(manger.getTagger().getTagger().getLocation().getWorld().getName())).setGameRule(GameRule.DO_MOB_SPAWNING, Boolean.FALSE);

        GameStartingTask gameStartingTask =  new GameStartingTask(manger);
        gameStartingTask.runTaskTimer(manger.getPlugin(),0,20);
        double X =manger.getX();
        double Z =manger.getZ();
        Location border = new Location(player.getWorld(), X,0,Z);
        World world1 = Bukkit.getWorld(manger.getTagger().getTagger().getLocation().getWorld().getName());
    }
    public void Active(){
        double X =manger.getX();
        double Z =manger.getZ();
        Location border = new Location(manger.getTagger().getSharks().get(0).getWorld(), (X + 100),160,Z);
        Location tele = new Location(manger.getTagger().getSharks().get(0).getWorld(), (X + 100),160,Z);
        for(Player player : manger.getPlayerArrayList()){
            if(!manger.getTagger().getSharks().contains(player)){
               player.teleport(tele);
            }
        }
        for(Player shark : manger.getTagger().getSharks()){
            shark.teleport(border);
        }



//        Biome biome = manger.getTagger().getTagger().getWorld().getBiome(manger.getTagger().getTagger().getLocation().getBlockX(),
//                manger.getTagger().getTagger().getLocation().getBlockY(),
//                manger.getTagger().getTagger().getLocation().getBlockZ());
//        if( biome.equals(Biome.OCEAN) || biome.equals(Biome.DEEP_COLD_OCEAN) || biome.equals(Biome.COLD_OCEAN) ||  biome.equals(Biome.LUKEWARM_OCEAN)){
//            manger.setState(State.STARTING);
//
//        }

        Objects.requireNonNull(Bukkit.getServer().getWorld(manger.getTagger().getTagger().getLocation().getWorld().getName())).setGameRule(GameRule.DO_MOB_SPAWNING,Boolean.FALSE);
        for(Player player1 : manger.getPlayerArrayList()){
            player1.setInvulnerable(false);
        }



    }

    public double getX100(){
        return manger.getX()+100;
    }
    public double getX_100(){
        return manger.getX()-100;
    }
}
