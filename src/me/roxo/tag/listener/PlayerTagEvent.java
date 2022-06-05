package me.roxo.tag.listener;

import me.roxo.tag.manager.Manger;
import me.roxo.tag.manager.State;
import me.roxo.tag.tagger.Tagger;
import me.roxo.tag.tasks.DoTask;
import org.bukkit.ChatColor;
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
        if(!(e.getDamager() instanceof Player)){
            return;
        }
        if(!(e instanceof Player)){
            return;
        }
        if(manger.getState() != State.ACTIVE){
            return;
        }
        e.setCancelled(true);
        Tagger tagger = new Tagger(manger);
        if(e.getDamager() == tagger.getTagger()){
            tagger.setTagger((Player) e);
            ((Player) e).setDisplayName(ChatColor.RED + ((Player) e).getPlayer().getName());
            ((Player) e.getDamager()).setDisplayName(ChatColor.GREEN + ((Player) e.getDamager()).getPlayer().getName());
            manger.getDoTask().Freeze();
        }
    }
}
