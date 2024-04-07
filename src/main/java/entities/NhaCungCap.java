/*
 * @ (#) NhaCungCap.java   1.0     01/04/2024
 *
 * Copyright (c) 2024 IUH All rights reserved
 */
package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Objects;

/*
 *@description:
 *@author: Nguyen Tien Dat 19473791
 *@date:   01/04/2024
 *version: 1.0
 */
@Entity
@Table(name = "NhaCungCap")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NhaCungCap {

    @Id
    @Column(name = "maNCC", unique = true, nullable = false,columnDefinition = "int")
    private int maNCC;

    @Column(name = "tenNCC", nullable = false, columnDefinition = "nvarchar(255)")
    private String tenNCC;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NhaCungCap that = (NhaCungCap) o;
        return getMaNCC() == that.getMaNCC() && Objects.equals(getTenNCC(), that.getTenNCC());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaNCC(), getTenNCC());
    }

}
