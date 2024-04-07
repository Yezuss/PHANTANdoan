/*
 * @ (#) HoaDon.java   1.0     01/04/2024
 *
 * Copyright (c) 2024 IUH All rights reserved
 */
package entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

/*
 *@description:
 *@author: Nguyen Tien Dat 19473791
 *@date:   01/04/2024
 *version: 1.0
 */
@Entity
@Table(name = "HoaDon")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HoaDon {
    @Id
    @Column(name = "maHoaDon" ,unique = true, nullable = false)
    private int maHoaDon;

    @Column(name = "ngayLap", nullable = false)
    private Date ngayLap;

    @ManyToOne
    @JoinColumn(name = "maNV")
    private NhanVien maNV;

    @ManyToOne
    @JoinColumn(name = "maKH")
    private KhachHang maKH;

    @Column(name = "tongTien", nullable = false)
    private double tongTien;

    @Column(name = "tinhTrang", nullable = false)
    private String tinhTrang;
}
