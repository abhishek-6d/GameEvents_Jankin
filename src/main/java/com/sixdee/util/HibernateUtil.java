package com.sixdee.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static Logger logger = LogManager.getLogger(HibernateUtil.class);
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
			String configFile = null;
			//if(AppCache.environment.getProperty("DB_TYPE")!=null&&AppCache.environment.getProperty("DB_TYPE").trim().equalsIgnoreCase("ORACLE"))
			if(AppCache.cacheMap.get("DB_TYPE")!=null && AppCache.cacheMap.get("DB_TYPE").equalsIgnoreCase("ORACLE"))
			{
				configFile = "hibernate.cfg.xml";
				logger.info("******************************* Oracle DB is loading ... .... ******************************* ");
				
			}
			else
			{
				configFile = "hibernate.cfg.xml";
				logger.info("*******************************  Mysql DB is loading ... .... ******************************* ");
			}
			
			logger.info("*******************************  configFile... .... ******************************* "+configFile);
		 	return new Configuration().configure(configFile).buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
