package store.model.validator;

import store.model.Product;

import java.util.regex.Pattern;

/**
 * Created by Alexandra on 21/05/2017.
 */
public class ProductValidator {
    public void validate(Product p) throws ValidatorException {
        if (p.getCode() < 0) throw new ValidatorException("Code must be greater than zero.");
        if (!legal(p.getName())) throw new ValidatorException("Name can only contain letters.");
        if (p.getQuantity() < 0) throw new ValidatorException("Quantity must be greater than zero.");
    }

    private boolean legal(String name) {
        return Pattern.matches("^[a-zA-Z]*$", name);
    }

}
