package me.roxo.tag.listener;

import me.roxo.tag.manager.Manger;
import me.roxo.tag.manager.State;
import me.roxo.tag.tagger.Tagger;
import me.roxo.tag.tasks.DoTask;
import me.roxo.tag.tasks.Freeze;
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

        if (e.getEntity().getType() != EntityType.PLAYER) return;

        Player whoWasHit;
        Player whoHit;

        whoWasHit = (Player) e.getEntity();
        whoHit = (Player) e.getDamager();
        Freeze a = new Freeze(whoWasHit, whoWasHit.getLocation());
        if(a.isFreezed()){
            return;
        }

        if(whoHit.getName().equals(manger.getTagger().getTagger().getName())){

            Bukkit.getServer().broadcastMessage(ChatColor.RED + whoWasHit.getName() + " Is now Tagged ");

            a.runTaskTimer(manger.getPlugin(), 0,20);


            manger.getTagger().setTagger(whoWasHit);

            whoHit.setGlowing(false);
            whoWasHit.setGlowing(true);


            Bukkit.getServer().getOnlinePlayers().stream().iterator().next().setHealth(20);

        }
    }
}
