/*
 * @ (#) TaiKhoanDaoTest.java   1.0     02/04/2024
 *
 * Copyright (c) 2024 IUH All rights reserved
 */
/*
 *@description:
 *@author: Nguyen Tien Dat 19473791
 *@date:   02/04/2024
 *version: 1.0
 */

import dao.TaiKhoanDao;
import entities.TaiKhoan;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.LinkedHashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaiKhoanDaoTest {

    private TaiKhoanDao taiKhoanDao;

    @BeforeEach
    public void init() {
        taiKhoanDao = new TaiKhoanDao();
    }

    @Test
    void testThemTaiKhoan() {
        TaiKhoan tk = new TaiKhoan();
        tk.setTenTaiKhoan(2);
        tk.setMatKhau("123");
        tk.setLoaiTaiKhoan("nhân viên");

        TaiKhoan result = taiKhoanDao.themTaiKhoan(tk);
        assertEquals(tk, result);
        assertNotNull(result.getTenTaiKhoan());

    }

    @Test
    void testLayDanhSachTaiKhoan() {
        LinkedHashSet<TaiKhoan> taiKhoans = taiKhoanDao.layDanhsachTaiKhoan();
        assertNotNull(taiKhoans);
        System.out.println(taiKhoans);
    }

    @Test
    void testLayTaiKhoanTheoTenTaiKhoan() {
        TaiKhoan taiKhoan = taiKhoanDao.layTaiKhoanTheoTenTaiKhoan(1);
        assertNotNull(taiKhoan);
        System.out.println(taiKhoan);
    }

    @Test
    void layTenNhanVienTheoTenTaiKhoan(){
        Map<Integer,Object> taiKhoan = taiKhoanDao.layTenNhanVienTheoTenTaiKhoan(2);
        assertNotNull(taiKhoan);
        System.out.println(taiKhoan);
    }

    @Test
    void testLayMatKhau(){
        String matKhau = taiKhoanDao.layMatKhau("0126756790","123443732886");
        assertNotNull(matKhau);

        System.out.println(matKhau);
    }

    @Test
    void testCapNhatMatKhau(){
        String matKhau = taiKhoanDao.capNhatMatKhau(1,"123456789","12345");
        assertEquals("12345",matKhau);

        System.out.println(matKhau);
    }

    @AfterAll
    public void tearDown() {
        taiKhoanDao.close();
    }
}
