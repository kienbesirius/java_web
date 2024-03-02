package models;

public class Product {
	private int product_id;
	private String name;
	private int quantity_in_stock;
	private double unit_price;
	
	public Product() {
		
	}
	
	public Product(int product_id, String name, int quantity_in_stock, double unit_price) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.quantity_in_stock = quantity_in_stock;
		this.unit_price = unit_price;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity_in_stock() {
		return quantity_in_stock;
	}

	public void setQuantity_in_stock(int quantity_in_stock) {
		this.quantity_in_stock = quantity_in_stock;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	@Override
	public String toString() {
		String product = "Product [product_id=" + product_id + ", name=" + name + ", quantity_in_stock=" + quantity_in_stock
				+ ", unit_prices=" + unit_price + "]";
		System.out.println(product);
		return product;
	}
	
	
}
