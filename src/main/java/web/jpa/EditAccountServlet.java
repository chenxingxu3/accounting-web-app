package web.jpa;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EditAccountServlet
 */
@WebServlet("/editaccount")
public class EditAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAccountServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession(false);
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        
        if (session == null || session.getAttribute("kuser") == null) {
            response.sendRedirect("userlogin");
            return;
        }
        
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            int accountId = Integer.parseInt(idStr);
            EntityManager em = EntityManagerFactory.create();
            KAccount account = em.find(KAccount.class, accountId);
            
            if (account != null) {
                request.setAttribute("account", account);
                request.getRequestDispatcher("/WEB-INF/editaccount.jsp").forward(request, response);
            } else {
                response.sendRedirect("accounts");
            }
            em.close();
        } else {
            response.sendRedirect("accounts");
        }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
		
	    String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            int accountId = Integer.parseInt(idStr);
            EntityManager em = EntityManagerFactory.create();
            
            try {
                em.getTransaction().begin();
                KAccount account = em.find(KAccount.class, accountId);

                if (account != null) {
                    // Update account details from request parameters
                    account.setName(request.getParameter("aname"));
                    account.setType(request.getParameter("type"));
                    account.setMode(request.getParameter("mode"));
                    account.setMoney(new BigDecimal(request.getParameter("money")));
                    account.setRemark(request.getParameter("remark"));

                    // Parse and set the date
                    try {
                        Timestamp date = new Timestamp(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(request.getParameter("date")).getTime());
                        account.setDate(date);
                    } catch (ParseException e) {
                        // Handle date parsing error
                    }
                    
                    em.persist(account);
                    em.getTransaction().commit();
                }
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                throw new ServletException(e);
            } finally {
                em.close();
            }
            
            response.sendRedirect("accounts");
        } else {
            response.sendRedirect("accounts");
        }
	}

}
