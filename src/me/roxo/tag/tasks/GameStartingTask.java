package me.roxo.tag.tasks;

import com.projectkorra.projectkorra.Manager;
import me.roxo.tag.manager.Manger;
import me.roxo.tag.manager.State;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class GameStartingTask extends BukkitRunnable {

    private Manger gameManager;

    public GameStartingTask(Manger gameManager) {
        this.gameManager = gameManager;
    }

    private  int timer = 20;
    @Override
    public void run() {
        if (timer <= 0) {
            System.out.println("hxhjl;s;djf;skdjf;lj;lsdfjslkjsdflkolkjsldjfljlsjdflkjlksjdlfkj");
            gameManager.setState(State.ACTIVE);
            cancel();
        }
        System.out.println("hx1");
        if (timer <= 5 || timer == 20) {
            System.out.println("hx4");

            for (Player players : gameManager.getPlayerArrayList()) {
                players.sendTitle(ChatColor.LIGHT_PURPLE + "Game starting in " , timer + " second" + (timer == 1 ? "" : "s") + "...",2,2,1);
            }
        }
        System.out.println("hx2");
        if(timer <= 3){
            System.out.println("hx5");

            for(Player players : gameManager.getPlayerArrayList()){
                players.playSound(players.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1,1);
                // players.sendTitle("", timer+ "", 20,20,20);
            }
        }
        timer--;



    }

}
