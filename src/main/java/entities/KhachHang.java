/*
 * @ (#) KhachHang.java   1.0     01/04/2024
 *
 * Copyright (c) 2024 IUH All rights reserved
 */
package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Date;
import java.util.Vector;


/*
 *@description:
 *@author: Nguyen Tien Dat 19473791
 *@date:   01/04/2024
 *version: 1.0
 */
@Entity
@Table(name = "KhachHang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KhachHang {
    @Id
    @Column(name = "maKH" ,unique = true, nullable = false,columnDefinition = "int")
    private int maKH;

    @Column(name = "hoKH", nullable = false,columnDefinition = "nvarchar(50)")
    private String hoKH;

    @Column(name = "tenKH", nullable = false,columnDefinition = "nvarchar(50)")
    private String tenKH;

    @Column(name = "ngaySinh", nullable = false,columnDefinition = "date")
    private Date ngaySinh;

    @Column(name = "gioiTinh", nullable = false,columnDefinition = "nvarchar(10)")
    private String gioiTinh;

    @Column(name = "soCMND", unique = true, nullable = false,columnDefinition = "nvarchar(12)")
    private String soCMND;

    @Column(name = "soDienThoai", unique = true, nullable = false,columnDefinition = "nvarchar(10)")
    private String soDienThoai;

    @Column(name = "diaChi", nullable = false,columnDefinition = "nvarchar(100)")
    private String diaChi;

    public KhachHang(String hoKH, String tenKH, Date ngaySinh, String gioiTinh, String soCMND, String soDienThoai, String diaChi) {
        super();
        this.hoKH = hoKH;
        this.tenKH = tenKH;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.soCMND = soCMND;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }

    public Vector<Object> toVector() {
        Vector<Object> v = new Vector<>();
        v.add("");
        v.add(maKH);
        v.add(hoKH);
        v.add(tenKH);
        v.add(ngaySinh);
        v.add(gioiTinh);
        v.add(soCMND);
        v.add(soDienThoai);
        v.add(diaChi);
        return v;
    }
}
