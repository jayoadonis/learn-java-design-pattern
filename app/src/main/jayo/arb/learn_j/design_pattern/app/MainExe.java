package jayo.arb.learn_j.design_pattern.app;

import jayo.arb.learn_j.design_pattern.lib.behavioral.visitor.HolidayTaxCalc;
import jayo.arb.learn_j.design_pattern.lib.behavioral.visitor.Necessity;
import jayo.arb.learn_j.design_pattern.lib.behavioral.visitor.Product;
import jayo.arb.learn_j.design_pattern.lib.behavioral.visitor.Visitor;
import jayo.arb.learn_j.design_pattern.lib.behavioral.visitor.Wants;

public class MainExe {
    public static void main( String[] args ) {

        Visitor taxer = new HolidayTaxCalc();
        Product rice = new Necessity( "necessity-001", "  rice  ", 45.5d );
        Product alcohol = new Wants( "wants-002", "  alcohol  ", 55.99d );
        System.out.println( rice );
        System.out.println( alcohol );
        rice.accept( taxer );
        alcohol.accept( taxer );
        System.out.println( rice );
        System.out.println( alcohol );
        System.out.println( "::: Hi there!" );
    }
}
