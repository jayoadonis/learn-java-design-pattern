package jayo.arb.learn_j.design_pattern_lib.behavioral.visitor;

public class Wants extends Product {

    public Wants( String id, String name ) {
        super( id, name );
    }

    public Wants(
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
