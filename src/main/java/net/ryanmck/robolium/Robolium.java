package net.ryanmck.robolium;

import net.ryanmck.robolium.command.CommandHandler;
import net.ryanmck.robolium.config.ConfigHandler;
import net.ryanmck.robolium.tempplugins.MorseTranslator;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

public class Robolium {
    private static PircBotX bot;
    private static CommandHandler commandHandler;
    private static ConfigHandler configHandler;

    public static PircBotX getBot() {
        return bot;
    } // end getBot

    public static CommandHandler getCommandHandler() {
        return commandHandler;
    } // end getCommandHandler

    public static ConfigHandler getConfigHandler() {
        return configHandler;
    } // end getConfigHandler

    public static void main(String[] args) {
        boolean resetConfig = false;

        for (String arg : args) {
            if (arg.equals("--reset-config")) resetConfig = true;
        } // end for

        configHandler = new ConfigHandler(resetConfig);
        commandHandler = new CommandHandler();


        Configuration.Builder building = new Configuration.Builder()
            .addListener(commandHandler)
            .addListener(new MorseTranslator())
            .setName(configHandler.getNick())
            .setLogin(configHandler.getUser())
            .setServerHostname(configHandler.getServer());
        for (String channel : configHandler.getChannels()) {
            building.addAutoJoinChannel(channel);
        } // end for
        Configuration configuration = building.buildConfiguration();
        building = null;
        bot = new PircBotX(configuration);
        try {
            bot.startBot();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end main
} // end class
