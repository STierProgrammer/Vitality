package org.stierprogrammer.vitality;

import org.bukkit.plugin.java.JavaPlugin;
import org.stierprogrammer.vitality.commands.HomeCommand;
import org.stierprogrammer.vitality.commands.SetHomeCommand;
import org.stierprogrammer.vitality.configs.HomeConfig;

import java.util.logging.Logger;

public final class Vitality extends JavaPlugin {
    public final Logger logger = Logger.getLogger("LOGGER");
    public final HomeConfig homeConfig = new HomeConfig(this);

    private final SetHomeCommand setHomeCommand = new SetHomeCommand(this);
    private final HomeCommand homeCommand = new HomeCommand(this);

    @Override
    public void onEnable() {
        getCommand("setHome").setExecutor(setHomeCommand);
        getCommand("home").setExecutor(homeCommand);
    }


}
