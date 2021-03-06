package net.irrelium.botman.command;

import net.irrelium.botman.BotMan;
import net.irrelium.botman.event.CommandEvent;
import net.irrelium.botman.listener.CommandListener;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.ListenerAdapter;

import java.util.ArrayList;

public class CommandHandler extends ListenerAdapter {
    String commandPrefix;
    ArrayList<CommandListener> listeners = new ArrayList<CommandListener>();

    public CommandHandler() {
        commandPrefix = BotMan.getConfigHandler().getCommandPrefix();
    } // end constructor

    public void addCommandListener(CommandListener cl) {
        listeners.add(cl);
    } // end addCommandListener

    public void onMessage(MessageEvent event) {
        String command;
        String[] args;
        String sender;

        if (event.getMessage().startsWith(commandPrefix) || event.getMessage().startsWith(BotMan.getBot().getNick())) {
            command = parseCommand(event.getMessage());
            args = parseArgs(event.getMessage());
            sender = event.getUser().getNick();

// This is some testing code just to make sure everything works properly with command parsing

            String argsString = null;
            for (String arg : args) {
                if (argsString == null) argsString = arg;
                else argsString = argsString + " " + arg;
            }
            if (argsString == null) argsString = "none";
            BotMan.getBot().sendIRC().message(event.getChannel().getName(), "Command \"" + command + "\" run by " + event.getUser().getHostmask() + ". Args: " + argsString);

            for (CommandListener cl : listeners) {
                cl.onCommand(new CommandEvent(command, args, sender));
            } // end for
        } // end if
    } // end onMessage

    String parseCommand(String message) {
        if (message.startsWith(commandPrefix)) {
            return message.split("\\s+")[0].substring(1);
        } // end if
        return message.split("\\s+")[1];
    } // end parsecommand

    String[] parseArgs(String message) {
        String[] split = message.split("\\s+");
        String[] args;
        if (message.startsWith(commandPrefix)) {
            args = new String[split.length - 1];
            for (int x = 0, i = 1; x < split.length - 1; x++, i++) {
                args[x] = split[i];
            } // end for
        } else {
            args = new String[split.length - 2];
            for (int x = 0, i = 2; x < split.length - 2; x++, i++) {
                args[x] = split[i];
            } // end for
        } // end if/else
        return args;
    } // end parseArgs
} // end class
