package me.roxo.tag.listener;

import me.roxo.tag.manager.Manger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Death implements Listener {

    private Manger manger;

    public Death(Manger manger) {
        this.manger = manger;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity().getPlayer();

        try{
            player.teleport(manger.getPlayerLocation());
        }catch (Exception exception){}


    }
}
