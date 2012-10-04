/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pointofsale.api;

import pointofsale.NoProductFound;
import pointofsale.Product;

/**
 *
 * @author Piotr
 */
public interface IsDAO {

    Product queryProducts(long barCode) throws NoProductFound;
    
}
