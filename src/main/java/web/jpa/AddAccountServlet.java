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
 * Servlet implementation class AddAccountServlet
 */
@WebServlet("/addaccount")
public class AddAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAccountServlet() {
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
            // ユーザーがログインしていない
            response.sendRedirect("userlogin");
        } else {
            // ユーザーはログインしています
            String aname = request.getParameter("aname");
            String type = request.getParameter("type");
            String mode = request.getParameter("mode");
            String moneyStr = request.getParameter("money");
            String dateStr = request.getParameter("date");
            String remark = request.getParameter("remark");

            // 日付と時刻を解析してみる
            Timestamp date = null;
            try {
                date = new Timestamp(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(dateStr).getTime());
            } catch (ParseException e) {
                // 日付形式エラー
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("日付形式が無効です。 「yyyy/MM/dd HH:mm:ss」形式を使用してください。");
                return;
            }

            BigDecimal money = new BigDecimal(moneyStr);

            // 新しいAccountを追加
            KUser kuser = (KUser) session.getAttribute("kuser");
            EntityManager em = EntityManagerFactory.create();
            try {
                em.getTransaction().begin();
                KAccount newAccount = new KAccount();
                
                newAccount.setKuser(kuser);
                newAccount.setName(aname);
                newAccount.setType(type);
                newAccount.setMode(mode);
                newAccount.setMoney(money);
                newAccount.setDate(date);
                newAccount.setRemark(remark);
                
                em.persist(newAccount);
                em.getTransaction().commit();
            } finally {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                em.close();
            }

            // Account にリダイレクト
            response.sendRedirect("accounts");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
