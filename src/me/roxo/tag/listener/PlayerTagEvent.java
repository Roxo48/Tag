package me.roxo.tag.listener;

import me.roxo.tag.manager.Manger;
import me.roxo.tag.manager.State;
import me.roxo.tag.tagger.Tagger;
import me.roxo.tag.tasks.DoTask;
import me.roxo.tag.tasks.Freeze;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.Effect;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class PlayerTagEvent implements Listener {

    private final Manger manger;


    public PlayerTagEvent(Manger manger) {
        this.manger = manger;
    }


    @EventHandler
    public void onTag(EntityDamageByEntityEvent e) {

        if (manger.getState() != State.ACTIVE) {
            return;
        }

        if (e.getEntity().getType() != EntityType.PLAYER) return;

        Player whoWasHit;
        Player whoHit;

        whoWasHit = (Player) e.getEntity();
        whoHit = (Player) e.getDamager();
        Freeze a = new Freeze(whoWasHit, whoWasHit.getLocation());
        if (a.isFreezed()) {
            return;
        }
        if (manger.isSet0()) {
            if (whoHit.getName().equals(manger.getTagger().getTagger().getName())) {

                Bukkit.getServer().broadcastMessage(ChatColor.RED + whoWasHit.getName() + " Is now Tagged ");

                a.runTaskTimer(manger.getPlugin(), 0, 20);


                manger.getTagger().setTagger(whoWasHit);
                Objects.requireNonNull(Bukkit.getServer().getWorld("world")).createExplosion(whoWasHit.getLocation(), 2F, false, false);


                whoHit.setGlowing(false);
                whoWasHit.setGlowing(true);


                Bukkit.getServer().getOnlinePlayers().stream().iterator().next().setHealth(20);

            } else if (manger.isSet1()) {

            } else if (manger.isSet2()) {

            } else if (manger.isSet3()) {
                boolean istagger = false;
                for (Player player : manger.getTagger().getInfectionTaggers()) {
                    if (whoHit.getName().equals(player.getName())) {
                        istagger = true;
                    }
                }
                if (istagger) {

                    Bukkit.getServer().broadcastMessage(ChatColor.RED + whoWasHit.getName() + " Is now Infected Along with " + whoHit.getName());

                    a.runTaskTimer(manger.getPlugin(), 0, 20);


                    manger.getTagger().setInfectionTaggers(whoWasHit);
                    Objects.requireNonNull(Bukkit.getServer().getWorld("world")).createExplosion(whoWasHit.getLocation(), 2F, false, false);


                    whoWasHit.setGlowing(true);


                    Bukkit.getServer().getOnlinePlayers().stream().iterator().next().setHealth(20);

                } else if (manger.isSet4()) {
                    if (whoHit.getName().equals(manger.getTagger().getTagger().getName())) {

                        Bukkit.getServer().broadcastMessage(ChatColor.RED + whoWasHit.getName() + " Is now Frozen ");

                        PotionEffect b = new PotionEffect(PotionEffectType.SLOW, 99999,255, true,false,false );
                        whoWasHit.addPotionEffect(b);

                        manger.getTagger().setTagger(whoWasHit);
                        Objects.requireNonNull(Bukkit.getServer().getWorld("world")).createExplosion(whoWasHit.getLocation(), 2F, false, false);



                        whoHit.setGlowing(false);
                        whoWasHit.setGlowing(true);


                        Bukkit.getServer().getOnlinePlayers().stream().iterator().next().setHealth(20);


                    } else if (manger.isSet5()) {



                    }


                }
            }
        }
    }
}
