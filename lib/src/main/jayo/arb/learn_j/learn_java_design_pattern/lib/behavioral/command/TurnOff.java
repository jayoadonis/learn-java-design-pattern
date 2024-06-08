package jayo.arb.learn_j.learn_java_design_pattern.lib.behavioral.command;

public class TurnOff extends Command {
    public TurnOff( IDevice device ) {
        super( device );
    }
    @Override
    public void execute() {
        super.device.off();
    }
}
