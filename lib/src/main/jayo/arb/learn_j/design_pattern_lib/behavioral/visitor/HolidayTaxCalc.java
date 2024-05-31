package jayo.arb.learn_j.design_pattern_lib.behavioral.visitor;

public class HolidayTaxCalc implements Visitor {

    @Override
    public void visit( Product product ) {
        if( product instanceof Necessity )
            product.setPrice( product.getPrice() + ( product.getPrice() * 0.02d ) );
        else if( product instanceof Wants )
            product.setPrice( product.getPrice() + ( product.getPrice() * 0.07d ) );
    }
}
