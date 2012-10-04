package pointofsale.api;

import pointofsale.Product;

/**
 *
 * @author Piotr
 */
public interface IsDisplay {

    public void displayProduct(Product product);

    public void displayError(String message);

    public void displayTotal(Double total);
}
