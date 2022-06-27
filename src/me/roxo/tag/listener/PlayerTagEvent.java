package me.roxo.tag.listener;

import me.roxo.tag.manager.Manger;
import me.roxo.tag.manager.State;
import me.roxo.tag.tasks.Freeze;
import me.roxo.tag.tasks.StayStill;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Objects;

public class PlayerTagEvent implements Listener {

    private final Manger manger;


    public PlayerTagEvent(Manger manger) {
        this.manger = manger;
    }


    @EventHandler
    public void onTag(EntityDamageByEntityEvent e) {
        Player whoWasHit;
        Player whoHit;
        whoWasHit = (Player) e.getEntity();
        whoHit = (Player) e.getDamager();
        Freeze a = new Freeze(whoWasHit, whoWasHit.getLocation());
        StayStill c = new StayStill( whoWasHit, whoWasHit.getLocation());
        ArrayList<StayStill> stayStills = new ArrayList<>();
        if(!manger.isSet4()){
            if(stayStills == null){return;}
            for (StayStill stay: stayStills) {
                stay.cancel();
            }
        }
        Bukkit.getServer().broadcastMessage("server before active in the player tag event");
        if (manger.getState() != State.ACTIVE) {
            return;
        }

        if (e.getEntity().getType() != EntityType.PLAYER) return;





        if (a.isFreezed()) {
            return;
        }

        Bukkit.getServer().broadcastMessage("server active10 " + manger.isSet4() + " " + manger.isSet0());
        if (manger.isSet0()) {

            if (whoHit.getName().equals(manger.getTagger().getTagger().getName())) {

                Bukkit.getServer().broadcastMessage(ChatColor.RED + whoWasHit.getName() + " Is now Tagged ");

                a.runTaskTimer(manger.getPlugin(), 0, 20);


                manger.getTagger().setTagger(whoWasHit);
                Objects.requireNonNull(Bukkit.getServer().getWorld("world")).createExplosion(whoWasHit.getLocation(), 2F, false, false);


                whoHit.setGlowing(false);
                whoWasHit.setGlowing(true);


                Bukkit.getServer().getOnlinePlayers().stream().iterator().next().setHealth(20);
            }

            }  if (manger.isSet1()) {


            }  if (manger.isSet2()) {
            ArrayList<Player> sharks = manger.getTagger().getSharks();
            double X = manger.getX();
            double Z = manger.getZ();
            try {
                manger.getTagger().getSafe().remove(whoWasHit);
            }catch(Exception ignored){}
            manger.getTagger().setSharks(whoWasHit);
            Location location = manger.getTagger().getSharks().get(0).getLocation();
            whoWasHit.teleport(location);
            whoWasHit.setGlowing(true);

            }  if (manger.isSet3()) {
            boolean istagger = false;
            for (Player player : manger.getTagger().getInfectionTaggers()) {
                if (whoHit.getName().equals(player.getName())) {
                    istagger = true;
                }
            }


            if (istagger && !manger.getTagger().getInfectionTaggers().contains(whoWasHit)) {

                Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + whoWasHit.getName() + " Is now Infected Along with " + whoHit.getName());

                a.runTaskTimer(manger.getPlugin(), 0, 20);


                manger.getTagger().setInfectionTaggers(whoWasHit);
                Objects.requireNonNull(Bukkit.getServer().getWorld("world")).createExplosion(whoWasHit.getLocation(), 2F, false, false);


                whoWasHit.setGlowing(true);


                Bukkit.getServer().getOnlinePlayers().stream().iterator().next().setHealth(20);
                if(manger.getTagger().getInfectionTaggers().size() == Bukkit.getServer().getOnlinePlayers().size()){
                    manger.setState(State.WON);

                }

            }
        }if (manger.isSet4()) {
            if (whoHit.getName().equals(manger.getTagger().getTagger().getName())) {

                Bukkit.getServer().broadcastMessage(ChatColor.DARK_AQUA + whoWasHit.getName() + " Is now Frozen ");

                PotionEffect b = new PotionEffect(PotionEffectType.SLOW, 99999, 255, true, false, false);
                whoWasHit.addPotionEffect(b);

                manger.getTagger().setFrzonPlayers(whoWasHit);

                stayStills.add(c);

                Bukkit.getServer().getOnlinePlayers().stream().iterator().next().setHealth(20);

            }
            if (manger.getTagger().getFozenPlayers().contains(whoWasHit)
                    && !(whoHit.getName().equals(manger.getTagger().getTagger().getName()))){

                for (StayStill stayStill : stayStills){

                    if(stayStill.getPlayer().getName().equals(whoWasHit.getName())){
                        stayStill.setFreezed(true);
                    }
                }
            }
            if(manger.getTagger().getFozenPlayers().size() == Bukkit.getServer().getOnlinePlayers().size()){
                manger.setState(State.WON);
            }



        }if (manger.isSet5()) {



                    }



                }

            }

