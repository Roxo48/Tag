package me.roxo.tag.command;

import me.roxo.tag.manager.Manger;
import me.roxo.tag.manager.State;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StartCommand implements CommandExecutor {
    private final Manger manger;

    public StartCommand(Manger manger) {
        this.manger = manger;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {



        if (!(commandSender instanceof Player)){
            return false;
        }

        Player player = (Player) commandSender;
        //if(!player.isOp()) return false;
        manger.setState(State.START);
        System.out.println("hi");

        return false;
    }
}
