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

@SuppressWarnings("Duplicates")
/**
 * Created by Alexandra on 21/05/2017.
 */
public class AddProductTest {
    private ProductValidator validator;
    private StoreRepository storeRepo;
    private StoreController storeCtrl;

    private static final int VALID_CODE = 100;
    private static final int INVALID_CODE = -1;
    private static final int DUPLICATED_CODE = 1;
    private static final String VALID_NAME = "product";
    private static final String INVALID_NAME = "prod1@";
    private static final int VALID_QUANTITY = 35;
    private static final int INVALID_QUANTITY = -10;

    private static Integer initialNrOfProducts;

    @Before
    public void setUp() throws Exception {
        validator = new ProductValidator();
        storeRepo = new StoreRepository(validator);
        storeCtrl = new StoreController(storeRepo);

        initialNrOfProducts = storeCtrl.getNumberOfProducts();
        System.out.println(initialNrOfProducts);
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

    @Test
    public void testValidId() throws ValidatorException {
        Product p = new Product(VALID_CODE, VALID_NAME, "category", VALID_QUANTITY);
        storeCtrl.addProduct(p);
        assertProductAddition();
    }

    @Test(expected = ValidatorException.class)
    public void testInvalidId() throws ValidatorException {
        Product p = new Product(INVALID_CODE, VALID_NAME, "category", VALID_QUANTITY);
        storeCtrl.addProduct(p);
    }

    @Test(expected = ValidatorException.class)
    public void testDuplicatedCode() throws ValidatorException {
        Product p = new Product(DUPLICATED_CODE, VALID_NAME, "category", VALID_QUANTITY);
        storeCtrl.addProduct(p);
    }

    @Test
    public void testValidName() throws ValidatorException {
        Product p = new Product(VALID_CODE, VALID_NAME, "category", VALID_QUANTITY);
        storeCtrl.addProduct(p);
        assertProductAddition();
    }

    @Test(expected = ValidatorException.class)
    public void testInvalidName() throws ValidatorException {
        Product p = new Product(VALID_CODE, INVALID_NAME, "category", VALID_QUANTITY);
        storeCtrl.addProduct(p);
    }

    @Test
    public void testValidQuantity() throws ValidatorException {
        Product p = new Product(VALID_CODE, VALID_NAME, "category", VALID_QUANTITY);
        storeCtrl.addProduct(p);
        assertProductAddition();
    }

    @Test(expected = ValidatorException.class)
    public void testInvalidQuantity() throws ValidatorException {
        Product p = new Product(VALID_CODE, VALID_NAME, "category", INVALID_QUANTITY);
        storeCtrl.addProduct(p);

    }
}
