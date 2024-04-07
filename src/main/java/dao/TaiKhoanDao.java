/*
 * @ (#) TaiKhoanDAO.java   1.0     02/04/2024
 *
 * Copyright (c) 2024 IUH All rights reserved
 */
package dao;
/*
 *@description:
 *@author: Nguyen Tien Dat 19473791
 *@date:   02/04/2024
 *version: 1.0
 */

import database.ConnectDatabase;
import entities.TaiKhoan;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class TaiKhoanDao {
    static EntityManager entityManager = ConnectDatabase.getEntityManager();
    static EntityTransaction transaction = entityManager.getTransaction();

    public static TaiKhoan themTaiKhoan(TaiKhoan taiKhoan) {
        try {
            transaction.begin();
            entityManager.persist(taiKhoan);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return taiKhoan;
    }

    public static LinkedHashSet<TaiKhoan> layDanhsachTaiKhoan() {
        LinkedHashSet<TaiKhoan> taiKhoans = new LinkedHashSet<>();
        try {
            transaction.begin();
            taiKhoans = new LinkedHashSet<TaiKhoan>(entityManager.createQuery("SELECT tk FROM TaiKhoan tk", TaiKhoan.class).getResultList());
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
        }
        return taiKhoans;
    }

    public static TaiKhoan layTaiKhoanTheoTenTaiKhoan(int tenTaiKhoan) {
        TaiKhoan taiKhoan = null;
        try {
            transaction.begin();
            taiKhoan = entityManager.find(TaiKhoan.class, tenTaiKhoan);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return taiKhoan;
    }

    public static Map<Integer, Object> layTenNhanVienTheoTenTaiKhoan(int tenTaiKhoan) {
        Map<Integer, Object> nhanVien = null;
        try {
            transaction.begin();
            String query = "SELECT nv.tenNV FROM NhanVien nv JOIN TaiKhoan tk ON nv.maNV = tk.tenTaiKhoan WHERE tk.tenTaiKhoan = :tenTaiKhoan";
            nhanVien = new HashMap<>();
            nhanVien.put(tenTaiKhoan, entityManager.createQuery(query).setParameter("tenTaiKhoan", tenTaiKhoan).getSingleResult());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return nhanVien;
    }

    public static String layMatKhau(String sdt, String cmnd) {
        String matKhau = null;
        try {
            transaction.begin();
            String query = "SELECT t.matKhau FROM TaiKhoan t JOIN NhanVien nv on t.tenTaiKhoan = nv.maNV WHERE nv.soCMND = :cmnd AND nv.soDienThoai = :sdt";
            matKhau = (String) entityManager.createQuery(query)
                    .setParameter("cmnd", cmnd)
                    .setParameter("sdt", sdt)
                    .getSingleResult();
            transaction.commit();
        } catch (NoResultException e) {
            // Xử lý khi không có kết quả
            System.out.println("Không tìm thấy mật khẩu cho sdt " + sdt + " và cmnd " + cmnd);
        } catch (NonUniqueResultException e) {
            // Xử lý khi có nhiều hơn một kết quả
            System.out.println("Có nhiều hơn một kết quả cho sdt " + sdt + " và cmnd " + cmnd);
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return matKhau;
    }

    public static String capNhatMatKhau(int TenTaiKhoan, String mkCu, String mkMoi) {
        String matKhau = null;
        try {
            transaction.begin();
            String updateQuery = "UPDATE TaiKhoan t SET t.matKhau = :mkMoi WHERE t.tenTaiKhoan = :tenTaiKhoan AND t.matKhau = :mkCu";
            int updatedCount = entityManager.createQuery(updateQuery)
                    .setParameter("tenTaiKhoan", TenTaiKhoan)
                    .setParameter("mkCu", mkCu)
                    .setParameter("mkMoi", mkMoi)
                    .executeUpdate();
            transaction.commit();

            if (updatedCount == 0) {
                System.out.println("Không tìm thấy mật khẩu cũ " + mkCu);
            } else {
                // Truy vấn để lấy mật khẩu đã cập nhật
                String selectQuery = "SELECT t.matKhau FROM TaiKhoan t WHERE t.tenTaiKhoan = :tenTaiKhoan AND t.matKhau = :mkMoi";
                matKhau = entityManager.createQuery(selectQuery, String.class)
                        .setParameter("tenTaiKhoan", TenTaiKhoan)
                        .setParameter("mkMoi", mkMoi)
                        .getSingleResult();
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return matKhau;
    }


    public void close() {
        entityManager.close();
    }
}
