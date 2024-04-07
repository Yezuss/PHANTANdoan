/*
 * @ (#) TheLoai.java   1.0     01/04/2024
 *
 * Copyright (c) 2024 IUH All rights reserved
 */
package entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

/*
 *@description:
 *@author: Nguyen Tien Dat 19473791
 *@date:   01/04/2024
 *version: 1.0
 */
@Entity
@Table(name = "TheLoai")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TheLoai {
    @Id
    @Column(name = "maTheLoai", unique = true, nullable = false,columnDefinition = "int")
    private int maTheLoai;

    @Column(name = "tenTheLoai",columnDefinition = "nvarchar(255)")
    private String tenTheLoai;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TheLoai)) return false;
        TheLoai theLoai = (TheLoai) o;
        return getMaTheLoai() == theLoai.getMaTheLoai() && Objects.equals(getTenTheLoai(), theLoai.getTenTheLoai());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaTheLoai(), getTenTheLoai());
    }
}
