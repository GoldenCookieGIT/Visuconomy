package me.goldencookie.visuconomy;

import me.goldencookie.visuconomy.web.Web;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.logging.Logger;

public class VaultImpl {
    private static Economy econ = null;
    private int port = 25566;
    Logger log = Bukkit.getLogger();
    public VaultImpl(){
        if(!setupEconomy()){
            log.severe("No economy plugin has been found, disabling. (REMEMBER, YOU NEED VAULT + ECONOMY PLUGIN)");
            Bukkit.getServer().getPluginManager().disablePlugin(Visuconomy.getPlugin(Visuconomy.class));
        }else{
            log.info("[Visuconomy] Successfully hooked into " + econ.getName());
            try{
                Web.getInstance().start(port);
                log.info("Started webserver on: " + port);
            }catch (Exception exception){
                log.severe("Unable to start webserver, exception: ");
                exception.printStackTrace();
            }

        }
    }

    private boolean setupEconomy() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    public static Economy getEconomy() {
        return econ;
    }
}
