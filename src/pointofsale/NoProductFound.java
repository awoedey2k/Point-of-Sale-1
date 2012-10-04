/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pointofsale;

/**
 *
 * @author Piotr
 */
public class NoProductFound extends Exception {

    /**
     * Creates a new instance of
     * <code>NoProductFound</code> without detail message.
     */
    public NoProductFound() {
    }

    /**
     * Constructs an instance of
     * <code>NoProductFound</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public NoProductFound(String msg) {
        super(msg);
    }
}
