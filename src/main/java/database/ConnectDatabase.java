/*
 * @ (#) ConnectDatabase.java   1.0     02/04/2024
 *
 * Copyright (c) 2024 IUH All rights reserved
 */
package database;
/*
 *@description:
 *@author: Nguyen Tien Dat 19473791
 *@date:   02/04/2024
 *version: 1.0
 */

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
 /*   private static final EntityManagerFactory entityManagerFactory;
    @Getter
    private static final EntityManager entityManager;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("JPA ORM MSSQL");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static void close() {
        entityManager.close();
        entityManagerFactory.close();
    }*/
    private static ConnectDatabase instance = new ConnectDatabase();
    private static final String PERSISTENCE_UNIT_NAME = "JPA ORM MSSQL";
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private ConnectDatabase() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static ConnectDatabase getInstance() {
        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void closeEntityManager() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }

    public void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
    /*private static final String PERSISTENCE_UNIT_NAME = "TênPersistenceUnit";
    private static EntityManagerFactory factory;

    private static ConnectDatabase instance;

    private ConnectDatabase() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static synchronized ConnectDatabase getInstance() {
        if (instance == null) {
            instance = new ConnectDatabase();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public void close() {
        if (factory != null) {
            factory.close();
        }
    }*/
}
