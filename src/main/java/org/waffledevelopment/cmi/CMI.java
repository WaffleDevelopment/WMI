package org.waffledevelopment.cmi;

import org.bukkit.plugin.java.JavaPlugin;
import org.waffledevelopment.cmi.commands.misc.afk;
import org.waffledevelopment.cmi.commands.misc.afkCheck;
import org.waffledevelopment.cmi.commands.misc.air;

public final class CMI extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("WAFFLE MANAGEMENT INTERFACE IS ACTIVATED");

        registeredCommands();
        
    }


    private void registeredCommands(){
        // Clean Registration Process

        getCommand("afk").setExecutor(new afk());
        getCommand("afkCheck").setExecutor(new afkCheck());
        getCommand("air").setExecutor(new air());

    }

    public void onDisable(){
        // Plugin shutdown logic

        System.out.println("WADDLE MANAGEMENT INTERFACE IS DEACTIVATED");

    }
    
}
