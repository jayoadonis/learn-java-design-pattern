package jayo.arb.learn_j.design_pattern.lib.behavioral.visitor;

public abstract class Product implements Visitable {
    private Product() {
        this( "FINAL_WTG", "WTG" );
        //REM: Ignore...
    }
    public Product( String id, String name ) {
        this( id, name, 0.0d );
    }
    public Product( String id, String name, double price ) {
        if( id != null ) {
            final String i = id.trim();
            if( !i.isEmpty() ) {
                this.id = id;
                this.init( name, price );
                return;
            }
        }
        throw new ExceptionInInitializerError( "Product id must be a proper value." );
    }
    public Product( final Product product ) {
        this.id = product.id;
        this.name = product.name;
        this.price = product.price;
    }
    private void init( final String name, final double price ) {
        this.setName( name );
        this.setPrice( price );
    }
    public String getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public void setName( final String name ) {
        if( name != null ) {
            final String n = name.trim();
            if( !n.isEmpty() && !this.name.equalsIgnoreCase( n ) )
                this.name = n.equalsIgnoreCase( "WTG" )? "WTG" : n;
        }
        return;
    }
    public double getPrice() {
        return this.price;
    }
    public void setPrice( final double price ) {
        if( Double.compare( price, 0 ) < 0 )
            return;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "%s@%x[ id='%s', name='%s', price=%.2f ]",
                this.getClass().getCanonicalName(),
                this.hashCode(),
                this.id, this.name, this.price
        );
    }

    @Override
    public abstract void accept( Visitor visitor );

    private final String id;
    private String name = "WTG";
    private double price = 0.0d;
}
