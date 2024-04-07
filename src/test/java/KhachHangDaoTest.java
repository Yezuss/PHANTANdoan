import dao.KhachHangDao;
import entities.KhachHang;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
 * @ (#) KhachHangDaoTest.java   1.0     03/04/2024
 *
 * Copyright (c) 2024 IUH All rights reserved
 */
/*
 *@description:
 *@author: Nguyen Tien Dat 19473791
 *@date:   03/04/2024
 *version: 1.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class KhachHangDaoTest {

    private KhachHangDao khachHangDao;

    @BeforeEach
    public void init() {
        khachHangDao = new KhachHangDao();
    }

    @Test
    void testThemKhachHang() {
        KhachHang khachHang = new KhachHang();

        khachHang.setMaKH(3);
        khachHang.setHoKH("Nguyễn");
        khachHang.setTenKH("Văn S");
        khachHang.setNgaySinh(Date.valueOf("1999-06-10"));
        khachHang.setGioiTinh("Nu");
        khachHang.setSoCMND("123436825740");
        khachHang.setSoDienThoai("0987657391");
        khachHang.setDiaChi("123 Đường ABC, Quận XYZ, Thành phố HCM");

        khachHangDao.themKhachHang(khachHang);

        assertNotNull(khachHang);
        assertEquals(3, khachHang.getMaKH());

    }

    @Test
    void testLayKhachHangTheoMa() {
        KhachHang khachHang = khachHangDao.layKhachHangTheoMa(2);

        assertNotNull(khachHang);
        assertEquals("Văn F", khachHang.getTenKH());
    }

    @Test
    void testLayDanhSachKhachHang() {
        LinkedHashSet<KhachHang> khachHangs = khachHangDao.layDanhSachKhachHang();
        assertEquals(2, khachHangs.size());

        System.out.println("Danh sách khách hàng:\n"+khachHangs);
    }

    @Test
    void testLayKhachHangTheoTen(){
        LinkedHashSet<KhachHang> khachHangs = khachHangDao.layKhachHangTheoTen("Văn F");
        assertEquals(1, khachHangs.size());
        System.out.println("Danh sách khách hàng theo tên:\n"+khachHangs);
    }

    @Test
    void testLayKhachHangTheoSDT(){
        KhachHang khachHang = khachHangDao.layKhachHangTheoSDT("0987654376");
        assertNotNull(khachHang);
        System.out.println("Khách hàng theo số điện thoại:\n"+khachHang);
    }

    @Test
    void testLayKhachHangTheoCMND(){
        KhachHang khachHang = khachHangDao.layKhachHangTheoCMND("123456769975");
        assertNotNull(khachHang);
        System.out.println("Khách hàng theo số CMND:\n"+khachHang);
    }

    @Test
    void testXoaKhachHang() {
        khachHangDao.xoaKhachHang(3);
        LinkedHashSet<KhachHang> khachHangs = khachHangDao.layDanhSachKhachHang();
        assertEquals(2, khachHangs.size());
    }

    @Test
    void testCapNhatKhachHang() {
        KhachHang khachHang = khachHangDao.capNhatKhachHang(
                3,
                "Nguyễn",
                "Văn S",
                Date.valueOf("1999-06-10"),
                "Nữ",
                "123436825740",
                "0987657391",
                "123 Đường ABC, Quận XYZ, Thành phố HCM"
        );
        assertEquals("Nữ", khachHang.getGioiTinh());
    }

    @AfterAll
    public void close() {
        khachHangDao.close();
    }
}
