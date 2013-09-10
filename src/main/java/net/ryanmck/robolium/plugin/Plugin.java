package net.ryanmck.robolium.plugin;

import net.ryanmck.robolium.Robolium;
import org.pircbotx.PircBotX;

public abstract class Plugin {

    public abstract void enable();

    public abstract void disable();

    public PircBotX getBot() {
        return Robolium.getBot();
    } // end getBot

} // end class
