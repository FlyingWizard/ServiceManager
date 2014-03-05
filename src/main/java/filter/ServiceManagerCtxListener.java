package filter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.service.PersistanceManager;

@WebListener
public class ServiceManagerCtxListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//Clean the JPA resource when application is stopped.
		PersistanceManager.cleanResources();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	}
		
}
