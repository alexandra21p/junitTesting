package store;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import store.controller.StoreController;
import store.model.Product;
import store.model.validator.ProductValidator;
import store.model.validator.ValidatorException;
import store.repository.StoreRepository;

import java.util.List;

@SuppressWarnings("Duplicates")

/**
 * Created by Alexandra on 22/05/2017.
 */
public class BigBangIntegrationTest {
    private ProductValidator validator;
    private StoreRepository storeRepo;
    private StoreController storeCtrl;

    private static final int VALID_CODE = 100;
    private static final String VALID_NAME = "product";
    private static final int VALID_QUANTITY = 35;
    private static final String INEXISTING_NAME = "newProduct";
    private static final String INEXISTING_CATEGORY= "weirdCategory";

    private static Integer initialNrOfProducts;

    @Before
    public void setUp() throws Exception {
        validator = new ProductValidator();
        storeRepo = new StoreRepository(validator);
        storeCtrl = new StoreController(storeRepo);

        initialNrOfProducts = storeCtrl.getNumberOfProducts();
    }

    @After
    public void tearDown() throws Exception {
        storeCtrl.removeTestedProduct();
        validator = null;
        storeRepo = null;
        storeCtrl = null;
        initialNrOfProducts = null;
    }


    private void assertProductAddition() {
        Integer currentNrOfProducts = storeCtrl.getNumberOfProducts();
        Assert.assertTrue(currentNrOfProducts == initialNrOfProducts + 1);
    }

    // requirement a
    @Test
    public void testValidProductAddition() throws ValidatorException {
        Product p = new Product(VALID_CODE, VALID_NAME, "category", VALID_QUANTITY);
        storeCtrl.addProduct(p);
        assertProductAddition();
    }

    // requirement b
    @Test
    public void testListProductsFromCategory() {
        List<Product> prods = storeCtrl.getProductsCategory(INEXISTING_CATEGORY);
        Assert.assertTrue(prods.size() == 0);
    }

    // requirement c
    @Test
    public void testStockSituationProduct() {
        List<Product> prods = storeCtrl.stockSituationProduct(INEXISTING_NAME);
        Assert.assertTrue(prods.size() == 0);
    }

    // all 3 requirements
    @Test
    public void integrationTest() throws ValidatorException {
        Product p = new Product(VALID_CODE, VALID_NAME, "category", VALID_QUANTITY);
        storeCtrl.addProduct(p);
        List<Product> prodsCateg = storeCtrl.getProductsCategory("category");
        List<Product> prods = storeCtrl.stockSituationProduct(VALID_NAME);

        assertProductAddition();
        Assert.assertTrue(prodsCateg.size() == 1);
        Assert.assertTrue(prods.size() == 1);
    }
}
