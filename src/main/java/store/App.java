package store;

import store.controller.StoreController;
import store.model.validator.ProductValidator;
import store.repository.StoreRepository;
import store.ui.StoreUI;

public class App {


	public static void main(String[] args)  {

		ProductValidator validator = new ProductValidator();
		StoreRepository repo = new StoreRepository(validator);
		StoreController c = new StoreController(repo);
		StoreUI u = new StoreUI(c);
		u.run();

	}

}
