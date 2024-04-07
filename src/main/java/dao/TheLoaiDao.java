package dao;/*
 * @description: Bài tập về nhà
 * @author: 19475251-Tăng Thanh Đức
 * @date: 08/04/2024
 */


import database.ConnectDatabase;
import entities.TheLoai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.LinkedHashSet;

public class TheLoaiDao {
    static EntityManager entityManager = ConnectDatabase.getEntityManager();
    static EntityTransaction transaction = entityManager.getTransaction();

    public static TheLoai themTheLoai(TheLoai theLoai) {
        TheLoai addedTheLoai = null;

        try {
            transaction.begin();
            entityManager.persist(theLoai);
            transaction.commit();
            addedTheLoai = theLoai;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return addedTheLoai;
    }

    public static TheLoai layTheLoaiTheoMa(int maTheLoai) {
        TheLoai theLoai = null;
        try {
            transaction.begin();
            theLoai = entityManager.find(TheLoai.class, maTheLoai);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return theLoai;
    }

    public static LinkedHashSet<TheLoai> layDanhSachTheLoai() {
        LinkedHashSet<TheLoai> danhSachTheLoai = null;
        try {
            transaction.begin();
            danhSachTheLoai = new LinkedHashSet<>(entityManager.createQuery("SELECT tl FROM TheLoai tl", TheLoai.class).getResultList());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return danhSachTheLoai;
    }

    public static boolean xoaTheLoai(int maTheLoai) {
        boolean result = false;
        try {
            transaction.begin();
            TheLoai theLoai = entityManager.find(TheLoai.class, maTheLoai);
            if (theLoai != null) {
                entityManager.remove(theLoai);
                result = true;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

    public static TheLoai capNhatTheLoai(int maTheLoai, String tenTheLoai) {
        TheLoai updatedTheLoai = null;
        try {
            transaction.begin();
            String query = "UPDATE TheLoai tl SET tl.tenTheLoai = :tenTheLoai WHERE tl.maTheLoai = :maTheLoai";
            entityManager.createQuery(query)
                    .setParameter("tenTheLoai", tenTheLoai)
                    .setParameter("maTheLoai", maTheLoai)
                    .executeUpdate();
            updatedTheLoai = entityManager.find(TheLoai.class, maTheLoai);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return updatedTheLoai;
    }

    public void close() {
        ConnectDatabase.close();
    }
}