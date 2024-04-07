/*
 * @ (#) main.java   1.0     01/04/2024
 *
 * Copyright (c) 2024 IUH All rights reserved
 */
/*
 *@description:
 *@author: Nguyen Tien Dat 19473791
 *@date:   01/04/2024
 *version: 1.0
 */

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.*;

public class main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA ORM MSSQL");

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Create an Instructor object

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}

