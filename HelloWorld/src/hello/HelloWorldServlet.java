package hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
//import org.persistence.Person;
import org.persistence.Product;

//import com.sap.security.core.server.csi.XSSEncoder;

// Custom comparator class
class CustomComparator implements Comparator<Product>
{
	@Override
	public int compare(Product p1, Product p2)
	{
		Integer a = new Integer(p1.getId());
		Integer b = new Integer(p2.getId());
		
		return (a.compareTo(b));
	}
}

// Servlet Class
public class HelloWorldServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static DataSource ds; 
	private static EntityManagerFactory emf;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		EntityManager em = emf.createEntityManager();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write("<html><head></head><body>");

		String uri = request.getRequestURI();

		if (uri.endsWith("/Add")) {
			doAdd(request, out, em);
		}
		else if(uri.endsWith("/Remove"))
		{
			doRemove(request, out, em);
		}

		// Product
		List<Product> resultList = em.createNamedQuery("AllProducts", Product.class).getResultList();
		
		Collections.sort(resultList, new CustomComparator());
		
		request.setAttribute("products", resultList);
		request.getRequestDispatcher("/products.jsp").forward(request, response); 
		
		em.close();
	}

	protected void doAdd(HttpServletRequest request, PrintWriter out,
			EntityManager em) throws ServletException, IOException {
		
		Product product = new Product();
		product.setId(Integer.parseInt(request.getParameter("pID")));
		product.setPName(request.getParameter("pName"));
		product.setPDescription(request.getParameter("pDescription"));
		product.setPPrice(request.getParameter("pPrice"));
		product.setPQuantity(request.getParameter("pQuantity"));
		
		em.getTransaction().begin();
		em.persist(product);
		em.getTransaction().commit();
	}
	
	protected void doRemove(HttpServletRequest request, PrintWriter out,
			EntityManager em) throws ServletException, IOException {
		
		int pKey = Integer.parseInt(request.getParameter("delID"));
		em.getTransaction().begin();  
		
		Product product = (Product)em.find(Product.class, pKey);
		em.remove(product);
		
		em.getTransaction().commit();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	public void init() throws ServletException {
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");
		} catch (NamingException e) {
			throw new ServletException(e);
		}

		Map properties = new HashMap();
		properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, ds);
		properties.put("eclipselink.target-database", "com.sap.persistence.platform.database.HDBPlatform"); // add HANA property to EMF
		emf = Persistence.createEntityManagerFactory("JPAModel", properties);
	}
}
