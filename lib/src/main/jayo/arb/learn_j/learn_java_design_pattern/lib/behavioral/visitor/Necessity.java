package jayo.arb.learn_j.learn_java_design_pattern.lib.behavioral.visitor;

public class Necessity extends Product {

    public Necessity( String id, String name ) {
        super( id, name );
    }

    public Necessity(
            String id,
            String name,
            double price
    ) {
        super( id, name, price );
    }

    @Override
    public void accept( Visitor visitor ) {
        visitor.visit( this );
        //REM: TODO-HERE; do more logic...
    }

}
