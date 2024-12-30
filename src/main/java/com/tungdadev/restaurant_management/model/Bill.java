package com.tungdadev.restaurant_management.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 09:52
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bill")
@Schema(description = "All details about the Bill. ")
public class Bill implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "bill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The database generated Bill ID")
    private Long id;

    @OneToMany(mappedBy = "bill")
    @Schema(description = "List detail of a bill")
    Set<BillDetail> billDetails;

    public List<Long> getMenuIds() {
        List<Long> ids = new ArrayList<>();
        for (BillDetail billDetail : billDetails) {
            ids.add(billDetail.getId().getMenuItemId());
        }
        return ids;
    }
}
