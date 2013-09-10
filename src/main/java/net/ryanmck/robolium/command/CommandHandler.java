package net.ryanmck.robolium.command;

import net.ryanmck.robolium.Robolium;
import net.ryanmck.robolium.listener.CommandListener;
import net.ryanmck.robolium.event.CommandEvent;
import net.ryanmck.robolium.config.ConfigHandler;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.managers.ListenerManager;
import java.util.ArrayList;

public class CommandHandler extends ListenerAdapter {
    String commandPrefix;
    ArrayList<CommandListener> listeners = new ArrayList<CommandListener>();

    public CommandHandler() {
        commandPrefix = Robolium.getConfigHandler().getCommandPrefix();
    } // end constructor

    public void addCommandListener(CommandListener cl) {
        listeners.add(cl);
    } // end addCommandListener

    public void onMessage(MessageEvent event) {
        String command;
        String[] args;
        String sender;

        if (event.getMessage().startsWith(commandPrefix) || event.getMessage().startsWith(Robolium.getBot().getNick())) {
            command = parseCommand(event.getMessage());
            args = parseArgs(event.getMessage());
            sender = event.getUser().getNick();

// This is some testing code just to make sure everything works properly with command parsing

//            String argsString = null;
//            for (String arg : args) {
//                if (argsString == null) argsString = arg;
//                else argsString = argsString + " " + arg;
//            }
//            if (argsString == null) argsString = "none";
//            Robolium.getBot().sendMessage(event.getChannel(), "Command \"" + command + "\" run by " + sender + ". Args: " + argsString);

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
