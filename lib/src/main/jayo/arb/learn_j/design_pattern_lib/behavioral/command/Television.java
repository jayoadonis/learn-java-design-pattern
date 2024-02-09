package jayo.arb.learn_j.design_pattern_lib.behavioral.command;

public class Television implements IDevice {

    public Television() {

    }

    @Override
    public void on() {
        this.isOn = true;
    }

    @Override
    public void off() {
        this.isOn = false;
    }

    @Override
    public void volUp() {
        if( this.volume >= 100 )
            return;
        ++this.volume;
    }

    @Override
    public void volDown() {
        if( this.volume <= 0 )
            return;
        --this.volume;
    }

    @Override
    public final boolean isOn() {
        return this.isOn;
    }

    @Override
    public final int getVolume() {
        return this.volume;
    }

    private boolean isOn;
    private int volume;
}
