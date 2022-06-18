package me.roxo.tag.gamemodemanager;

import me.roxo.tag.manager.Manger;
import me.roxo.tag.manager.State;
import me.roxo.tag.tasks.GameStartingTask;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Infection {


    private final Manger manger;

    public Infection(Manger manger) {
        this.manger = manger;
    }

    public void start(){

        manger.getPlayerArrayList().addAll(Bukkit.getServer().getOnlinePlayers());
        Objects.requireNonNull(Bukkit.getServer().getWorld("world")).setDifficulty(Difficulty.PEACEFUL);
        try {
            if (manger.getTagger().getInfectionTaggers().size() > 1) {
                manger.getTagger().getInfectionTaggers().clear();
            }
        }catch (Exception ignored){}
        manger.setState(State.STARTING);

    }
    public void Starting(){
        for (Player player : manger.getPlayerArrayList()){
            player.getActivePotionEffects().clear();
            player.setGlowing(false);
            player.getInventory().clear();
        }
        int max,min;
        min = 0;
        max = manger.getPlayerArrayList().size()-1;
        int b = (int)(Math.random()*(max-min+1)+min);

        Player player = manger.getPlayerArrayList().get(b);

        manger.getTagger().setInfectionTaggers(player);

        player.setGlowing(true);

        Objects.requireNonNull(Bukkit.getServer().getWorld("world")).setGameRule(GameRule.DO_MOB_SPAWNING, Boolean.FALSE);

        GameStartingTask gameStartingTask =  new GameStartingTask(manger);
        gameStartingTask.runTaskTimer(manger.getPlugin(),0,20);

    }
    public void Active(){
        Biome biome = manger.getTagger().getInfectionTaggers().get(0).getWorld().getBiome(manger.getTagger().getInfectionTaggers().get(0).getLocation().getBlockX(),
                manger.getTagger().getInfectionTaggers().get(0).getLocation().getBlockY(),
                manger.getTagger().getInfectionTaggers().get(0).getLocation().getBlockZ());
        if( biome.equals(Biome.OCEAN) || biome.equals(Biome.DEEP_COLD_OCEAN) || biome.equals(Biome.COLD_OCEAN)||  biome.equals(Biome.LUKEWARM_OCEAN)){
            manger.setState(State.STARTING);
        }

        Objects.requireNonNull(Bukkit.getServer().getWorld("world")).setGameRule(GameRule.DO_MOB_SPAWNING,Boolean.FALSE);
        for(Player player1 : manger.getPlayerArrayList()){
            player1.setInvulnerable(false);
        }



    }
    public void Won(){
        for(Player player : manger.getPlayerArrayList()){
            player.playSound(player.getLocation(), Sound.ENTITY_GHAST_DEATH, 2,2);
            player.sendTitle(ChatColor.RED + "The Infected has WON...", "", 20,20,20);
        }
        for(Player player : manger.getTagger().getInfectionTaggers()){
            manger.getTagger().getInfectionTaggers().remove(player);
        }
    }


}
