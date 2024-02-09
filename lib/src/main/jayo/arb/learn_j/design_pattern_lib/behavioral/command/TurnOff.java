package jayo.arb.learn_j.design_pattern_lib.behavioral.command;

public class TurnOff extends Command {
    public TurnOff( IDevice device ) {
        super( device );
    }
    @Override
    public void execute() {
        super.device.off();
    }
}
