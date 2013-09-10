package net.ryanmck.robolium.config;

import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.util.Map;
import java.util.List;

public class ConfigHandler {
    private Yaml yaml;

    private String nick;
    private String user;
    private String server;
    private List<String> channels;
    private String commandPrefix;

    public ConfigHandler() throws Exception {
        yaml = new Yaml();
        readConfig();
    } // end constructor

    public void readConfig() throws Exception {
        Map<String, Object> map = (Map<String, Object>) yaml.load(new FileInputStream("config.yml"));
        nick = (String) map.get("nick");
        user = (String) map.get("user");
        server = (String) map.get("server");
        channels = (List<String>) map.get("channels");
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

    public List<String> getChannels() {
        return channels;
    } // end getChannel

    public String getCommandPrefix() {
        return commandPrefix;
    } // end getCommandPrefix
} // end class
