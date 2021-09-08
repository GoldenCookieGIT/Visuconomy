package me.goldencookie.visuconomy;

import org.bukkit.plugin.java.JavaPlugin;

public final class Visuconomy extends JavaPlugin {
    VaultImpl vault = null;
    @Override
    public void onEnable() {
        vault = new VaultImpl();

    }

    @Override
    public void onDisable() {
        vault = null;
    }
}
