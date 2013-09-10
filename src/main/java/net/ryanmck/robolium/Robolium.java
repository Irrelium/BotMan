package net.ryanmck.robolium;

import net.ryanmck.robolium.command.CommandHandler;
import net.ryanmck.robolium.config.ConfigHandler;
import org.pircbotx.exception.IrcException;
import org.pircbotx.PircBotX;
import java.io.IOException;

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
            if (arg == "--reset-config") resetConfig = true;
        } // end for

        bot = new PircBotX();
        configHandler = new ConfigHandler(resetConfig);
        commandHandler = new CommandHandler();

        bot.getListenerManager().addListener(commandHandler);

        bot.setName(configHandler.getNick());
        bot.setLogin(configHandler.getUser());
        bot.setVerbose(configHandler.getVerbose());
        try {
            bot.connect(configHandler.getServer());
        } catch (IrcException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } // end try/catch
        for (String channel : configHandler.getChannels()) {
            bot.joinChannel(channel);
        } // end for
    } // end main
} // end class
