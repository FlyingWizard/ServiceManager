package filter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.service.PersistenceManager;

@WebListener
public class ServiceManagerCtxListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//Clean the JPA resource when application is stopped.
		PersistenceManager.cleanResources();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	}
		
}
