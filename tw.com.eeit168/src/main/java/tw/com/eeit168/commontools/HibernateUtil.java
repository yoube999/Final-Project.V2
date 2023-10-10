package tw.com.eeit168.commontools;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;

    static {
        try {
            // 創建一個 Configuration 對象，並配置 Hibernate
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml"); // 加載 Hibernate 配置文件

            // 根據配置創建 SessionFactory
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
