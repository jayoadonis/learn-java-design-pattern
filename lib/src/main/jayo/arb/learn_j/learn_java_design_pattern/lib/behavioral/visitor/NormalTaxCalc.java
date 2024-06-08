package jayo.arb.learn_j.learn_java_design_pattern.lib.behavioral.visitor;


public class NormalTaxCalc implements Visitor {

    public void visit( Product product ) {

        if( product instanceof Necessity )
            product.setPrice( product.getPrice() + ( product.getPrice() * 0.05d ) );
        else if( product instanceof Wants )
            product.setPrice( product.getPrice() + ( product.getPrice() * 0.10d ) );
    }

}