package jayo.arb.learn_j.learn_java_design_pattern.lib.structural.decorator;

public class TomatoSauce extends Topping {

    public TomatoSauce( IPizza pizza ) {
        super( pizza );
    }

    @Override
    public float getPrice() {
        return super.pizza.getPrice() + 10.9f;
    }
}
