package pointofsale;

import java.util.HashMap;
import pointofsale.api.IsDAO;

/**
 *
 * Pretends to be a database. 
 * 
 * @author Piotr
 */
public class DAO implements IsDAO {

    private HashMap<Long, Product> products;

    public DAO() {
        products = new HashMap<>();
        generate();
    }

    private void addProduct(long barCode, String name, double price) {
        products.put(barCode, new Product(barCode, name, price));
    }

    private void generate() {
        addProduct(111111L, "GreatBook", 100.00);
        addProduct(222222L, "WorkBook", 10.00);
        addProduct(333333L, "Novel", 20.00);
        addProduct(444444L, "Poem", 30.00);
        addProduct(555555L, "SciFiBook", 10.00);
        addProduct(666666L, "ExcerciseBook", 10.00);

    }

    @Override
    public Product queryProducts(long barCode) throws NoProductFound {
        Product product = products.get(barCode);
        if (product == null) {
            throw new NoProductFound();
        }
        return product;
    }
}
