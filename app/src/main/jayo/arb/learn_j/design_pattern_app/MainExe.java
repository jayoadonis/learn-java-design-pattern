package jayo.arb.learn_j.design_pattern_app;

import jayo.arb.learn_j.design_pattern_lib.behavioral.command.Button;
import jayo.arb.learn_j.design_pattern_lib.behavioral.command.Command;
import jayo.arb.learn_j.design_pattern_lib.behavioral.command.IDevice;
import jayo.arb.learn_j.design_pattern_lib.behavioral.command.Television;
import jayo.arb.learn_j.design_pattern_lib.behavioral.command.TurnOff;
import jayo.arb.learn_j.design_pattern_lib.behavioral.command.TurnOn;
import jayo.arb.learn_j.design_pattern_lib.behavioral.visitor.Necessity;
import jayo.arb.learn_j.design_pattern_lib.behavioral.visitor.NormalTaxCalc;
import jayo.arb.learn_j.design_pattern_lib.behavioral.visitor.Product;
import jayo.arb.learn_j.design_pattern_lib.behavioral.visitor.Visitor;
import jayo.arb.learn_j.design_pattern_lib.behavioral.visitor.Wants;

public class MainExe {
    public static void main( String[] args ) {

        //REM: VISITOR DP
        Visitor taxer = new NormalTaxCalc();
        Product rice = new Necessity( "necessity-001", "  rice  ", 45.5d );
        Product alcohol = new Wants( "wants-002", "  alcohol  ", 55.99d );
        System.out.println( rice );
        System.out.println( alcohol );
        rice.accept( taxer );
        alcohol.accept( taxer );
        System.out.println( rice );
        System.out.println( alcohol );
        System.out.println( "::: Hi there!" );


        //REM: COMMAND DP
        IDevice d1 = new Television();
        System.out.println( "::: " + d1.isOn() );
        Command c1 = new TurnOn( d1 );
        Button b1 = new Button( c1 );
        b1.press();
        System.out.println( "::: " + d1.isOn() );
        c1 = new TurnOff( d1 );
        b1.setCommand( c1 );
        b1.press();
        System.out.println( "::: " + d1.isOn() );
    }
}
