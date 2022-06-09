package me.roxo.tag.listener;

import me.roxo.tag.manager.Manger;
import me.roxo.tag.manager.State;
import org.bukkit.entity.Player;
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
        System.out.println("x1");
        System.out.println(manger.isBreakBlocks());
        if(!manger.isBreakBlocks()){
            return;
        }

        System.out.println("x2");
        if (e.getAction() == Action.LEFT_CLICK_BLOCK){
            System.out.println("x3");
            e.setCancelled(true);
        }



    }


}
