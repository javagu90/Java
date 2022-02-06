package com.alidasoftware.pos.util;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

	private static final EntityManagerFactory emf;
    
    static {
        try {
        	/*
        	Map<String, String> persistenceMap = new HashMap<String, String>();
            persistenceMap.put("javax.persistence.jdbc.url", "<url>");
            persistenceMap.put("javax.persistence.jdbc.user", "<username>");
            persistenceMap.put("javax.persistence.jdbc.password", "<password>");
            persistenceMap.put("javax.persistence.jdbc.driver", "<driver>");
            emf = Persistence.createEntityManagerFactory("AlidaPosPU", persistenceMap);
            */
            
            emf = Persistence.createEntityManagerFactory("AlidaPosPU");
        } catch (Throwable t) {
            throw new ExceptionInInitializerError();
        }
    }
    
    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
    
    public static void closeEntityManager() {
    	emf.close();
    }
    
}
