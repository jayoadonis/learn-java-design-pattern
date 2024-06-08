package jayo.arb.learn_j.learn_java_design_pattern.lib.behavioral.command;

public class TurnOn extends Command {

    public TurnOn( IDevice device ) {
        super( device );
    }
    @Override
    public void execute() {
        super.device.on();
    }
}
