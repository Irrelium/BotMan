package net.ryanmck.botman.plugin;

import net.ryanmck.botman.BotMan;
import org.pircbotx.PircBotX;

public abstract class Plugin {

    public abstract void enable();

    public abstract void disable();

    public PircBotX getBot() {
        return BotMan.getBot();
    } // end getBot

} // end class
