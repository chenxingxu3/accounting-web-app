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


/**
 * Servlet implementation class AllAccountServlet
 */
@WebServlet("/allaccounts")
public class AllAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllAccountServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
	    EntityManager em = EntityManagerFactory.create();
		
	    try {
            TypedQuery<KAccount> query = em.createQuery(
                    "SELECT t FROM KAccount t JOIN t.kuser u", KAccount.class);
            List<KAccount> kaccounts = query.getResultList();
            request.setAttribute("results", kaccounts);
            request.getRequestDispatcher("/WEB-INF/accounts.jsp").forward(request, response);
        } finally {
            em.close();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
