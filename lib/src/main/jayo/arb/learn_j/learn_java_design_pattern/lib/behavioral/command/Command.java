package jayo.arb.learn_j.learn_java_design_pattern.lib.behavioral.command;

public abstract class Command {

    public Command( IDevice device ) {
        this.device = device;
    }
    public abstract void execute();

    protected IDevice device;
}
