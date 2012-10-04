package pointofsale.api;

import pointofsale.Product;

/**
 *
 * @author Piotr
 */
public interface IsPrinter {

    public void printProduct(Product product);

    public void printTotal(Double total);
}
