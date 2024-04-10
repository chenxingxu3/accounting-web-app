package web.jpa;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/userlogin")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
        
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
	    
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("kuser") == null) {
            // ユーザーはログインしていません
            request.getRequestDispatcher("/WEB-INF/userlogin.jsp").forward(request, response);
        } else {
            response.sendRedirect("accounts");
        }
	    // request.getRequestDispatcher("/WEB-INF/userlogin.jsp").forward(request, response);
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
        EntityManager em = EntityManagerFactory.create();
	    
	    String name = request.getParameter("name");
        String password = request.getParameter("password");
        
        try {
            String encode = new BCryptPasswordEncoder().encode(password);
            System.out.println(encode);
            

            TypedQuery<KUser> query = em.createQuery("SELECT u FROM KUser u WHERE u.name = :uname", KUser.class);
            query.setParameter("uname", name);

            KUser kuser = query.getSingleResult();
            
            boolean matches = new BCryptPasswordEncoder().matches(password, kuser.getPassword());
            
            if(matches) {
             // ログイン成功
                HttpSession session = request.getSession(true);
                session.setAttribute("kuser", kuser);

                response.sendRedirect("accounts");
            } else {
             // ログイン失敗の場合は403を返す
                response.sendError(403, "ログインに失敗しました。");
            }
            
        } catch (NoResultException e) {
            // ログインに失敗しました
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "ログインに失敗しました。");
        } finally {
            em.close();
        }
	}

}
