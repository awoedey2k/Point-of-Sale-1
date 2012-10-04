/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pointofsale;

import java.util.ArrayList;
import java.util.List;
import pointofsale.api.IsDAO;
import pointofsale.api.IsDisplay;
import pointofsale.api.IsPrinter;
import pointofsale.api.IsScanner;

/**
 *
 * Main Point of Sale device.
 * 
 * @author Piotr
 */
public class MainDevice {

    IsDisplay display;
    IsPrinter printer;
    IsScanner scanner;
    IsDAO dao;
    Thread scannerThread;
    List<Product> receipt = new ArrayList<>();

    public MainDevice() {
        display = createDisplay();
        scanner = createScanner();
        scanner.attachTo(this);
        printer = createPrinter();
        dao = createDAO();
    }

    /**
     * Start Point of Sale device
     */
    public void start() {
        scannerThread = new Thread(scanner);
        scannerThread.start();
        scannerThread.setName("Scanner");
    }

    /**
     * Stop Point of Sale device
     */
    public void stop() {
        scannerThread.interrupt();
    }

    public void onValidBarCode(Long barCode) {
        try {
            Product product = dao.queryProducts(barCode);
            getDisplay().displayProduct(product);
            receipt.add(product);
        } catch (NoProductFound ex) {
            getDisplay().displayError("Product not found");
        }
    }

    public void onInvalidBarCode() {
        getDisplay().displayError("Invalid bar-code");
    }

    public void onExit() {
        double total = 0.00d;
        for (Product p : receipt) {
            total += p.getPrice();
            printer.printProduct(p);
        }
        printer.printTotal(total);
        display.displayTotal(total);
    }

    /**
     * Creates and returns LCD device
     *
     * @return
     */
    protected IsDisplay createDisplay() {
        // TODO Implement real LCD
        return null;
    }

    /**
     * Creates and returns Bar Code Scanner device
     *
     * @return
     */
    protected IsScanner createScanner() {
        // TODO Implement real Bar Code Scanner
        return null;
    }

    /**
     * Creates and returns Printer device
     *
     * @return
     */
    protected IsPrinter createPrinter() {
        // TODO Implement real Printer
        return null;
    }

    /**
     * Creates and returns DAO.
     *
     * @return
     */
    protected IsDAO createDAO() {
        // TODO Implement real Printer
        return null;
    }

    public IsDisplay getDisplay() {
        return display;
    }

    public IsPrinter getPrinter() {
        return printer;
    }

    public IsDAO getDao() {
        return dao;
    }

    public IsScanner getScanner() {
        return scanner;
    }

    public Thread getScannerThread() {
        return scannerThread;
    }
}
