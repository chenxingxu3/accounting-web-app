package web.jpa;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        EntityManagerFactory.destroy();
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        EntityManagerFactory.initialize("jpa");
        EntityManager em = EntityManagerFactory.create();
        try {
            Number c = (Number) em.createQuery("select count(u) from KUser u").getSingleResult();
            if (c.intValue() == 0) { // ユーザが0件ならデータ⼊れておく
                addTestUser(em);
            }
        } finally {
            em.close();
        }
    }
    
    private void addTestUser(EntityManager em) {
        em.getTransaction().begin();
        KUser u = new KUser("user000id", new BCryptPasswordEncoder().encode("user000pass"), "男","030qeq5n@linshiyouxiang.net");
        em.persist(u);
        BigDecimal money= new BigDecimal(Double.toString(3000.00));
        KAccount kaccount = new KAccount("収入", "所得", "現金", money, new Timestamp(new Date().getTime()),"今日の給料", u);
        em.persist(kaccount);

        u.getKaccounts().add(kaccount);
        em.getTransaction().commit();
    }

}
