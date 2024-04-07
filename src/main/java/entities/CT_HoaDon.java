/*
 * @ (#) ChiTietHoaDon.java   1.0     01/04/2024
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
@Table(name = "ChiTietHoaDon")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CT_HoaDon {

    @Id
    @ManyToOne
    @JoinColumn(name = "maHoaDon")
    private HoaDon maHoaDon;

    @Id
    @ManyToOne
    @JoinColumn(name = "maSanPham")
    private SanPham maSanPham;

    @Column(name = "donGia", nullable = false)
    private double donGia;

    @Column(name = "soLuong", nullable = false)
    private int soLuong;

    @Column(name = "donViTinh", nullable = false)
    private String donViTinh;

    @Column(name = "giamGia", nullable = false)
    private double giamGia;
}
