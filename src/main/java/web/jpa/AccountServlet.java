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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/accounts")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession(false);
	    request.setCharacterEncoding("utf-8");
	    
	    if (session == null || session.getAttribute("kuser") == null) {
            // ユーザーはログインしていません
            response.sendRedirect("userlogin");
        } else {
         // ユーザーがログインし、Accountリストが表示されます。
            KUser kuser = (KUser) session.getAttribute("kuser");
            EntityManager em = EntityManagerFactory.create();
            try {
                TypedQuery<KAccount> query = em.createQuery("SELECT t FROM KAccount t WHERE t.kuser.id = :userId ORDER BY t.date", KAccount.class);
                query.setParameter("userId", kuser.getId());
                List<KAccount> kaccounts = query.getResultList();
                
                request.setAttribute("results", kaccounts);
                
                // KAccountリストページへ進む
                request.getRequestDispatcher("/WEB-INF/accounts.jsp").forward(request, response);
            } finally {
                em.close();
            }
        }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
