package me.roxo.tag.tasks;

import me.roxo.tag.manager.Manger;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class StayStill extends BukkitRunnable {

    private int tick = 0;
    private Player player;
    private Location loc;

    private Location loc2;
    private Location loc3;

    private boolean isFreezed;

    public StayStill(Player player, Location location) {
        this.loc3 = location;
        this.player = player;
        this.loc = player.getLocation();
        this.isFreezed = false;
        this.loc2 = loc.add(0,150,0);
    }

    @Override
    public void run() {
        PotionEffect b = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 ,100, true,false,false );
        player.addPotionEffect(b);
        if(isFreezed){
            player.addPotionEffect(b);
            player.teleport(loc);
            player.sendTitle( ChatColor.RED + "UnFrozen", "",20,20,20);
            isFreezed = false;
            player.addPotionEffect(b);
            player.teleport(loc3);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP,1,1);
            player.setGameMode(GameMode.SURVIVAL);
            cancel();
            return;
        }

        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1,1);

        player.teleport(loc2);
        player.sendTitle(ChatColor.BLUE + "You are frozen...","Unfreezing when a Player hits you");
        tick++;


    }

    public boolean isFreezed(){
        return isFreezed;

    }
    public void setFreezed(boolean a){
        isFreezed = a;
    }

    public Player getPlayer(){

        return player;
    }

}
