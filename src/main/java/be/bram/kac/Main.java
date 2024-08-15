package be.bram.kac;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main( String[] args ) {
        App app = new App();
        app.run();
    }
    
    
    static void visit() {
    	Visitor visitor = new Visitor();
    	Color.BLACK.visit(visitor);
    }
    
}
