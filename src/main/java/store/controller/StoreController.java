package store.controller;

import store.model.Product;
import store.model.validator.ValidatorException;
import store.repository.StoreRepository;

import java.io.IOException;
import java.util.ArrayList;

public class StoreController {
	StoreRepository repo;
	//ProductValidator pValidator;

	public StoreController(StoreRepository repo) {
		this.repo = repo;
		//this.pValidator = pValidator;
		readProducts("data.txt");
	}
	public void readProducts(String f){
		try {
			repo.readFile(f);
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
	}
	
	public void addProduct(Product p) throws ValidatorException {
			//pValidator.validate(p);
			repo.addNewProduct(p);

	}
	public ArrayList<Product> getProductsCategory(String cat){
		return repo.getProductsCategory(cat);
	}
	
	public ArrayList<Product> stockSituationProduct(String pname){
		return repo.stockSituationProduct(pname);
	}
	public ArrayList<Product> stockSituation(){
		return repo.stockSituation();
	}

	// METHODS USED FOR TESTING
	public Integer getNumberOfProducts() { return this.stockSituation().size(); }
	public void removeTestedProduct() { repo.removeTestedProd(); }
}
