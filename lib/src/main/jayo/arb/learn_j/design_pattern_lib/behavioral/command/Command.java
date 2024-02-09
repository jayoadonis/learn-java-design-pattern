package jayo.arb.learn_j.design_pattern_lib.behavioral.command;

public abstract class Command {

    public Command( IDevice device ) {
        this.device = device;
    }
    public abstract void execute();

    protected IDevice device;
}
