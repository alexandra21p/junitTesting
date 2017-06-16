package store.model;

public class Product {
	private int code;
	private String name;
	private String category;
	private int quantity;

	public Product(int code, String name, String category, int quantity) {
		this.code = code;
		this.name = name;
		this.category = category;
		this.quantity = quantity;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [code = " + code + ", name = " + name + ", category = "
				+ category + ", quantity = " + quantity + "]";
	}
}

