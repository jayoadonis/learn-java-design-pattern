package jayo.arb.learn_j.learn_java_design_pattern.lib.creational.prototype;

public class BrokenEngine extends Engine {

    public BrokenEngine( String name ) {
        super(name);
        super.setIsBroken(true);
        super.setDescription( "[DESCRIPTION:BROKEN_ENGINE]" );
    }

    public BrokenEngine( BrokenEngine brokenEngine ) {
        this(brokenEngine.getName());
    }

    @Override
    public BrokenEngine clone() throws CloneNotSupportedException {
        return new BrokenEngine( this );
    }
}
