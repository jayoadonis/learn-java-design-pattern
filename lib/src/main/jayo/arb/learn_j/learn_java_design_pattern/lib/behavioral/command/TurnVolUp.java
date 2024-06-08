package jayo.arb.learn_j.learn_java_design_pattern.lib.behavioral.command;

public class TurnVolUp extends Command {

    public TurnVolUp( IDevice device ) {
        super( device );
    }
    @Override
    public void execute() {
        super.device.volUp();
    }
}
