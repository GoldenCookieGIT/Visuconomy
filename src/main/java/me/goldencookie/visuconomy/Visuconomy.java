package me.goldencookie.visuconomy;

import me.goldencookie.visuconomy.web.WebServer;
import org.apache.commons.io.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

public final class Visuconomy extends JavaPlugin {
    VaultImpl vault = null;
    WebServer server = null;
    @Override
    public void onEnable() {
        if(!this.getDataFolder().exists()) {
            try {
                this.getDataFolder().mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        vault = new VaultImpl();
        server = new WebServer();

        server.start();

        File file = new File(this.getDataFolder(), "test.zip");
        if(!file.exists()){
            URL inputUrl = getClass().getResource("/test.zip");
            try{
                FileUtils.copyURLToFile(inputUrl, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onDisable() {
        vault = null;
        server.stop();
    }

}
