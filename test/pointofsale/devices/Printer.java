/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pointofsale.devices;

import pointofsale.Product;
import pointofsale.api.IsPrinter;

/**
 *
 * Pretends to be a printing device. Messages are displayed on STDOUT.
 * 
 * @author Piotr
 */
public class Printer implements IsPrinter {

    static int MAX_LENGTH = 50;

    public Printer() {
    }

    @Override
    public void printProduct(Product product) {
        printLine(product.getName(), product.getPrice());
    }

    private void printLine(String key, Double price) {
        int spacesNum = MAX_LENGTH - key.length();
        for (int i = 0; i < spacesNum; i++) {
            key = key.concat(".");
        }
        System.out.println(key + " " + price);
    }

    private void printLine() {
        String line = "";
        for (int i = 0; i < MAX_LENGTH; i++) {
            line = line.concat("_");
        }
        System.out.println(line);
    }

    @Override
    public void printTotal(Double total) {
        printLine();
        printLine("TOTAL:", total);
    }
}
