package jayo.arb.learn_j.learn_java_design_pattern.lib.behavioral.command;

public class TurnVolDown extends Command {

    public TurnVolDown( IDevice device ) {
        super( device );
    }
    @Override
    public void execute() {
        super.device.volDown();
    }
}
