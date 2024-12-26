package org.stierprogrammer.vitality.configs;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.stierprogrammer.vitality.Vitality;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;
import java.util.logging.Level;

public class HomeConfig {
    private File file = null;
    private FileConfiguration config = null;
    private final Vitality plugin;

    public HomeConfig(Vitality plugin) {
        this.plugin = plugin;
        saveDefaultConfig();
    }

    public FileConfiguration getConfig() {
        if (this.config == null)
            reloadConfig();

        return config;
    }

    public void reloadConfig() {
        if (this.file == null)
            this.file = new File(this.plugin.getDataFolder(), "homes.yml");

        this.config = YamlConfiguration.loadConfiguration(this.file);

        InputStream defaultStream = this.plugin.getResource("homes.yml");

        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.config.setDefaults(defaultConfig);
        }
    }

    public void saveConfig() {
        if (this.config == null || this.file == null)
            return;

        try {
            this.getConfig().save(this.file);
        }
        catch (IOException exception) {
            this.plugin.getLogger().log(Level.SEVERE, "Couldn't save data to: " + this.file, exception);
        }
    }

    public void saveDefaultConfig() {
        if (this.file == null)
            this.file = new File(this.plugin.getDataFolder(), "homes.yml");

        if (!this.file.exists())
            this.plugin.saveResource("homes.yml", false);
    }

    public void saveHome(UUID uuid, String name, Location location) {
        if (this.config == null) {
            reloadConfig();
        }

        String path = "homes." + uuid + "." + name;

        this.config.set(path, location);

        saveConfig();
    }

    public Location getHome(UUID uuid, String name) {
        if (this.config == null) {
            reloadConfig();
            return null;
        }

        String path = "homes." + uuid.toString() + "." + name;

        if (!this.config.contains(path))
            return null;


        return this.config.getLocation(path);
    }
}
