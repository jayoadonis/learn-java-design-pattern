package jayo.arb.learn_j.learn_java_design_pattern.lib.creational.prototype.test;

import jayo.arb.learn_j.learn_java_design_pattern.lib.creational.prototype.Engine;
import jayo.arb.learn_j.learn_java_design_pattern.lib.creational.prototype.LandVehicles;
import jayo.arb.learn_j.learn_java_design_pattern.lib.creational.prototype.Vehicles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPrototype {

    @Test
    public void testInstantiation() {
        Vehicles beanNemesisCar = new LandVehicles("Bean Nemesis", 3 );
        System.out.println(">>> " + beanNemesisCar.getName() );

        Engine beanNemesisCarEngine = beanNemesisCar.getEngine();
        System.out.println(">>> " + beanNemesisCarEngine.getName() );

        Assertions.assertFalse( beanNemesisCarEngine.isOn() );
        Assertions.assertFalse( beanNemesisCar.isOn() );

        beanNemesisCar.getCloneEngine().on();
        Assertions.assertFalse( beanNemesisCarEngine.isOn() );
        Assertions.assertFalse( beanNemesisCar.isOn() );

        beanNemesisCar.getEngine().on();
        Assertions.assertTrue( beanNemesisCarEngine.isOn() );
        Assertions.assertTrue( beanNemesisCar.isOn() );
        System.out.println(">>> numberWheel: " + ((LandVehicles)beanNemesisCar).getNumberWheel() );
        System.out.println(">>> isBroken: " + beanNemesisCarEngine.isBroken() );
        System.out.println(">>> isOn: " + beanNemesisCarEngine.isOn() );

        System.out.println("---");

        beanNemesisCar.setIsBroken( true );
        Assertions.assertFalse( beanNemesisCarEngine.isOn() );
        Assertions.assertFalse( beanNemesisCar.isOn() );
        System.out.println(">>> numberWheel: " + ((LandVehicles)beanNemesisCar).getNumberWheel() );
        System.out.println(">>> isBroken: " + beanNemesisCarEngine.isBroken() );
        System.out.println(">>> isOn: " + beanNemesisCarEngine.isOn() );

        System.out.println("---");

        beanNemesisCar.getEngine().on();
        Assertions.assertFalse( beanNemesisCarEngine.isOn() );
        Assertions.assertFalse( beanNemesisCar.isOn() );
        System.out.println(">>> numberWheel: " + ((LandVehicles)beanNemesisCar).getNumberWheel() );
        System.out.println(">>> isBroken: " + beanNemesisCarEngine.isBroken() );
        System.out.println(">>> isOn: " + beanNemesisCarEngine.isOn() );
    }
}
