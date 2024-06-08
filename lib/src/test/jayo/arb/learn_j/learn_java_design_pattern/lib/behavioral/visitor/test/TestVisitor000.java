package jayo.arb.learn_j.learn_java_design_pattern.lib.behavioral.visitor.test;

import jayo.arb.learn_j.learn_java_design_pattern.lib.behavioral.visitor.HolidayTaxCalc;
import jayo.arb.learn_j.learn_java_design_pattern.lib.behavioral.visitor.Necessity;
import jayo.arb.learn_j.learn_java_design_pattern.lib.behavioral.visitor.Visitor;
import jayo.arb.learn_j.learn_java_design_pattern.lib.behavioral.visitor.Wants;
import jayo.arb.learn_j.learn_java_design_pattern.lib.behavioral.visitor.NormalTaxCalc;
import jayo.arb.learn_j.learn_java_design_pattern.lib.behavioral.visitor.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestVisitor000 {

    private Product rice;
    private Product cake;
    private Visitor taxer;

    @BeforeEach
    public void init() {
        this.rice = new Necessity( "n-101", "Rice", 0.0 );
        this.cake = new Wants( "w-101", "Cake", 0.0 );
    }

    @Test
    @Order( 0 )
    public void testNormalTaxCalc() {
        System.out.println( "::: testNormalTaxCalc()V" );
        System.out.println( "::: " + this.rice );
        this.taxer = new NormalTaxCalc();
        this.rice.setPrice( 40.09 );
        this.rice.accept( this.taxer );
        Assertions.assertNotEquals( 40.09, this.rice.getPrice() );
        Assertions.assertTrue( Double.compare( 40.09, this.rice.getPrice() ) < 0 );
        System.out.println( "::: " + this.rice );

        System.out.println( "::: " + this.cake );
        this.taxer = new NormalTaxCalc();
        this.cake.setPrice( 40.09 );
        this.cake.accept( this.taxer );

        Assertions.assertEquals( 0, Double.compare( 44.099, QuickUtil.round(this.cake.getPrice(), 3) ) );
        Assertions.assertNotEquals( 40.09, this.cake.getPrice() );
        Assertions.assertTrue( Double.compare( 40.09, this.cake.getPrice() ) < 0 );
        System.out.println( "::: " + this.cake );
    }

    @Test
    @Order( 1 )
    public void testHolidayTaxCalc() {
        System.out.println( "::: testHolidayTaxCalc()V" );
        System.out.println( "::: " + this.rice );
        this.taxer = new HolidayTaxCalc();
        this.rice.setPrice( 40.09 );
        this.rice.accept( this.taxer );
        Assertions.assertNotEquals( 40.09, this.rice.getPrice() );
        Assertions.assertTrue( Double.compare( 40.09, this.rice.getPrice() ) < 0 );
        System.out.println( "::: " + this.rice );

        System.out.println( "::: " + this.cake );
        this.taxer = new HolidayTaxCalc();
        this.cake.setPrice( 40.09 );
        this.cake.accept( this.taxer );
        Assertions.assertNotEquals( 40.09, this.cake.getPrice() );
        Assertions.assertTrue( Double.compare( 40.09, this.cake.getPrice() ) < 0 );
        System.out.println( "::: " + this.cake );
    }
}

class QuickUtil {
    public static double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException("2nd param named 'places' should not be less than zero.");

        final long factor = (long) Math.pow(10, places); //REM: 10^places, such as 10^1 == 10th place.
        final long tmp = Math.round( (value * factor ) );//REM: n * (10^d), where n = target floating value, and d = the # of precision(s).
        return (double) tmp / factor;
    }
}
