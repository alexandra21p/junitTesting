package store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import store.model.Product;
import store.model.validator.ProductValidator;
import store.model.validator.ValidatorException;
import store.repository.StoreRepository;

import java.util.List;

/**
 * Created by Alexandra on 21/05/2017.
 */

@Controller
public class WebController {
    private StoreController storeCtrl;

    public WebController() {
        ProductValidator validator = new ProductValidator();
        StoreRepository storeRepo = new StoreRepository(validator);
        storeCtrl = new StoreController(storeRepo);

        System.out.println("nr of products from repo: " + storeCtrl.getNumberOfProducts());
        List<Product> list = storeCtrl.stockSituation();
        for (Product p : list) {
            System.out.println(p.toString());
        }
    }

    @RequestMapping(value = "/initial", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute(name = "product")Product product, Model model) {
        String message = null;

        try {
            List<Product> list = storeCtrl.stockSituation();
            for (Product p : list) {
                System.out.println(p.toString());
            }
            storeCtrl.addProduct(product);
            message = "Product was successfully added!";
        } catch (ValidatorException e) {
            message = e.getMessage();
            System.out.println(e.getMessage());
        }

        model.addAttribute("message", message);

        return "home";
    }
}
