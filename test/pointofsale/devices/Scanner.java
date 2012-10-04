/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pointofsale.devices;

/**
 *
 * Pretends to be a scanning device. Products are scanned in a loop from a smthToScan object.
 * Works as an independent thread.
 * 
 * @author Piotr
 */
public class Scanner extends AbstractScanner {

    Object smthToScan;
    boolean inProgress = false;

    @Override
    public void scan() {
        synchronized (this) {
            if (getSmthToScan() != null) {
                doScan(smthToScan);
                setSmthToScan(null);
            }
            notifyAll();

        }


    }

    private void doScan(Object smthToScan) {
        // Logger.getAnonymousLogger().log(Level.INFO, "Performing scan..");
        if (smthToScan != null && smthToScan instanceof Long) {
            //     Logger.getAnonymousLogger().log(Level.INFO, "Valid Bar Code: {0}", smthToScan);
            mainDevice.onValidBarCode((Long) smthToScan);
        } else {
            //   Logger.getAnonymousLogger().log(Level.INFO, "Invalid Bar Code: {0}", smthToScan);
            mainDevice.onInvalidBarCode();
        }
    }

    public synchronized void passSmthToScan(Object smthToScan) {
        //Logger.getAnonymousLogger().log(Level.INFO, "Passing object.." + smthToScan);
        setSmthToScan(smthToScan);
    }

    public void setSmthToScan(Object smthToScan) {
        this.smthToScan = smthToScan;
    }

    public Object getSmthToScan() {
        return smthToScan;
    }
}
