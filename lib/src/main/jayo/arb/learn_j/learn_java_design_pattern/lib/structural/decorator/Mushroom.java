package jayo.arb.learn_j.learn_java_design_pattern.lib.structural.decorator;

public class Mushroom extends Topping {

    public Mushroom( IPizza pizza ) {
        super( pizza );
    }
    @Override
    public float getPrice() {
        return super.pizza.getPrice() + 23.55f;
    }
}
