package me.roxo.tag;

import me.roxo.tag.command.StartCommand;
import me.roxo.tag.listener.PlayerTagEvent;
import me.roxo.tag.manager.Manger;
import org.bukkit.plugin.java.JavaPlugin;

public class Tag extends JavaPlugin {



    @Override
    public void onEnable(){
        Manger manger = new Manger(this);
        getCommand("startgame").setExecutor(new StartCommand(manger));
        getServer().getPluginManager().registerEvents(new PlayerTagEvent(manger),this);


    }

    @Override
    public void onDisable(){



    }



}
