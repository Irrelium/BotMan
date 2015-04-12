package net.irrelium.botman.listener;

import net.irrelium.botman.event.CommandEvent;

public interface CommandListener {
    public abstract void onCommand(CommandEvent event);
} // end commandListener
