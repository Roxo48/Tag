package me.roxo.tag.manager;

import me.roxo.tag.Tag;
import me.roxo.tag.tagger.Tagger;
import me.roxo.tag.tasks.DoTask;
import me.roxo.tag.tasks.GameStartingTask;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Manger {

    private final Tag plugin;

    private final List<Player> playerArrayList = new ArrayList<>();
    private final DoTask doTask;



    private final Tagger tagger;
    private State state;



    public Manger(Tag plugin) {

        this.tagger = new Tagger(this);
        this.plugin = plugin;
        this.doTask = new DoTask(this);
    }


    public void setState(State state){
        this.state = state;

        switch (state){

            case START:
                playerArrayList.addAll(Bukkit.getServer().getOnlinePlayers());
                Objects.requireNonNull(Bukkit.getServer().getWorld("world")).setDifficulty(Difficulty.PEACEFUL);

                setState(State.STARTING);
                break;
            case STARTING:
                for (Player player : playerArrayList){
                    player.setGlowing(false);
                    player.getInventory().clear();
                }
                int max,min;
                min = 0;
                max = playerArrayList.size()-1;
                int b = (int)(Math.random()*(max-min+1)+min);

                Player player = playerArrayList.get(b);

                tagger.setTagger(player);

                player.setGlowing(true);

                Objects.requireNonNull(Bukkit.getServer().getWorld("world")).setGameRule(GameRule.DO_MOB_SPAWNING, Boolean.FALSE);

                GameStartingTask gameStartingTask =  new GameStartingTask(this);
                gameStartingTask.runTaskTimer(plugin,0,20);

                break;
            case ACTIVE:

                Biome biome = getTagger().getTagger().getWorld().getBiome(getTagger().getTagger().getLocation().getBlockX(),
                        getTagger().getTagger().getLocation().getBlockY(),
                        getTagger().getTagger().getLocation().getBlockZ());
                if( biome.equals(Biome.OCEAN) || biome.equals(Biome.DEEP_COLD_OCEAN)){
                    setState(State.STARTING);

                }

                Objects.requireNonNull(Bukkit.getServer().getWorld("world")).setGameRule(GameRule.DO_MOB_SPAWNING,Boolean.FALSE);
                for(Player player1 : playerArrayList){
                    player1.setInvulnerable(false);
                }
                doTask.Timer();
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
}
