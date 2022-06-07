package me.roxo.tag.manager;

import me.roxo.tag.Tag;
import me.roxo.tag.tagger.Tagger;
import me.roxo.tag.tasks.DoTask;
import me.roxo.tag.tasks.GameStartingTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Manger {

    private final Tag plugin;

    private List<Player> playerArrayList = new ArrayList<Player>();
    private DoTask doTask;



    private Tagger tagger;
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


                setState(State.STARTING);
                break;
            case STARTING:
                Bukkit.getServer().getOnlinePlayers().stream().iterator().next().getInventory().clear();
                Bukkit.getServer().getOnlinePlayers().stream().iterator().next().setGlowing(false);
                Player player = playerArrayList.stream().findFirst().get().getPlayer();
                tagger.setTagger(player);
                player.setGlowing(true);
                Bukkit.getServer().broadcastMessage(getTagger().getTagger().getName());
                 GameStartingTask gameStartingTask =  new GameStartingTask(this);
                gameStartingTask.runTaskTimer(plugin,0,20);

                break;
            case ACTIVE:
                double a = Math.random() * 5000;
                Location loc = new Location(Bukkit.getServer().getWorld("world"),a,100,a);

                World world = Bukkit.getWorld("world");
                WorldBorder worldBorder = world.getWorldBorder();
                worldBorder.setCenter(loc);
                worldBorder.setSize(100);
                for(Player player1 : playerArrayList){
                    player1.teleport(loc);
                }


                doTask.Timer();
                System.out.println("x5");
                break;
            case WON:

                World world1 = Bukkit.getWorld("world");
                WorldBorder worldBorder1 = world1.getWorldBorder();
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
    public DoTask getDoTask(){
        return doTask;
    }
    public Tag getPlugin(){
        return plugin;
    }

    public State getState() {
        return state;
    }
}
