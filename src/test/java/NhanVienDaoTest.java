import dao.NhanVienDao;
import entities.NhanVien;
import org.junit.jupiter.api.*;

import java.sql.Date;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

/*
 * @ (#) NhanVienDaoTest.java   1.0     02/04/2024
 *
 * Copyright (c) 2024 IUH All rights reserved
 */
/*
 *@description:
 *@author: Nguyen Tien Dat 19473791
 *@date:   02/04/2024
 *version: 1.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NhanVienDaoTest {

    private NhanVienDao nhanVienDao;

    @BeforeEach
    public void setUp() {
        nhanVienDao = new NhanVienDao();
    }

    @Test
    void testThemNhanVien(){
        NhanVien nhanVien = new NhanVien();

        // Thiết lập các thuộc tính cho đối tượng nhân viên
        nhanVien.setMaNV(3);
        nhanVien.setHoNV("Nguyễn");
        nhanVien.setTenNV("Văn C");
        nhanVien.setNgaySinh(Date.valueOf("2004-06-15"));
        nhanVien.setGioiTinh("Nữ");
        nhanVien.setSoDienThoai("0126756790");
        nhanVien.setSoCMND("123443732886");
        nhanVien.setDiaChi("123 Đường ABC, Quận XYZ, Thành phố HCM");
        nhanVien.setCaLamViec("Ca chiều");
        nhanVien.setLoaiNhanVien("Toàn thời gian");

        NhanVien adedNhanVien = NhanVienDao.themNhanVien(nhanVien);
        assertNotNull(adedNhanVien, "Thêm nhân viên không thành công");
        assertNotNull(nhanVien.getMaNV());
    }

    @Test
    void testXoaNhanVien(){
        boolean result = NhanVienDao.xoaNhanVien(2);
        assertTrue(result, "Xóa nhân viên không thành công");
    }

    @Test
    void testCapNhatNhanVien(){
        // Act
        boolean result = nhanVienDao.updateNhanVien(
                1,
                "Trần",
                "Văn B",
                Date.valueOf("1999-12-31"),
                "Nam",
                "987654321",
                "0987654321",
                "321 Đường XYZ, Quận ABC, Thành phố HCM",
                "Ca chiều",
                "Quản lý"
        );

        // Assert
        assertTrue(result, "Cập nhật nhân viên không thành công");

        // Check if the information was updated correctly
        NhanVien updatedNhanVien = nhanVienDao.timNhanVienTheoMa(1);
        assertNotNull(updatedNhanVien);
        assertEquals("Trần", updatedNhanVien.getHoNV());
        assertEquals("Văn B", updatedNhanVien.getTenNV());
        assertEquals(Date.valueOf("1999-12-31"), updatedNhanVien.getNgaySinh());
        assertEquals("Nam", updatedNhanVien.getGioiTinh());
        assertEquals("987654321", updatedNhanVien.getSoCMND());
        assertEquals("0987654321", updatedNhanVien.getSoDienThoai());
        assertEquals("321 Đường XYZ, Quận ABC, Thành phố HCM", updatedNhanVien.getDiaChi());
        assertEquals("Ca chiều", updatedNhanVien.getCaLamViec());
        assertEquals("Quản lý", updatedNhanVien.getLoaiNhanVien());
    }

    @Test
    void testTimNhanVienTheoMa(){
        NhanVien nhanVien = NhanVienDao.timNhanVienTheoMa(1);
        assertNotNull(nhanVien, "Không tìm thấy nhân viên");
    }

    @Test
    void testTimNhanVienTheoTen(){
        LinkedHashSet<NhanVien> nhanVien = NhanVienDao.timNhanVienTheoTen("Văn B");
        assertNotNull(nhanVien, "Không tìm thấy nhân viên");
    }

    @Test
    void testTimNhanVVienTheoSDT(){
        NhanVien nhanVien = NhanVienDao.timNhanVienTheoSDT("0987654321");
        assertNotNull(nhanVien, "Không tìm thấy nhân viên");
        System.out.println(nhanVien);
    }

    @Test
    void testTimNhanVienTheoCaLamViec(){
        LinkedHashSet<NhanVien> nhanVien = NhanVienDao.timNhanVienTheoCaLamViec("Ca sáng");
        assertNotNull(nhanVien, "Không tìm thấy nhân viên");
        System.out.println(nhanVien);
    }
    @Test
    void testLayDanhSachNhanVien(){
        LinkedHashSet<NhanVien> nhanVien = NhanVienDao.layDanhSachNhanVien();
        assertNotNull(nhanVien, "Không tìm thấy nhân viên");
        System.out.println(nhanVien);
    }

    @AfterAll
    public void tearDown() {
        nhanVienDao.close();
    }
}
