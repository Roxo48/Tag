package me.roxo.tag.listener;

import me.roxo.tag.manager.Manger;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Objects;

public class LaserListner implements Listener {
    Manger manger;
    public LaserListner(Manger manger) {
        this.manger = manger;
    }
    @EventHandler
    public void gravitationalUraniumBlaster(@NotNull PlayerInteractEvent e){
        if(!manger.isSet1()){return;}
        String itemsName = Objects.requireNonNull(e.getPlayer().getInventory().getItemInMainHand().getItemMeta()).getDisplayName();
        Player player = e.getPlayer();
        if (e.getAction().equals(Action.LEFT_CLICK_AIR) && player.getInventory().getItemInMainHand().getType() == Material.IRON_HORSE_ARMOR && player.getName().equals(manger.getTagger().getTagger().getName())) {
            Location loc = player.getEyeLocation();
            Location location = loc.clone();
            Vector vector = loc.getDirection();
            Vector vec = vector.normalize().multiply(0.2);
            double i = 0;
            while(i <= 20){
                location.add(vec);
                Collection<Entity> entities = Objects.requireNonNull(Bukkit.getServer().getWorld(player.getWorld().getName())).getNearbyEntities(location,1,1,1);
                if(entities != null){
                    for(Entity entity : entities){
                        if (entity instanceof LivingEntity){
                            LivingEntity livingEntity = (LivingEntity) entity;
                            livingEntity.damage(4);
                            if(livingEntity instanceof Player){
                                Player hit = (Player) livingEntity;
                                hit.spawnParticle(Particle.REDSTONE, player.getEyeLocation(), 5,1);
                                manger.getTagger().setTagger(hit);
                                player.setGlowing(false);
                                hit.setGlowing(true);
                                player.getInventory().clear();
                                hit.getInventory().getItemInMainHand().setType(Material.IRON_HORSE_ARMOR);
                            }
                        }
                    }
                }
                if(location.getBlock().getType() != Material.AIR){
                    Objects.requireNonNull(Bukkit.getServer().getWorld(player.getWorld().getName())).createExplosion(location, 1,false,false);
                    return;
                }
                player.spawnParticle(Particle.TOTEM, location, 2);
                player.playSound(location, Sound.ENTITY_GUARDIAN_ATTACK, 2,2);
                i += 0.2;
            }
            return;
        }
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) && player.getInventory().getItemInMainHand().getType() == Material.IRON_HORSE_ARMOR && itemsName.equals("Gravitational Uranium Blaster")){
            player.setWalkSpeed(-0.25F);//Makes the zoom effect.
        }else{
            player.setWalkSpeed(0.2F);//Undoes the zoom effect.
        }
    }
}
