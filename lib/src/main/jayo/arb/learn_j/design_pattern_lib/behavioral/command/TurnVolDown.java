package jayo.arb.learn_j.design_pattern_lib.behavioral.command;

public class TurnVolDown extends Command {

    public TurnVolDown( IDevice device ) {
        super( device );
    }
    @Override
    public void execute() {
        super.device.volDown();
    }
}
