package me.goldencookie.visuconomy.web;

import io.undertow.Undertow;
import io.undertow.server.handlers.resource.FileResourceManager;
import io.undertow.util.Headers;
import me.goldencookie.visuconomy.Visuconomy;
import org.bukkit.Bukkit;

import java.io.File;

import static io.undertow.Handlers.resource;


public class WebServer {
    Visuconomy plugin = Visuconomy.getPlugin(Visuconomy.class);
    Undertow server = null;
    public boolean started = false;
    public void start(){
        server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(
                        resource(
                                new FileResourceManager(
                                        new File(
                                                plugin.getDataFolder().getPath() + "/website"
                                        ), 100
                                )
                        )
                        .setDirectoryListingEnabled(false)
                        .setWelcomeFiles("index.html"))
                .build();
        server.start();
        started = true;
    }
    public void stop(){
        if(started){
            server.stop();
        }else{
            Bukkit.getLogger().info("[Visuconomy] No server instance to disable");
        }
    }
}
