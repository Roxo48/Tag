package me.roxo.tag.tasks;

import me.roxo.tag.manager.Manger;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StayStill extends BukkitRunnable {

    private final Manger manger;

    private Player player;
    private Location location;

    public StayStill(Manger manger, Player player) {
        this.manger = manger;
        this.player = player;
        this.location = player.getLocation();

    }

    @Override
    public void run() {


            player.teleport(location);






    }
}
