package net.ryanmck.robolium.config;

import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.util.Map;

public class ConfigHandler {
    private String nick;
    private String user;
    private String server;
    private String channel;
    private String commandPrefix;
    private Yaml yaml;

    public ConfigHandler() throws Exception {
        yaml = new Yaml();
        readConfig();
    } // end constructor

    public void readConfig() throws Exception {
        Map<String, Object> map = (Map<String, Object>) yaml.load(new FileInputStream("config.yml"));
        nick = (String) map.get("nick");
        user = (String) map.get("user");
        server = (String) map.get("server");
        channel = (String) map.get("channel");
        commandPrefix = (String) map.get("command_prefix");
    } // end readConfig

    public String getNick() {
        return nick;
    } // end getNick

    public String getUser() {
        return user;
    } // end getUser

    public String getServer() {
        return server;
    } // end getServer

    public String getChannel() {
        return channel;
    } // end getChannel

    public String getCommandPrefix() {
        return commandPrefix;
    } // end getCommandPrefix
} // end class
