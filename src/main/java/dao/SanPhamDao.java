package dao;/*
 * @description:
 * @author: 19475251-Tăng Thanh Đức
 * @date: 07/04/2024
 */

import database.ConnectDatabase;
import entities.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.LinkedHashSet;
import java.util.List;

public class SanPhamDao {
    static EntityManager entityManager = ConnectDatabase.getEntityManager();
    static EntityTransaction transaction = entityManager.getTransaction();

    public static SanPham themSanPham(SanPham sanPham) {
        SanPham addedSanPham = null;

        try {
            transaction.begin();
            entityManager.persist(sanPham);
            transaction.commit();
            addedSanPham = sanPham;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return addedSanPham;
    }

    public static SanPham laySanPhamTheoMa(int maSanPham) {
        SanPham sanPham = null;
        try {
            transaction.begin();
            sanPham = entityManager.find(SanPham.class, maSanPham);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return sanPham;
    }

    public static LinkedHashSet<SanPham> layDanhSachSanPham() {
        LinkedHashSet<SanPham> danhSachSanPham = null;
        try {
            transaction.begin();
            danhSachSanPham = new LinkedHashSet<>(entityManager.createQuery("SELECT sp FROM SanPham sp", SanPham.class).getResultList());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return danhSachSanPham;
    }

    public static List<SanPham> laySanPhamTheoTen(String ten) {
        List<SanPham> danhSachSanPham = null;
        try {
            transaction.begin();
            danhSachSanPham = entityManager.createQuery("SELECT sp FROM SanPham sp WHERE sp.tenSanPham LIKE :ten", SanPham.class)
                    .setParameter("ten", "%" + ten + "%")
                    .getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return danhSachSanPham;
    }

    public static boolean xoaSanPham(int maSanPham) {
        boolean result = false;
        try {
            transaction.begin();
            SanPham sanPham = entityManager.find(SanPham.class, maSanPham);
            if (sanPham != null) {
                entityManager.remove(sanPham);
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

    public static SanPham capNhatSanPham(int maSanPham, String tenSanPham, String tenTacGia, String donViTinh,
                                         double giaNhap, double donGia, int soLuong) {
        SanPham updatedSanPham = null;
        try {
            transaction.begin();
            String query = "UPDATE SanPham sp SET sp.tenSanPham = :tenSanPham, sp.tenTacGia = :tenTacGia, sp.donViTinh = :donViTinh, " +
                    "sp.giaNhap = :giaNhap, sp.donGia = :donGia, sp.soLuong = :soLuong WHERE sp.maSanPham = :maSanPham";
            entityManager.createQuery(query)
                    .setParameter("tenSanPham", tenSanPham)
                    .setParameter("tenTacGia", tenTacGia)
                    .setParameter("donViTinh", donViTinh)
                    .setParameter("giaNhap", giaNhap)
                    .setParameter("donGia", donGia)
                    .setParameter("soLuong", soLuong)
                    .setParameter("maSanPham", maSanPham)
                    .executeUpdate();
            updatedSanPham = entityManager.find(SanPham.class, maSanPham);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return updatedSanPham;
    }

    public void close() {
        ConnectDatabase.close();
    }
}