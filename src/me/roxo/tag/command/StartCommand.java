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
        manger.setBreakBlocks(false);
        manger.Set0(false);
        manger.Set1(false);
        manger.Set2(false);
        manger.Set3(false);
        manger.Set4(false);
        manger.Set5(false);
        if (!(commandSender instanceof Player)){
            return false;
        }
        double X =  Math.random() * 6000;
        double Z =   Math.random() * 6000;
        if(X > 2500){X = -X;}
        if (Z < 2500) {Z = -Z;}
        Player player = (Player) commandSender;
        if(!player.isOp()) return false;
        if(strings.length == 0){
            commandSender.sendMessage("Tag gamemodes: /startgame <gamemode> <X> <Z> <boolean of breakblocks>\n" +
                    "0 - normal\n" +
                    "1 - Laser tag\n" +
                    "2 - Sharks and minnows.\n" +
                    "3 - Infection\n" +
                    "4 - Freeze Tag (1 tagger stays as tagger. tries to freeze everyone else)\n" +
                    "5 - random powerups spawn that give you a potion effect (haste, speed, jump boost, slowness, mining fatigue, glowing, etc.)\n" +
                    "if you input 0 for the X and Z it will send you to random location ");
            return false;
        }
        System.out.println(strings[0] +" " + strings[1] +" " + strings[2] +" " + strings[3] +" " );
        if (strings[0].equals("0")){
            manger.setX(Double.parseDouble(strings[1]));
            manger.setZ(Double.parseDouble(strings[2]));
            if(strings[1].equals("0") || strings[2].equals("0")){
                manger.setX(X);
                manger.setZ(Z);
            }
            if (strings[3].equals("true")){
                manger.setBreakBlocks(true);
            }

            manger.Set0(true);
        }
        if (strings[0].equals("1")){
            manger.setX(Double.parseDouble(strings[1]));
            manger.setZ(Double.parseDouble(strings[2]));
            if(strings[1].equals("0") || strings[2].equals("0")){
                manger.setX(X);
                manger.setZ(Z);
            }
            if (strings[3].equals("true")){
                manger.setBreakBlocks(true);
            }

            manger.Set1(true);
        }
        if (strings[0].equals("2")){
            manger.setX(Double.parseDouble(strings[1]));
            manger.setZ(Double.parseDouble(strings[2]));
            if(strings[1].equals("0") || strings[2].equals("0")){
                manger.setX(X);
                manger.setZ(Z);
            }
            if (strings[3].equals("true")){
                manger.setBreakBlocks(true);
            }

            manger.Set2(true);
        }
        if (strings[0].equals("3")){
            manger.setX(Double.parseDouble(strings[1]));
            manger.setZ(Double.parseDouble(strings[2]));
            if(strings[1].equals("0") || strings[2].equals("0")){
                manger.setX(X);
                manger.setZ(Z);
            }
            if (strings[3].equals("true")){
                manger.setBreakBlocks(true);
            }

            manger.Set3(true);
        }
        if (strings[0].equals("4")){
            manger.setX(Double.parseDouble(strings[1]));
            manger.setZ(Double.parseDouble(strings[2]));
            if(strings[1].equals("0") || strings[2].equals("0")){
                manger.setX(X);
                manger.setZ(Z);
            }
            if (strings[3].equals("true")){
                manger.setBreakBlocks(true);
            }
            System.out.println("5");
            manger.Set4(true);
        }
        if (strings[0].equals("5")){
            manger.setX(Double.parseDouble(strings[1]));
            manger.setZ(Double.parseDouble(strings[2]));
            if(strings[1].equals("0") || strings[2].equals("0")){
                manger.setX(X);
                manger.setZ(Z);
            }
            if (strings[3].equals("true")){
                manger.setBreakBlocks(true);
            }

            manger.Set5(true);
        }


        manger.setState(State.START);


        return false;
    }
}
