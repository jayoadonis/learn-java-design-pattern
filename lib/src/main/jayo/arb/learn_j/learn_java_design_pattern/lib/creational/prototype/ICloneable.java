package jayo.arb.learn_j.learn_java_design_pattern.lib.creational.prototype;

public interface ICloneable<T> {
    public abstract T clone() throws CloneNotSupportedException;
}
