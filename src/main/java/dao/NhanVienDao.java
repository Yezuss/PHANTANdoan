/*
 * @ (#) NhanVienDao.java   1.0     02/04/2024
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
import entities.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.sql.Date;
import java.util.LinkedHashSet;

public class NhanVienDao {
    static EntityManager entityManager = ConnectDatabase.getEntityManager();
    static EntityTransaction transaction = entityManager.getTransaction();

    public static NhanVien themNhanVien(NhanVien nhanVien) {

        NhanVien addedNhanVien = null;

        try {
            transaction.begin();
            entityManager.persist(nhanVien);
            transaction.commit();
            addedNhanVien = nhanVien; // Lưu lại đối tượng nhân viên vừa được thêm vào cơ sở dữ liệu
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return addedNhanVien;
    }

    public static boolean xoaNhanVien(int maNV) {
        NhanVien nhanVien = entityManager.find(NhanVien.class, maNV);

        if (nhanVien == null) {
            return false;
        }

        try {
            transaction.begin();
            entityManager.remove(nhanVien);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean updateNhanVien(int maNV, String hoNV, String tenNV, Date ngaySinh, String gioiTinh, String soCMND,
                                  String soDienThoai, String diaChi, String caLamViec, String loaiNhanVien) {
        int n = 0;
        try {
            transaction.begin();
            NhanVien nhanVien = entityManager.find(NhanVien.class, maNV);
            if (nhanVien != null) {
                nhanVien.setHoNV(hoNV);
                nhanVien.setTenNV(tenNV);
                nhanVien.setNgaySinh(ngaySinh);
                nhanVien.setGioiTinh(gioiTinh);
                nhanVien.setSoCMND(soCMND);
                nhanVien.setSoDienThoai(soDienThoai);
                nhanVien.setDiaChi(diaChi);
                nhanVien.setCaLamViec(caLamViec);
                nhanVien.setLoaiNhanVien(loaiNhanVien);
                entityManager.merge(nhanVien);
                transaction.commit();
                n = 1;
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return n > 0;
    }

    public static NhanVien timNhanVienTheoMa(int maNV) {
        try {
            transaction.begin();
            NhanVien nhanVien = entityManager.find(NhanVien.class, maNV);
            transaction.commit();
            return nhanVien;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public static LinkedHashSet<NhanVien> timNhanVienTheoTen(String tenNV) {
        LinkedHashSet<NhanVien> nhanViens = null;

        try {
            transaction.begin();
            nhanViens = new LinkedHashSet<>(entityManager.createQuery("SELECT nv FROM NhanVien nv WHERE nv.tenNV = :tenNV", NhanVien.class)
                    .setParameter("tenNV", tenNV)
                    .getResultList());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return nhanViens;
    }

    public static NhanVien timNhanVienTheoSDT(String sdt){
        try {
            transaction.begin();
            NhanVien nhanVien = entityManager.createQuery("SELECT nv FROM NhanVien nv WHERE nv.soDienThoai = :sdt", NhanVien.class)
                    .setParameter("sdt", sdt)
                    .getSingleResult();
            transaction.commit();
            return nhanVien;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public static LinkedHashSet<NhanVien> timNhanVienTheoCaLamViec(String ca){
        LinkedHashSet<NhanVien> nhanViens = null;
        try {
            transaction.begin();
            nhanViens = new LinkedHashSet<>(entityManager.createQuery("SELECT nv FROM NhanVien nv WHERE nv.caLamViec = :ca", NhanVien.class)
                    .setParameter("ca", ca)
                    .getResultList());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return nhanViens;
    }

    public static LinkedHashSet<NhanVien> layDanhSachNhanVien() {
        LinkedHashSet<NhanVien> nhanViens = null;
        try {
            transaction.begin();
            nhanViens = new LinkedHashSet<>(entityManager.createQuery("SELECT nv FROM NhanVien nv", NhanVien.class)
                    .getResultList());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return nhanViens;
    }

    public void close(){
        entityManager.close();
    }
}

