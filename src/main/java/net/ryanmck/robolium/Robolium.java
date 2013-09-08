package net.ryanmck.robolium;

import net.ryanmck.robolium.command.CommandHandler;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.exception.NickAlreadyInUseException;
import java.io.*;

public class Robolium {
    private static PircBotX bot;
    private static CommandHandler commandHandler;

    public static PircBotX getBot() {
        return bot;
    } //end getBot

    public static CommandHandler getCommandHandler() {
        return commandHandler;
    } //end getCommandHandler

    public static void main(String[] args) {
        bot = new PircBotX();
        commandHandler = new CommandHandler();

        bot.getListenerManager().addListener(commandHandler);

        bot.setName("Robolium");
        bot.setLogin("Robolium");
        bot.setVerbose(true);
        try {
            bot.connect("irc.quakenet.org");
        } catch (IrcException ex) {
            System.out.println("[ERROR] Could not join server");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("[ERROR] Server could not be found");
            ex.printStackTrace();
        } //end try/catch
        bot.joinChannel("#mindcrack");
    } //end main
} //end class
