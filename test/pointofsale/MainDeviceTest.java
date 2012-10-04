package pointofsale;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pointofsale.api.IsDAO;
import pointofsale.api.IsDisplay;
import pointofsale.api.IsPrinter;
import pointofsale.api.IsScanner;
import pointofsale.devices.Display;
import pointofsale.devices.Printer;
import pointofsale.devices.Scanner;

/**
 *
 * @author Piotr
 */
public class MainDeviceTest {

    MainDevice mainDevice;
    Scanner scanner;
    Display display;
    private List<Object> products = new ArrayList<>();

    @Before
    public void setUp() {

        mainDevice = new MainDevice() {
            @Override
            protected IsDisplay createDisplay() {
                return new Display();
            }

            @Override
            protected IsPrinter createPrinter() {
                return new Printer();
            }

            @Override
            protected IsScanner createScanner() {
                return new Scanner();
            }

            @Override
            protected IsDAO createDAO() {
                return new DAO();
            }
        };
        mainDevice.start();
        display = (Display) mainDevice.getDisplay();
        scanner = (Scanner) mainDevice.getScanner();
    }

    public void passSmthToScan(Object smthToScan) {
        synchronized (scanner) {
            try {
                System.out.println("Passing object.." + smthToScan);
                scanner.setSmthToScan(smthToScan);
                scanner.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(MainDeviceTest.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Test(timeout = 100000L)
    public void test() {
        passSmthToScan(111111L); //GreatBook
        assertEquals("GreatBook:100.0", display.getOnDisplay());
        passSmthToScan(222222L); //WorkBook
        assertEquals("WorkBook:10.0", display.getOnDisplay());
        passSmthToScan(333333L); //Novel
        assertEquals("Novel:20.0", display.getOnDisplay());
        passSmthToScan(444444L); //Poem
        assertEquals("Poem:30.0", display.getOnDisplay());
        passSmthToScan(555555L); //SciFiBook
        assertEquals("SciFiBook:10.0", display.getOnDisplay());
        passSmthToScan(666666L); //ExcerciseBook
        assertEquals("ExcerciseBook:10.0", display.getOnDisplay());

        passSmthToScan(123456L); // Non existing
        assertEquals("Product not found", display.getOnDisplay());

        passSmthToScan(""); // Empty code
        assertEquals("Invalid bar-code", display.getOnDisplay());

        passSmthToScan("invalid"); // Empty code
        assertEquals("Invalid bar-code", display.getOnDisplay());
        
        mainDevice.onExit(); // Show total and print receipt

    }
}
