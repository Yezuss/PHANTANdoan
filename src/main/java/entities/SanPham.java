/*
 * @ (#) SanPham.java   1.0     01/04/2024
 *
 * Copyright (c) 2024 IUH All rights reserved
 */
package entities;

import jakarta.persistence.*;
import lombok.*;

/*
 *@description:
 *@author: Nguyen Tien Dat 19473791
 *@date:   01/04/2024
 *version: 1.0
 */
@Entity
@Table(name = "SanPham")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maSanPham")
    private int maSanPham;

    @ManyToOne
    @JoinColumn(name = "maTheLoai")
    private TheLoai theLoai;

    @Column(name = "tenSanPham")
    private String tenSanPham;

    @Column(name = "tenTacGia")
    private String tenTacGia;

    @ManyToOne
    @JoinColumn(name = "maNCC")
    private NhaCungCap nhaCungCap;

    @Column(name = "donViTinh")
    private String donViTinh;

    @Column(name = "giaNhap")
    private double giaNhap;

    @Column(name = "donGia")
    private double donGia;

    @Column(name = "soLuong")
    private int soLuong;

    @Column(name = "hinhAnh")
    private byte[] hinhAnh;

    public SanPham(TheLoai theLoai, String tenSanPham, String tenTacGia, NhaCungCap nhaCungCap, String donViTinh, double giaNhap, double donGia, int soLuong, byte[] hinhAnh) {
        this.theLoai = theLoai;
        this.tenSanPham = tenSanPham;
        this.tenTacGia = tenTacGia;
        this.nhaCungCap = nhaCungCap;
        this.donViTinh = donViTinh;
        this.giaNhap = giaNhap;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
    }
}