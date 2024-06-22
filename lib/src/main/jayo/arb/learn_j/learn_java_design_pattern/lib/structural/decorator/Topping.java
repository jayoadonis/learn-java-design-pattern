package jayo.arb.learn_j.learn_java_design_pattern.lib.structural.decorator;

public abstract class Topping implements IPizza {
    protected IPizza pizza;
    public Topping( IPizza pizza ) {
        this.pizza = pizza;
    }
}
