package net.ryanmck.robolium.config;

import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

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
        if (resetConfig) resetConfig();
        readConfig();
    } // end constructor

    public void readConfig() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = (Map<String, Object>) yaml.load(new FileInputStream("config.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        nick = (String) map.get("nick");
        user = (String) map.get("user");
        server = (String) map.get("server");
        channels = (List<String>) map.get("channels");
        verbose = (Boolean) map.get("verbose");
        commandPrefix = (String) map.get("command_prefix");
    } // end readConfig

    public void resetConfig() {
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
