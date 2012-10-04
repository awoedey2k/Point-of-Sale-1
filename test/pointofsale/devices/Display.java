package pointofsale.devices;

import pointofsale.Product;
import pointofsale.api.IsDisplay;

/**
 *
 * Pretends to be a display device. Messages are displayed on STDOUT and saved to onDisplay field.
 * 
 * @author Piotr
 */
public class Display implements IsDisplay {

    String onDisplay;

    @Override
    public void displayProduct(Product product) {
        this.onDisplay = product.getName() + ":" + product.getPrice();
        System.out.println(onDisplay);
    }

    @Override
    public void displayError(String message) {
        this.onDisplay = message;
        System.out.println(onDisplay);
    }

    public String getOnDisplay() {
        return onDisplay;
    }

    @Override
    public void displayTotal(Double total) {
        this.onDisplay = "TOTAL:" + total;
        System.out.println(onDisplay);
    }
}
