package jayo.arb.learn_j.design_pattern_lib.behavioral.command;

public class TurnOn extends Command {

    public TurnOn( IDevice device ) {
        super( device );
    }
    @Override
    public void execute() {
        super.device.on();
    }
}
