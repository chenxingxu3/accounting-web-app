package web.jpa;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/userregister")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.getRequestDispatcher("/WEB-INF/userregister.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      request.setCharacterEncoding("utf-8");
	        	        	        
	        String email = request.getParameter("email");
	        String name = request.getParameter("name");
	        String password = request.getParameter("password");
	        String epassword = new BCryptPasswordEncoder().encode(password);
	        String sex = request.getParameter("sex");
	        
	        // ユーザー名が空かどうかを確認する
	        if (name == null || name.trim().isEmpty()) {
	            response.sendError(HttpServletResponse.SC_FORBIDDEN, "ユーザー名を空にすることはできません");
	            return;
	        }
	                	        
	        EntityManager em = EntityManagerFactory.create();
	                	        
	        try {
	            // ユーザー名がデータベースにすでに存在するかどうかを確認します
	            TypedQuery<KUser> query = em.createQuery("SELECT u FROM KUser u WHERE u.name = :name", KUser.class);
	            query.setParameter("name", name);
	            if (!query.getResultList().isEmpty()) {
	                response.sendError(HttpServletResponse.SC_FORBIDDEN, "ユーザー名は既に存在します");
	                return;
	            }
	            
                em.getTransaction().begin();

                KUser newKUser = new KUser();
                newKUser.setEmail(email);
                newKUser.setName(name);
                newKUser.setPassword(epassword);
                newKUser.setSex(sex);
                em.persist(newKUser);
                em.getTransaction().commit();
            } finally {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                em.close();
            }
	        
	        response.sendRedirect("userlogin");
	}

}
