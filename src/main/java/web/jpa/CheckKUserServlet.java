package web.jpa;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class CheckKUserServlet
 */
@WebServlet("/checkuser")
public class CheckKUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckKUserServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String name = request.getParameter("name");
	    boolean userExists = false;
	    
	    if (name != null && !name.trim().isEmpty()) {
            EntityManager em = EntityManagerFactory.create();
            try {
                TypedQuery<KUser> query = em.createQuery(
                        "SELECT u FROM KUser u WHERE u.name = :name", KUser.class);
                query.setParameter("name", name);
                List<KUser> users = query.getResultList();

                userExists = !users.isEmpty();
            } finally {
                em.close();
            }
        }
	    
	    JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("exist", userExists);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
