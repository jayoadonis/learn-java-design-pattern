package jayo.arb.learn_j.learn_java_design_pattern.lib.creational.prototype;

import jayo.arb.learn_j.learn_java_design_pattern.lib.behavioral.command.Command;
import jayo.arb.learn_j.learn_java_design_pattern.lib.behavioral.command.TurnOff;

public abstract class Vehicles implements ICloneable<Vehicles> {

    public Vehicles( String name, Engine engine ) {
        this.name = name;
        this.engine = engine;
        this.command = new TurnOff(engine);
    }

    public String getName() {
        return this.name;
    }

    public Engine getEngine() {
        return this.engine;
    }
    public Engine  getCloneEngine() {
        Engine cloneEngine = null;
        try {
             cloneEngine = this.engine.clone();
        }
        catch( CloneNotSupportedException cNSEx ) {
            cloneEngine = new BrokenEngine( this.engine.getName() );
        }
        return cloneEngine;
    }

    public Command getCommand() {
        return this.command; //REM: . [TODO] . Did not forceful implement 'ICloneable' or 'java.lang.Cloneable', maybe because it is a behavioral DP
    }

    public void setIsBroken( boolean isBroken ) {
        this.engine.setIsBroken( isBroken );
    }

    public boolean isBroken() {
        return this.engine.isBroken();
    }

    public boolean isOn() {
        return this.engine.isOn();
    }

    @Override
    public abstract Vehicles clone() throws CloneNotSupportedException;

    private final Command command;//REM: . [TODO] . Did not forceful implement 'ICloneable' or 'java.lang.Cloneable', maybe because it is a behavioral DP
    private final Engine engine;
    private final String name;
}
