package dao;/*
 * @description:
 * @author: 19475251-Tăng Thanh Đức
 * @date: 08/04/2024
 */


import database.ConnectDatabase;
import entities.CT_HoaDon;
import entities.HoaDon;
import entities.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDao{

    private EntityManager entityManager;

    public HoaDonDao() {
        entityManager = ConnectDatabase.getInstance().getEntityManager();
    }

    public List<HoaDon> getAllHoaDons() {
        List<HoaDon> list = new ArrayList<>();
        try {
            Query query = entityManager.createQuery("SELECT h FROM HoaDon h");
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }



/*    public List<HoaDon> getHoaDonsByNhanVien(String tenNV) {
        List<HoaDon> list = new ArrayList<>();
        try {
            String jpql = "SELECT h FROM HoaDon h JOIN FETCH h.nhanVien n WHERE n.tenNV LIKE :tenNV";
            TypedQuery<HoaDon> query = entityManager.createQuery(jpql, HoaDon.class);
            query.setParameter("tenNV", tenNV);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }*/


    public List<HoaDon> getHoaDonsByNgayLap(int ngay, int thang, int nam) {
        List<HoaDon> list = new ArrayList<>();
        try {
            Query query = entityManager.createQuery("SELECT h FROM HoaDon h WHERE DAY(h.ngayLap) = :ngay AND MONTH(h.ngayLap) = :thang AND YEAR(h.ngayLap) = :nam");
            query.setParameter("ngay", ngay);
            query.setParameter("thang", thang);
            query.setParameter("nam", nam);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<HoaDon> getHoaDons() {
        List<HoaDon> list = new ArrayList<>();
        try {
            Query query = entityManager.createQuery("SELECT h FROM HoaDon h");
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<CT_HoaDon> getChiTiets(int ma) {
        List<CT_HoaDon> list = new ArrayList<>();
        try {
            Query query = entityManager.createQuery("SELECT c FROM CT_HoaDon c WHERE c.maHoaDon = :ma");
            query.setParameter("ma", ma);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getTenKH(int ma) {
        String ten = "";
        try {
            Query query = entityManager.createQuery("SELECT kh.tenKH FROM KhachHang kh WHERE kh.maKH = :ma");
            query.setParameter("ma", ma);
            ten = (String) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ten;
    }

    public HoaDon getHoaDonByMa(int ma) {
        HoaDon hd = null;
        try {
            hd = entityManager.find(HoaDon.class, ma);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hd;
    }

}