package dao;/*
 * @description:
 * @author: 19475251-Tăng Thanh Đức
 * @date: 08/04/2024
 */


import database.ConnectDatabase;
import entities.NhaCungCap;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.LinkedHashSet;

public class NhaCungCapDao {
    static EntityManager entityManager = ConnectDatabase.getEntityManager();
    static EntityTransaction transaction = entityManager.getTransaction();

    public static NhaCungCap themNhaCungCap(NhaCungCap nhaCungCap) {
        NhaCungCap addedNhaCungCap = null;

        try {
            transaction.begin();
            entityManager.persist(nhaCungCap);
            transaction.commit();
            addedNhaCungCap = nhaCungCap;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return addedNhaCungCap;
    }

    public static NhaCungCap layNhaCungCapTheoMa(int maNCC) {
        NhaCungCap nhaCungCap = null;
        try {
            transaction.begin();
            nhaCungCap = entityManager.find(NhaCungCap.class, maNCC);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return nhaCungCap;
    }

    public static LinkedHashSet<NhaCungCap> layDanhSachNhaCungCap() {
        LinkedHashSet<NhaCungCap> danhSachNhaCungCap = null;
        try {
            transaction.begin();
            danhSachNhaCungCap = new LinkedHashSet<>(entityManager.createQuery("SELECT ncc FROM NhaCungCap ncc", NhaCungCap.class).getResultList());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return danhSachNhaCungCap;
    }

    public static boolean xoaNhaCungCap(int maNCC) {
        boolean result = false;
        try {
            transaction.begin();
            NhaCungCap nhaCungCap = entityManager.find(NhaCungCap.class, maNCC);
            if (nhaCungCap != null) {
                entityManager.remove(nhaCungCap);
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

    public static NhaCungCap capNhatNhaCungCap(int maNCC, String tenNCC) {
        NhaCungCap updatedNhaCungCap = null;
        try {
            transaction.begin();
            String query = "UPDATE NhaCungCap ncc SET ncc.tenNCC = :tenNCC WHERE ncc.maNCC = :maNCC";
            entityManager.createQuery(query)
                    .setParameter("tenNCC", tenNCC)
                    .setParameter("maNCC", maNCC)
                    .executeUpdate();
            updatedNhaCungCap = entityManager.find(NhaCungCap.class, maNCC);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return updatedNhaCungCap;
    }

    public void close() {
        ConnectDatabase.close();
    }
}