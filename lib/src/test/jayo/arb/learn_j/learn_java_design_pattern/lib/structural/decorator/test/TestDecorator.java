package jayo.arb.learn_j.learn_java_design_pattern.lib.structural.decorator.test;

import jayo.arb.learn_j.learn_java_design_pattern.lib.structural.decorator.DoubleDough;
import jayo.arb.learn_j.learn_java_design_pattern.lib.structural.decorator.IPizza;
import jayo.arb.learn_j.learn_java_design_pattern.lib.structural.decorator.Mushroom;
import jayo.arb.learn_j.learn_java_design_pattern.lib.structural.decorator.SingleDough;
import jayo.arb.learn_j.learn_java_design_pattern.lib.structural.decorator.TomatoSauce;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDecorator {

    @Test
    public void test000() {
        IPizza plainPizza = new Mushroom(new TomatoSauce( new SingleDough() ) );
        Assertions.assertEquals(
                String.format( "%.2f", 34.449f ),
                String.format( "%.2f", plainPizza.getPrice() )
        );
        Assertions.assertEquals(
                34.449997f,
                plainPizza.getPrice()
        );
    }

    @Test
    public void test001() {
        IPizza fatPizza = new TomatoSauce(new Mushroom( new DoubleDough() ) );
        Assertions.assertEquals(
                String.format( "%.2f", 35.65441f ),
                String.format( "%.2f", fatPizza.getPrice() )
        );
        Assertions.assertEquals(
                35.65f,
                fatPizza.getPrice()
        );
    }
}
