package jayo.arb.learn_j.design_pattern_lib.behavioral.visitor.test;

import jayo.arb.learn_j.design_pattern_lib.behavioral.visitor.HolidayTaxCalc;
import jayo.arb.learn_j.design_pattern_lib.behavioral.visitor.Necessity;
import jayo.arb.learn_j.design_pattern_lib.behavioral.visitor.NormalTaxCalc;
import jayo.arb.learn_j.design_pattern_lib.behavioral.visitor.Product;
import jayo.arb.learn_j.design_pattern_lib.behavioral.visitor.Visitor;
import jayo.arb.learn_j.design_pattern_lib.behavioral.visitor.Wants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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
