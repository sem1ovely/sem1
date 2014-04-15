package listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dao.MysqlCourseDao;
import util.DBConnectionPool;

public class ContextLoaderListener implements ServletContextListener {
	DBConnectionPool dbConnectionPool;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		dbConnectionPool.closeAll();

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("호출되었느냐?");
		ServletContext sc = event.getServletContext();		
		dbConnectionPool = new DBConnectionPool();	  
	  dbConnectionPool.setDRIVER(sc.getInitParameter("driver"));
	  dbConnectionPool.setURL(sc.getInitParameter("url"));
	  dbConnectionPool.setUSERNAME(sc.getInitParameter("username"));
	  dbConnectionPool.setPASSWORD(sc.getInitParameter("password"));	 
	  
		MysqlCourseDao courseDao = new MysqlCourseDao();
		courseDao.setDBConnectionPool(dbConnectionPool);		
		
		sc.setAttribute("courseDao", courseDao);
	}

}
