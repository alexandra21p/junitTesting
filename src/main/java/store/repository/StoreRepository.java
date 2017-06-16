package store.repository;

import store.model.Product;
import store.model.validator.ProductValidator;
import store.model.validator.ValidatorException;

import java.io.*;
import java.util.ArrayList;

public class StoreRepository {
	private ArrayList<Product> allProducts;
	private ProductValidator validator;
	private final static int PRODUCT_TEST_CODE = 100;


	public StoreRepository(ProductValidator validator) {
		this.validator = validator;
		this.allProducts = new ArrayList<>();
	}

	public void readFile(String fname) throws NumberFormatException, IOException{
		FileInputStream f = new FileInputStream(fname);
		DataInputStream in = new DataInputStream(f);
		BufferedReader buf = new BufferedReader(new InputStreamReader(in));
		String rd;
		String []line;
		while((rd = buf.readLine()) != null){
            if (rd.isEmpty())
                continue; // skip empty lines
				line = rd.split(",");
				this.allProducts.add(new Product(Integer.parseInt(line[0]),line[1],line[2],Integer.parseInt(line[3])));
		}
		in.close();
	}
	public void addNewProduct(Product p) throws ValidatorException {
		System.out.println("begin: add product");
		//if (p.getCode() > 0 && p.getQuantity() >= 0 && p.getCode() < Integer.MAX_VALUE && p.getQuantity() < Integer.MAX_VALUE&& !illegal(p.getName())){
		validator.validate(p);

		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter("data.txt", true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int valid = 1;
		for (Product i : allProducts) {
			if (i.getCode() == p.getCode()) {
				valid = 0;
			}
		}
		if (valid == 1) {
			try {
				allProducts.add(p);
				out.write(p.getCode() + "," + p.getName() + "," + p.getCategory() + "," + p.getQuantity());
				out.close();
				System.out.println("Product added.\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
				throw new ValidatorException("This code already exists.\n");
			}

	}

	public ArrayList<Product> getProductsCategory(String cat){
		ArrayList<Product> cProducts = new ArrayList<Product>();
		for(Product p : allProducts){
			if(p.getCategory().compareTo(cat) == 0){
				cProducts.add(p);
			}
		}
		return cProducts;
	}
	
	public ArrayList<Product> stockSituationProduct(String pname){
		ArrayList<Product> prods = new ArrayList<Product>();
		for(Product p : allProducts)
			if(p.getName().compareTo(pname) == 0)
				prods.add(p);
		return prods;
	}
	public ArrayList<Product> stockSituation() {
		return allProducts;
	}

	public void removeTestedProd() {
		if (allProducts.size() > 0) {
			//System.out.println("initial: " + allProducts.size());
			int lastProdPos = allProducts.size() - 1;
			Product p = allProducts.get(lastProdPos);
			if (p.getCode() == PRODUCT_TEST_CODE) {
				allProducts.remove(lastProdPos);

				// make sure to also delete from file
				BufferedWriter out = null;
				try {
					out = new BufferedWriter(new FileWriter("data.txt", false));
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					for (Product pr : allProducts) {
						out.write(pr.getCode() + "," + pr.getName() + "," + pr.getCategory() + "," + pr.getQuantity());
						out.newLine();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//System.out.println("after deleting: " + allProducts.size());
		}

	}


}
