package net.ryanmck.botman.listener;

import net.ryanmck.botman.event.CommandEvent;

public interface CommandListener {
    public abstract void onCommand(CommandEvent event);
} // end commandListener
