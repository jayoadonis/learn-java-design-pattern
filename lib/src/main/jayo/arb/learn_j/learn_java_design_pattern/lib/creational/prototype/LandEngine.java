package jayo.arb.learn_j.learn_java_design_pattern.lib.creational.prototype;

public class LandEngine extends Engine {
    public LandEngine() {
        this("LAND_ENGINE");
    }
    public LandEngine( String name ) {
        super( name );
        super.setDescription("[DESCRIPTION:LAND_ENGINE]");
    }
    public LandEngine( LandEngine landEngine ) {
        this( landEngine.getName() );
    }
    @Override
    public LandEngine clone() throws CloneNotSupportedException {
        return new LandEngine( this );
    }
}
