package me.roxo.tag.tasks;

import com.projectkorra.projectkorra.Manager;
import me.roxo.tag.manager.Manger;
import me.roxo.tag.manager.State;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

 public class DoTask  {

    private int counter;
    private final Manger gamerManager;
    private boolean some = false;
    private int done;

    private int a;
    public DoTask(Manger gamerManager){
        this.counter = 0;
        this.gamerManager = gamerManager;
        this.done =  0;

    }





    public void Timer(){
        long b = 20L * 60 * 30;

        new BukkitRunnable() {
            @Override
            public void run() {

                Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "You have been playing for " + done * 30 + " minutes");
                done++;
            }


        }.runTaskTimer(gamerManager.getPlugin(), 0L, b);




    }



    public boolean getPvPBoolean(){
        return some;
    }
    public int getDone(){return done;}

}
