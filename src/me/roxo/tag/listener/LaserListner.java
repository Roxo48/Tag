package me.roxo.tag.listener;

import me.roxo.tag.manager.Manger;
import me.roxo.tag.manager.State;
import me.roxo.tag.tasks.BlasterTimer;
import me.roxo.tag.tasks.Freeze;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Objects;

public class LaserListner implements Listener {
    Manger manger;
    private int count;
    private boolean bool;
    private int agh;
    private Player player2;
    private BlasterTimer blasterTimer;
    private Freeze freeze;
    public LaserListner(Manger manger) {
        this.manger = manger;
        count = 0;
    }
    @EventHandler
    public void gravitationalUraniumBlaster(@NotNull PlayerInteractEvent e){
        if(!manger.isSet1()){return;}
        if(manger.getState() != State.ACTIVE){return;}
        Material itemsName = null;
        try{
            itemsName = e.getPlayer().getInventory().getItemInMainHand().getType();
        }catch (Exception ignored){}
        if (itemsName == null)return;
        Player player = e.getPlayer();
        this.blasterTimer = new BlasterTimer(player);
        if(itemsName != Material.IRON_HORSE_ARMOR){ player.setWalkSpeed(0.2F);} else {
            player.getInventory().getItemInMainHand();
        }
        if (e.getAction().equals(Action.LEFT_CLICK_AIR) && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.IRON_HORSE_ARMOR && player.getName().equals(manger.getTagger().getTagger().getName())) {
           if (player.hasCooldown(Material.IRON_HORSE_ARMOR)){return;}
            player.setCooldown(Material.IRON_HORSE_ARMOR, 30);
            Location loc = player.getEyeLocation();
            Location location = loc.clone();
            Vector vector = loc.getDirection();
            Vector vec = vector.normalize().multiply(0.2);
            double i = 0;
            while(i <= 30){
                location.add(vec);
                Collection<Entity> entities = Objects.requireNonNull(Bukkit.getServer().getWorld(player.getWorld().getName())).getNearbyEntities(location,.4,.4,.4);
                if(entities != null){
                    for(Entity entity : entities){
                        if(entity.getName().equals(e.getPlayer().getName())){continue;}
                        if (entity instanceof LivingEntity){
                            LivingEntity livingEntity = (LivingEntity) entity;
                            livingEntity.damage(4);
                            if(livingEntity instanceof Player){
                                
                                Player hit = (Player) livingEntity;
                                player2 = hit;
                                manger.getTagger().setTagger(player2);
                                bool = true;
                                dothis(player);
                                return;
                            }
                        }
                    }
                }
                if(location.getBlock().getType() != Material.AIR){
                    Objects.requireNonNull(Bukkit.getServer().getWorld(player.getWorld().getName())).createExplosion(location, 1,false,false);
                    return;
                }
                player.spawnParticle(Particle.ASH, location, 1);
                player.playSound(location, Sound.ENTITY_LLAMA_SPIT, .2f,2);
                i += 0.2;
            }
        }
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) && player.getInventory().getItemInMainHand().getType() == Material.IRON_HORSE_ARMOR){
            if(count % 2 == 0){
                player.setWalkSpeed(-0.25F);
            }else {
                player.setWalkSpeed(0.2F);
            }
        count++;
        }
    }
    private void dothis(Player player){
        player.setWalkSpeed(.2F);
        player.setGlowing(false);
        player2.setGlowing(true);
        player.getInventory().clear();
        ItemStack itemStack = new ItemStack(Material.IRON_HORSE_ARMOR);
        player2.getInventory().addItem(itemStack);
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2,2);
        PotionEffect b = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 30 ,100, true,false,false );
        player2.addPotionEffect(b);
        freeze = new Freeze(player2, player2.getLocation());
        freeze.runTaskTimer(manger.getPlugin(),0,20);
    }
}
