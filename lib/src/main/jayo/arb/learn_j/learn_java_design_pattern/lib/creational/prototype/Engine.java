package jayo.arb.learn_j.learn_java_design_pattern.lib.creational.prototype;

import jayo.arb.learn_j.learn_java_design_pattern.lib.behavioral.command.IDevice;

public abstract class Engine implements IDevice, ICloneable<Engine> {

    public Engine( String name ) {
        this.name = name;
        this.description = "N/a";
        this.isBroken = false;
        this.isOn = false;
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
        throw new RuntimeException("No Such Method");
    }

    @Override
    public void volDown() {
        throw new RuntimeException("No Such Method");
    }

    @Override
    public boolean isOn() {
        return this.isOn && !this.isBroken;
    }

    @Override
    public int getVolume() {
        throw new RuntimeException("No Such Method");
    }

    public String getName() {
        return this.name;
    }

    protected final void setName( String name ) {
        this.name = name;
    }

    public boolean isBroken() {
        return this.isBroken;
    }

    protected final void setIsBroken( boolean isBroken ) {
        this.isBroken = isBroken;
    }

    public String getDescription() {
        return this.description;
    }

    protected final void setDescription( String description ) {
        this.description = description;
    }

    @Override
    public abstract Engine clone() throws CloneNotSupportedException;

    private boolean isOn;
    private String name;
    private String description;
    private boolean isBroken;
}
