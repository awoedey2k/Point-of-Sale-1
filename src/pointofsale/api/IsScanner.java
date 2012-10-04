package pointofsale.api;

import pointofsale.MainDevice;

/**
 *
 * @author Piotr
 */
public interface IsScanner extends Runnable {

    public void attachTo(MainDevice dev);

    public void scan();
}
