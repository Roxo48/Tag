package me.roxo.tag.listener;

import me.roxo.tag.manager.Manger;
import me.roxo.tag.manager.State;
import me.roxo.tag.tagger.Tagger;
import me.roxo.tag.tasks.DoTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerTagEvent implements Listener {

    private final Manger manger;

    public PlayerTagEvent(Manger manger) {
        this.manger = manger;
    }


    @EventHandler
    public void onTag(EntityDamageByEntityEvent e){
        if(manger.getState() != State.ACTIVE){
            return;
        }

      //  Bukkit.getServer().broadcastMessage(manger.getTagger().getTagger().getName());
        if (e.getEntity().getType() == EntityType.PLAYER) return;
        Player whoWasHit;
        Player whoHit;
        whoWasHit = (Player) e.getEntity();
        whoHit = (Player) e.getDamager();
        Bukkit.getServer().broadcastMessage(manger.getTagger().getTagger().getName() + "  " + whoWasHit.getName());
        if(whoWasHit ==  manger.getTagger().getTagger()){
            Bukkit.getServer().broadcastMessage(ChatColor.RED + ((Player) e).getPlayer().getName() + " Is now Tagged ");
            manger.getTagger().setTagger((Player) e);
            whoHit.setGlowing(false);
            whoWasHit.setGlowing(true);
            manger.getDoTask().Freeze( ((Player) e).getPlayer());
            Bukkit.getServer().getOnlinePlayers().stream().iterator().next().setHealth(20);

        }
    }
}
