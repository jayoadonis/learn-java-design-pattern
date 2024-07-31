package jayo.arb.learn_j.learn_java_design_pattern.lib.creational.prototype;

public class LandVehicles extends Vehicles {

    public LandVehicles( String name, int numberWheel ) {
        super( name, new LandEngine() );
        this.numberWheel = numberWheel;
    }

    public LandVehicles( LandVehicles landVehicles ) {
        this( landVehicles.getName(), landVehicles.numberWheel );
    }

    @Override
    public LandVehicles clone() throws CloneNotSupportedException {
        return new LandVehicles( this );
    }

    public int getNumberWheel() {
        return this.numberWheel;
    }

    protected final void setNumberWheel( int numberWheel ) {
        this.numberWheel = numberWheel;
    }

    private int numberWheel;
}
