package core.listener;

import static core.util.HibernateUtil.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class HibernateListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        getSessionFactory();
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        shutdown();
    }
}
