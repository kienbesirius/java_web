package filters;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import databases.MySQLConnection;
import databases.MySQLQuery;
import models.Product;

/**
 * Servlet Filter implementation class IndexHTMLFilter
 */
@WebFilter(urlPatterns = { "/index.html", "/SQLInventoryProductsList", "/SQLInventoryDeleteProduct",
		"/SQLInventoryInsertProduct", "/SQLInventoryUpdateProduct" })
public class IndexHTMLFilter extends HttpFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ADMINISTRATOR = "bechj";

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public IndexHTMLFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		String command = "";
		Product product = new Product();
		System.out.println("PROCESSES FILTERINNG");
		if (request.getParameter("command") != null) {
			command = request.getParameter("command");
		}
		// pass the request along the filter chain

		switch (command) {
		case "verify": {
			String administrator = request.getParameter("admin") == null || request.getParameter("admin") == ""
					? "admin access denied"
					: request.getParameter("admin");

			// Asset the condition
			if (ADMINISTRATOR.equalsIgnoreCase(administrator)) {
				String ERROR = null;
				try {
					Connection connection = MySQLConnection.getConnection();
					List<Product> products = MySQLQuery.getProducts(connection);
					request.setAttribute("products", products);
				} catch (Exception e) {
					e.printStackTrace();
					ERROR = e.getMessage();
					request.setAttribute("ERROR", ERROR);
					// TODO: handle exception
				}
				System.out.println(administrator);
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ProductList.jsp");
//				dispatcher.forward(request, response);
				chain.doFilter(request, response);
			} else {
				System.out.println(administrator);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
				dispatcher.forward(request, response);
			}
			break;
		}
		case "insert": {
			// verify data in field and Save change/
			try {
				Connection connection = MySQLConnection.getConnection();
				int product_id = Integer.parseInt(request.getParameter("product_id"));
				String name = request.getParameter("name");
				int quantity_in_stock = Integer.parseInt(request.getParameter("quantity_in_stock"));
				double unit_price = Double.parseDouble(request.getParameter("unit_price"));
				product = new Product(product_id, name, quantity_in_stock, unit_price);
				String finalResult = MySQLQuery.InsertProduct(connection, product);

				request.setAttribute("product", product);
				request.setAttribute("ERROR", finalResult);
				

			} catch (Exception e) {
				System.out.print("INSERTING ERROR\n");
				e.printStackTrace();
				request.setAttribute("ERROR", e.getMessage());
			}
			chain.doFilter(request, response);
			break;
		}
		case "insertProduct": {
			// verify data in field and Save change/
			chain.doFilter(request, response);
		}
		case "delete": {
			// get product_id and delete
			try {
				int product_id = Integer.parseInt(request.getParameter("product_id"));
				Connection connection = MySQLConnection.getConnection();
				product = MySQLQuery.getProduct(connection, product_id);
				String finalResult = MySQLQuery.DeleteProduct(connection, product_id);
				if (finalResult.contains("DElETED")) {
					request.setAttribute("ERROR", null);
					chain.doFilter(request, response);
				} else {
					request.setAttribute("ERROR", finalResult);
					request.setAttribute("product", product);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/DeleteError.jsp");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		}
		case "update": {
			// verify data in field and Save changes

			String result = "??";

			try {
				Connection connection = MySQLConnection.getConnection();
				int product_id = Integer.parseInt(request.getParameter("product_id"));
				String name = request.getParameter("name");
				int quantity_in_stock = Integer.parseInt(request.getParameter("quantity_in_stock"));
				double unit_price = Double.parseDouble(request.getParameter("unit_price"));
				product = new Product(product_id, name, quantity_in_stock, unit_price);
				String finalResult = MySQLQuery.UpdateProduct(connection, product);
				if (finalResult.contains("UPDATED")) {
					request.setAttribute("product", product);
					request.setAttribute("ERROR", finalResult);
					chain.doFilter(request, response);
				} else {
					request.setAttribute("ERROR", finalResult);
					request.setAttribute("product", product);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UpdateProduct.jsp");
					dispatcher.forward(request, response);
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result = e.getMessage();
				request.setAttribute("ERROR", result);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UpdateProduct.jsp");
				dispatcher.forward(request, response);
			}
			break;
		}
		case "updateProduct": {
			// Get data
			try {
				Connection connection = MySQLConnection.getConnection();
				int product_id = Integer.parseInt(request.getParameter("product_id"));
				product = MySQLQuery.getProduct(connection, product_id);
				request.setAttribute("product", product);
				chain.doFilter(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("PRODUCT NOT FOUND");
			}
			break;
		}
		default: {
			String administrator = request.getParameter("admin") == null || request.getParameter("admin") == ""
					? "admin access denied"
					: request.getParameter("admin");

			// Asset the condition
			if (ADMINISTRATOR.equalsIgnoreCase(administrator)) {
				String ERROR = null;
				try {
					Connection connection = MySQLConnection.getConnection();
					List<Product> products = MySQLQuery.getProducts(connection);
					request.setAttribute("products", products);
				} catch (Exception e) {
					e.printStackTrace();
					ERROR = e.getMessage();
					request.setAttribute("ERROR", ERROR);
					// TODO: handle exception
				}
				System.out.println(administrator);
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ProductList.jsp");
//				dispatcher.forward(request, response);
				chain.doFilter(request, response);
			} else {
				System.out.println(administrator);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
				dispatcher.forward(request, response);
			}
		}
		}
//		chain.doFilter(request, response);
		System.out.println("END FILTERING");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
