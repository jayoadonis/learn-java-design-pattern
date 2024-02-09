package jayo.arb.learn_j.design_pattern_lib.behavioral.command;

public class TurnVolUp extends Command {

    public TurnVolUp( IDevice device ) {
        super( device );
    }
    @Override
    public void execute() {
        super.device.volUp();
    }
}
