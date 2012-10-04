/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pointofsale.devices;

import java.util.logging.Level;
import java.util.logging.Logger;
import pointofsale.MainDevice;
import pointofsale.api.IsScanner;

/**
 *
 * @author Piotr
 */
public abstract class AbstractScanner implements IsScanner {

    MainDevice mainDevice;

    @Override
    public void attachTo(MainDevice dev) {
        this.mainDevice = dev;
    }

    @Override
    public abstract void scan();

    @Override
    public void run() {
        System.out.println("Starting up scanner");
        while (true) {
            scan();
        }
    }
}
