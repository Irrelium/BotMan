package net.ryanmck.robolium.plugin;

import org.pircbotx.PircBotX;
import net.ryanmck.robolium.Robolium;

public abstract class Plugin {

    public abstract void enable();

    public abstract void disable();

    public PircBotX getBot() {
        return Robolium.getBot();
    } //end getBot

} //end class
