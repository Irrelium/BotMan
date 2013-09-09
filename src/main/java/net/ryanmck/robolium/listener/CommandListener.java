package net.ryanmck.robolium.listener;

import net.ryanmck.robolium.event.CommandEvent;

public interface CommandListener {
    public abstract void onCommand(CommandEvent event);
} // end commandListener
