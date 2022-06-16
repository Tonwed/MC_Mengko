package com.tonwed.mengko;


import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static org.bukkit.Bukkit.getLogger;


public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("mksend")) { // 判断输入的指令是否是 /mconnect
            /*
            if (args.length != 2) {
                // 参数数量太少，拒绝处理
                return false;
            }

             */
            //Thread tcp1 = TCP.start(1234);
            //tcp1.start();
            //String ip = args[0];
            //String port = args[1];
            //getLogger().info("已启动！");
            TCP.SendMsg(args[0]+"\n");
            return true;
        }
        return false;
    }
}