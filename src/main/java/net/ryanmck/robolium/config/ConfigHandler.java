package net.ryanmck.robolium.config;

import java.util.Properties;
import java.io.FileInputStream;

public class ConfigHandler {
    private String nick;
    private String user;
    private String server;
    private String channel;
    private Properties properties;

    public ConfigHandler() throws Exception {
        properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
    } // end constructor

    public void readConfig() throws Exception {
        nick = properties.getProperty("nick");
        user = properties.getProperty("user");
        server = properties.getProperty("server");
        channel = properties.getProperty("channel");
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
} // end class
