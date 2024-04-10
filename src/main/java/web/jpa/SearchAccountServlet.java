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
 * Servlet implementation class SearchAccountServlet
 */
@WebServlet("/searchaccount")
public class SearchAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAccountServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession(false);
	    
	    request.setCharacterEncoding("utf-8");
	    
	 // ユーザーがログインしているかどうかを確認する
        if (session == null || session.getAttribute("kuser") == null) {
            // ユーザーはログインしていません
            response.sendRedirect("userlogin");
        } else {
            // ユーザーはログインしています
            String remark = request.getParameter("remark");
            
            // remarkをリクエスト属性に保存する
            request.setAttribute("searchRemark", remark);
            
            // 現在ログインしているユーザーを取得する
            KUser kuser = (KUser) session.getAttribute("kuser");
            EntityManager em = EntityManagerFactory.create();
            try {
                TypedQuery<KAccount> query = em.createQuery(
                        "SELECT t FROM KAccount t WHERE t.kuser.id = :userId AND t.remark LIKE :remark ORDER BY t.date", 
                        KAccount.class);
                query.setParameter("userId", kuser.getId());
                query.setParameter("remark", "%" + remark + "%");
                List<KAccount> kaccounts = query.getResultList();
                
                // 検索結果をリクエスト属性に保存する
                request.setAttribute("results", kaccounts);
                
                // accounts.jsp ページに転送する
                request.getRequestDispatcher("/WEB-INF/accounts.jsp").forward(request, response);
            } finally {
                em.close();
            }
        }
		
	}

}
