package jayo.arb.learn_j.design_pattern_lib.behavioral.command;

public interface IDevice {

    public abstract void on();
    public abstract void off();
    public abstract void volUp();
    public abstract void volDown();

    public abstract boolean isOn();
    public abstract int getVolume();
}
