package me.roxo.tag.listener;

import me.roxo.tag.manager.Manger;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class PlayerMove implements Listener {
    private final Manger manger;

    public PlayerMove(Manger manger) {
        this.manger = manger;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player player = e.getPlayer();
       double X = manger.getSharksandMinnows().getX100();
       double _x = manger.getSharksandMinnows().getX_100();
       boolean a = false;
        if(manger.getTagger().getSharks().contains(player)){return;}
        for(double i = -64; i <= 130; i+=1){
            for(double j = _x; j <= X; j+=1){
                Location plus = new Location(player.getWorld(), j,i,(100 +manger.getZ()) );
                Location minus = new Location(player.getWorld(), j,i,(100 -manger.getZ()) );
                if(player.getLocation() == plus || player.getLocation() == minus){
                    a = true;
                    manger.getTagger().setSafe(player);
                    player.sendTitle(ChatColor.GREEN + "SAFE", "", 20,20,20);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2,2);
                    player.setInvulnerable(true);
                }
            }
        }
        if(!a){
            manger.getTagger().getSafe().remove(player);
            player.setInvulnerable(false);
        }
        player.setInvulnerable(false);


    }
}
