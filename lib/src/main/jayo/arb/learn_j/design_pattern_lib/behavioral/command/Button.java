package jayo.arb.learn_j.design_pattern_lib.behavioral.command;

public class Button {

    public Button( Command command ) {
        this.command = command;
    }

    public void press() {
        this.command.execute();
    }

    public void setCommand( Command command ) {
        this.command = command;
    }

    private Command command;
}
