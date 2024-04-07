/*
 * @ (#) KhachHangDao.java   1.0     03/04/2024
 *
 * Copyright (c) 2024 IUH All rights reserved
 */
package dao;
/*
 *@description:
 *@author: Nguyen Tien Dat 19473791
 *@date:   03/04/2024
 *version: 1.0
 */

import database.ConnectDatabase;
import entities.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.sql.Date;
import java.util.LinkedHashSet;

public class KhachHangDao {
    static EntityManager entityManager = ConnectDatabase.getEntityManager();
    static EntityTransaction transaction = entityManager.getTransaction();

    public static KhachHang themKhachHang(KhachHang khachHang) {
        KhachHang addedKhachHang = null;

        try {
            transaction.begin();
            entityManager.persist(khachHang);
            transaction.commit();
            addedKhachHang = khachHang;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return addedKhachHang;
    }

    public static KhachHang layKhachHangTheoMa(int maKH) {
        KhachHang khachHang = null;
        try {
            transaction.begin();
            khachHang = entityManager.find(KhachHang.class, maKH);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return khachHang;
    }

    public static LinkedHashSet<KhachHang> layDanhSachKhachHang() {
        LinkedHashSet<KhachHang> danhSachKhachHang = null;
        try {
            transaction.begin();
            danhSachKhachHang = new LinkedHashSet<>(entityManager.createQuery("SELECT kh FROM KhachHang kh", KhachHang.class).getResultList());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return danhSachKhachHang;
    }

    public static LinkedHashSet<KhachHang> layKhachHangTheoTen(String ten) {
        LinkedHashSet<KhachHang> danhSachKhachHang = null;
        try {
            transaction.begin();
            danhSachKhachHang = new LinkedHashSet<>(entityManager.createQuery("SELECT kh FROM KhachHang kh WHERE kh.tenKH = :ten", KhachHang.class)
                    .setParameter("ten", ten).getResultList());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return danhSachKhachHang;
    }

    public static KhachHang layKhachHangTheoSDT(String soDienThoai) {
        KhachHang khachHang = null;
        try {
            transaction.begin();
            khachHang = entityManager.createQuery("SELECT kh FROM KhachHang kh WHERE kh.soDienThoai = :soDienThoai", KhachHang.class)
                    .setParameter("soDienThoai", soDienThoai).getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return khachHang;
    }

    public static KhachHang layKhachHangTheoCMND(String soCMND) {
        KhachHang khachHang = null;
        try {
            transaction.begin();
            khachHang = entityManager.createQuery("SELECT kh FROM KhachHang kh WHERE kh.soCMND = :soCMND", KhachHang.class)
                    .setParameter("soCMND", soCMND).getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return khachHang;
    }

    public static boolean xoaKhachHang(int maKH) {
        boolean result = false;
        try {
            transaction.begin();
            KhachHang khachHang = entityManager.find(KhachHang.class, maKH);
            if (khachHang != null) {
                entityManager.remove(khachHang);
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

    public static KhachHang capNhatKhachHang(int maKH, String hoKH, String tenKH, Date ngaySinh, String gioiTinh, String soCMND,
                                             String soDienThoai, String diaChi) {
        KhachHang updatedKhachHang = null;
        try {
            transaction.begin();
            String query = "UPDATE KhachHang kh SET kh.hoKH = :hoKH, kh.tenKH = :tenKH, kh.ngaySinh = :ngaySinh, kh.gioiTinh = :gioiTinh, kh.soCMND = :soCMND, kh.soDienThoai = :soDienThoai, kh.diaChi = :diaChi WHERE kh.maKH = :maKH";
            entityManager.createQuery(query)
                    .setParameter("hoKH", hoKH)
                    .setParameter("tenKH", tenKH)
                    .setParameter("ngaySinh", ngaySinh)
                    .setParameter("gioiTinh", gioiTinh)
                    .setParameter("soCMND", soCMND)
                    .setParameter("soDienThoai", soDienThoai)
                    .setParameter("diaChi", diaChi)
                    .setParameter("maKH", maKH)
                    .executeUpdate();
            updatedKhachHang = entityManager.find(KhachHang.class, maKH);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return updatedKhachHang;
    }


    public void close() {
        ConnectDatabase.close();
    }
}
