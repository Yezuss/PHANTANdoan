/*
 * @ (#) TaiKhoan.java   1.0     01/04/2024
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
@Table(name = "TaiKhoan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaiKhoan {

    @Id
    @Column(name = "tenTaiKhoan", nullable = false, columnDefinition = "int")
    private int tenTaiKhoan;

    @Column(name = "matKhau", nullable = false, columnDefinition = "VARCHAR(50)")
    private String matKhau;

    @Column(name = "loaiTaiKhoan", nullable = false, columnDefinition = "VARCHAR(50)")
    private String loaiTaiKhoan;
//
//    @OneToOne
//    @JoinColumn(name = "maNV")
//    private NhanVien nhanVien;

}
