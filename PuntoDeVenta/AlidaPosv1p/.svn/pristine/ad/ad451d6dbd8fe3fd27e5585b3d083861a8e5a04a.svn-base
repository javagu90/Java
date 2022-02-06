package com.alidasoftware.pos.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

	private static final EntityManagerFactory emf;
    
    static {
        try {
            emf = Persistence.createEntityManagerFactory("AlidaPosPU");
        } catch (Throwable t) {
            throw new ExceptionInInitializerError();
        }
    }
    
    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
    
}
