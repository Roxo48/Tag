package me.roxo.tag.gamemodemanager;

import me.roxo.tag.manager.Manger;
import me.roxo.tag.manager.State;
import me.roxo.tag.tasks.GameStartingTask;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class LaserTag {

    private final Manger manger;

    public LaserTag(Manger manger) {
        this.manger = manger;
    }


    public void start(){

        manger.getPlayerArrayList().addAll(Bukkit.getServer().getOnlinePlayers());
        Objects.requireNonNull(Bukkit.getServer().getWorld("world")).setDifficulty(Difficulty.PEACEFUL);
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
            player.getActivePotionEffects().clear();
            player.setGlowing(false);
            player.getInventory().clear();
        }
        int max,min;
        min = 0;
        max = manger.getPlayerArrayList().size()-1;
        int b = (int)(Math.random()*(max-min+1)+min);

        Player player = manger.getPlayerArrayList().get(b);

        manger.getTagger().setTagger(player);
        ItemStack itemStack = new ItemStack(Material.IRON_HORSE_ARMOR);
        player.getInventory().addItem(itemStack);

        player.setGlowing(true);

        Objects.requireNonNull(Bukkit.getServer().getWorld("world")).setGameRule(GameRule.DO_MOB_SPAWNING, Boolean.FALSE);

        GameStartingTask gameStartingTask =  new GameStartingTask(manger);
        gameStartingTask.runTaskTimer(manger.getPlugin(),0,20);

    }
    public void Active(){
        Biome biome = manger.getTagger().getTagger().getWorld().getBiome(manger.getTagger().getTagger().getLocation().getBlockX(),
                manger.getTagger().getTagger().getLocation().getBlockY(),
                manger.getTagger().getTagger().getLocation().getBlockZ());
        if( biome.equals(Biome.OCEAN) || biome.equals(Biome.DEEP_COLD_OCEAN) || biome.equals(Biome.COLD_OCEAN)||  biome.equals(Biome.LUKEWARM_OCEAN)){
            manger.setState(State.STARTING);

        }

        Objects.requireNonNull(Bukkit.getServer().getWorld("world")).setGameRule(GameRule.DO_MOB_SPAWNING,Boolean.FALSE);
        for(Player player1 : manger.getPlayerArrayList()){
            player1.setInvulnerable(false);
        }


    }

}
