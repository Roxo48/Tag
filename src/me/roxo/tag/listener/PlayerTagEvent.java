package me.roxo.tag.listener;

import me.roxo.tag.manager.Manger;
import me.roxo.tag.manager.State;
import me.roxo.tag.tasks.Freeze;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
        Bukkit.getServer().broadcastMessage("server before active in the player tag event");
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
        Bukkit.getServer().broadcastMessage("server active10");
        if (manger.isSet0()) {
            System.out.println("normal");
            if (whoHit.getName().equals(manger.getTagger().getTagger().getName())) {

                Bukkit.getServer().broadcastMessage(ChatColor.RED + whoWasHit.getName() + " Is now Tagged ");

                a.runTaskTimer(manger.getPlugin(), 0, 20);


                manger.getTagger().setTagger(whoWasHit);
                Objects.requireNonNull(Bukkit.getServer().getWorld("world")).createExplosion(whoWasHit.getLocation(), 2F, false, false);


                whoHit.setGlowing(false);
                whoWasHit.setGlowing(true);
                System.out.println(manger.isSet4() + "idk");

                Bukkit.getServer().getOnlinePlayers().stream().iterator().next().setHealth(20);

            }  if (manger.isSet1()) {

            }  if (manger.isSet2()) {

            }  if (manger.isSet3()) {
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

                }  if (manger.isSet4()) {
                    Bukkit.getServer().broadcastMessage("server active7");
                    if (whoHit.getName().equals(manger.getTagger().getTagger().getName())) {
                        Bukkit.getServer().broadcastMessage("server active8");
                        Bukkit.getServer().broadcastMessage(ChatColor.RED + whoWasHit.getName() + " Is now Frozen ");

                        PotionEffect b = new PotionEffect(PotionEffectType.SLOW, 99999,255, true,true,true );
                        whoWasHit.addPotionEffect(b);

                        manger.getTagger().setTagger(whoWasHit);
                        Objects.requireNonNull(Bukkit.getServer().getWorld("world")).createExplosion(whoWasHit.getLocation(), 2F, false, false);



                        whoHit.setGlowing(false);
                        whoWasHit.setGlowing(true);


                        Bukkit.getServer().getOnlinePlayers().stream().iterator().next().setHealth(20);


                    }  if (manger.isSet5()) {



                    }


                }
            }
        }
    }
}
