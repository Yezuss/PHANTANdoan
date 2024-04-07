/*
 * @ (#) NhanVien.java   1.0     01/04/2024
 *
 * Copyright (c) 2024 IUH All rights reserved
 */
package entities;
/*
 *@description:
 *@author: Nguyen Tien Dat 19473791
 *@date:   01/04/2024
 *version: 1.0
 */

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Vector;

@Entity
@Table(name = "NhanVien")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NhanVien {
    @Id
    @Column(name = "maNV", unique = true, nullable = false,columnDefinition = "int")
    private int maNV;

    @Column(name = "hoNV", nullable = false,columnDefinition = "nvarchar(50)")
    private String hoNV;

    @Column(name = "tenNV", nullable = false,columnDefinition = "nvarchar(50)")
    private String tenNV;

    @Column(name = "ngaySinh", nullable = false,columnDefinition = "date")
    private Date ngaySinh;

    @Column(name = "gioiTinh", nullable = false,columnDefinition = "nvarchar(10)")
    private String gioiTinh;

    @Column(name = "soDienThoai",unique = true, nullable = false,columnDefinition = "nvarchar(10)")
    private String soDienThoai;

    @Column(name = "soCMND",unique = true, nullable = false,columnDefinition = "nvarchar(12)")
    private String soCMND;

    @Column(name = "diaChi", nullable = false,columnDefinition = "nvarchar(100)")
    private String diaChi;

    @Column(name = "caLamViec", nullable = false,columnDefinition = "nvarchar(50)")
    private String caLamViec;

    @OneToOne
    @JoinColumn(name = "tenTaiKhoan")
    private TaiKhoan taiKhoan;

    @Column(name = "loaiNhanVien", nullable = false,columnDefinition = "nvarchar(50)")
    private String loaiNhanVien;


    public Vector<Object> toVector() {
        Vector<Object> v = new Vector<>();
        v.add("");
        v.add(maNV);
        v.add(hoNV);
        v.add(tenNV);
        v.add(ngaySinh);
        v.add(gioiTinh);
        v.add(soDienThoai);
        v.add(soCMND);
        v.add(diaChi);
        v.add(caLamViec);
//        v.add(taiKhoan.getTenTaiKhoan());
        v.add(loaiNhanVien);
        return v;
    }
}
