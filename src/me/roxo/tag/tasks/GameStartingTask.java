package me.roxo.tag.tasks;

import com.projectkorra.projectkorra.Manager;
import me.roxo.tag.manager.Manger;
import me.roxo.tag.manager.State;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class GameStartingTask extends BukkitRunnable {

    private Manger gameManager;

    public GameStartingTask(Manger gameManager) {
        this.gameManager = gameManager;
    }

    private  int timer = 20;
    double randX = Math.random() ;
    double randZ = Math.random() ;
    double rand = (Math.random() * 10);





    @Override
    public void run() {

        if (timer <= 0) {
            for(Player players : gameManager.getPlayerArrayList()){
                players.setInvulnerable(true);
                // players.sendTitle("", timer+ "", 20,20,20);
            }
            gameManager.setState(State.ACTIVE);
            cancel();
        }

        if (timer <= 5 || timer == 20) {
            for (Player players : gameManager.getPlayerArrayList()) {
                players.sendTitle(ChatColor.LIGHT_PURPLE + "Game starting in " , timer + " second" + (timer == 1 ? "" : "s") + "...",20,20,20);
            }
        }
        if(timer == 5){
            for(Player players : gameManager.getPlayerArrayList()){
                int X = (int) randX;
                int Z = (int) randZ;
                double random =  rand;
                if(random > 6.5){
                    X = (int)-X;
                } else if(random < 3.5){
                    Z = (int )-Z;
                }
                Location loc = new Location(Bukkit.getServer().getWorld("world"), X * 6000, 100 ,Z * 6000);
                players.setInvulnerable(true);
                players.playSound(players.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1,1);
                players.teleport(loc);
                World world = Bukkit.getWorld("world");
                WorldBorder worldBorder = world.getWorldBorder();
                worldBorder.setCenter(loc);
                worldBorder.setSize(100);
            }
        }
        timer--;
    }

}
