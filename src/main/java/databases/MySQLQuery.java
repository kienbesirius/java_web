package databases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Product;

public class MySQLQuery extends MySQLConnection {
	private static final String SELECT_QUERY = "SELECT * FROM " + USE_DB_TABLE + ";";
	private static final String INSERT_QUERY = "INSERT INTO " + USE_DB_TABLE
			+ "(product_id,name,quantity_in_stock,unit_price) VALUES (?,?,?,?);";
	private static final String UPDATE_QUERY = "UPDATE " + USE_DB_TABLE
			+ " SET name=?, quantity_in_stock=?, unit_price=? WHERE product_id=?;";
	private static final String DELETE_QUERY = "DELETE FROM " + USE_DB_TABLE + " WHERE product_id=?;";
	private static final String SEARCH_QUERY = "SELECT * FROM " + USE_DB_TABLE + " WHERE product_id=?;";

	public MySQLQuery() {

	}

	public static List<Product> getProducts(Connection c) {
		List<Product> list = new ArrayList<Product>();
		try {
			PreparedStatement preparedStatement = c.prepareStatement(SELECT_QUERY);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int product_id = rs.getInt("product_id");
				String name = rs.getString("name");
				int quantity_in_stock = rs.getInt("quantity_in_stock");
				double unit_price = rs.getDouble("unit_price");
				Product product = new Product(product_id, name, quantity_in_stock, unit_price);
				product.toString();
				list.add(product);
				
			}
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static Product getProduct(Connection c, int product_id) {
		Product product = null;
		try {
			PreparedStatement preparedStatement = c.prepareStatement(SEARCH_QUERY);
			preparedStatement.setInt(1, product_id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				product_id = rs.getInt("product_id");
				String name = rs.getString("name");
				int quantity_in_stock = rs.getInt("quantity_in_stock");
				double unit_price = rs.getDouble("unit_price");
				product = new Product(product_id, name, quantity_in_stock, unit_price);
			}
			preparedStatement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}

	public static String UpdateProduct(Connection c, Product p) {
		try {
			PreparedStatement preparedStatement = c.prepareStatement(UPDATE_QUERY);
			preparedStatement.setInt(4, p.getProduct_id());
			preparedStatement.setString(1, p.getName());
			preparedStatement.setInt(2, p.getQuantity_in_stock());
			preparedStatement.setDouble(3, p.getUnit_price());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			return "UPDATED";
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return "UPDATE FAILED\n" + e.getMessage();
		}
	}

	public static String InsertProduct(Connection c, Product p) {
		try {
			PreparedStatement preparedStatement = c.prepareStatement(INSERT_QUERY);
			preparedStatement.setInt(1, p.getProduct_id());
			preparedStatement.setString(2, p.getName());
			preparedStatement.setInt(3, p.getQuantity_in_stock());
			preparedStatement.setDouble(4, p.getUnit_price());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			return "INSERTED " +p.toString();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return "INSERT FAILED\n" + e.getMessage();
		}
	}

	public static String DeleteProduct(Connection c, int product_id) {
		try {
			PreparedStatement preparedStatement = c.prepareStatement(DELETE_QUERY);
			preparedStatement.setInt(1, product_id);
			preparedStatement.execute();
			preparedStatement.close();
			return "DElETED " + "PRODUCT_ID = " + product_id;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return "DELETE FAILED\n" + e.getMessage();
		}
	}
}
