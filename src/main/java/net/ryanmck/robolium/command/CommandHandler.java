package net.ryanmck.robolium.command;

import net.ryanmck.robolium.listener.CommandListener;
import net.ryanmck.robolium.event.CommandEvent;
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
        commandPrefix = "!";
    } //end constructor

    public void addCommandListener(CommandListener cl) {
        listeners.add(cl);
    } //end addCommandListener

    public void onMessage(MessageEvent event) {
        String command;
        String[] args;
        String sender;

        if (event.getMessage().startsWith(commandPrefix)) {
            command = parseCommand(event.getMessage());
            args = parseArgs(event.getMessage());
            sender = event.getUser().getNick();

            for (CommandListener cl : listeners) {
                cl.onCommand(new CommandEvent(command, args, sender));
            }
        } //end if
    } //end onMessage

    String parseCommand(String message) {
        return message.split(" ")[0].substring(1);
    } //end parsecommand

    String[] parseArgs(String message) {
        String[] split = message.split(" ");
        String[] args = new String[split.length - 1];
        int x = 0;
        for (int i = 1; i < split.length; i++) {
            args[x] = split[i];
            x++;
        } //end for
        return args;
    } //end parseArgs
} //end class
