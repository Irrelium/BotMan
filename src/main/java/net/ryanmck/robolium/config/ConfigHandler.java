package net.ryanmck.robolium.config;

import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigHandler {
    private Yaml yaml;

    private String nick;
    private String user;
    private String server;
    private List<String> channels;
    private boolean verbose;
    private String commandPrefix;

    public ConfigHandler(boolean resetConfig) {
        yaml = new Yaml();
        if (resetConfig || !(new File("config.yml").exists())) resetConfig();
        readConfig();
    } // end constructor

    public void readConfig() {
        Map<String, Object> settings = new HashMap<String, Object>();

        Map<String, Object> ircSettings = new HashMap<String, Object>();
        Map<String, Object> botSettings = new HashMap<String, Object>();

        try (InputStream config = new FileInputStream("config.yml")) {
            settings = (Map<String, Object>) yaml.load(config);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } // end try/catch

        ircSettings = (Map<String, Object>) settings.get("irc_settings");
        botSettings = (Map<String, Object>) settings.get("bot_settings");

        nick = (String) ircSettings.get("nick");
        user = (String) ircSettings.get("user");
        server = (String) ircSettings.get("server");
        channels = (List<String>) ircSettings.get("channels");

        verbose = (Boolean) botSettings.get("verbose");
        commandPrefix = (String) botSettings.get("command_prefix");
    } // end readConfig

    public void resetConfig() {
        try (InputStream resource = this.getClass().getResourceAsStream("/config.yml"); OutputStream out = new FileOutputStream("config.yml");){
            byte[] buffer = new byte[4096];
            int read;
            while ((read = resource.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            } // end while
        } catch (IOException e) {
            e.printStackTrace();
        } // end try/catch
    } // end resetConfig

    public String getNick() {
        return nick;
    } // end getNick

    public String getUser() {
        return user;
    } // end getUser

    public String getServer() {
        return server;
    } // end getServer

    public List<String> getChannels() {
        return channels;
    } // end getChannel

    public boolean getVerbose() {
        return verbose;
    } // end getVerbose

    public String getCommandPrefix() {
        return commandPrefix;
    } // end getCommandPrefix
} // end class
