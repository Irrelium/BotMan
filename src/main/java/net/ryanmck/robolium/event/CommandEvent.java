package net.ryanmck.robolium.event;

public class CommandEvent {
    String command;
    String[] args;
    String sender;

    public CommandEvent(String command, String[] args, String sender) {
        this.command = command;
        this.args = args;
        this.sender = sender;
    } // end constructor

    public String getCommand() {
        return command;
    } // end getCommand

    public String[] getArgs() {
        return args;
    } // end getArgs

    public String getSender() {
        return sender;
    } // end getSender
} // end class
