package me.roxo.tag.listener;

import me.roxo.tag.manager.Manger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BreakBlock implements Listener {
    private final Manger manger;

    public BreakBlock(Manger manger) {
        this.manger = manger;
    }


    @EventHandler
    public void onBreak(PlayerInteractEvent e){
        if(!manger.isBreakBlocks()){
            return;
        }
        if (e.getAction() == Action.LEFT_CLICK_BLOCK){
            e.setCancelled(true);
        }



    }


}
