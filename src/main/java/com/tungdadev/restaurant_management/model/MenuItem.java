package com.tungdadev.restaurant_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 09:55
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "menu_item")
@Schema(description = "All details about the Menu. ")
public class MenuItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @Column(name = "menu_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The database generated Menu Item ID")
    private Long Id;

    @NotEmpty
    @Column(name = "name", nullable = false)
    @Schema(description = "Menu item title")
    private String name;

    @NotEmpty
    @Column(name = "description", nullable = false)
    @Schema(description = "Menu item description")
    private String description;

    @NotEmpty
    @Column(name = "image_url", nullable = false)
    @Schema(description = "URL for image of menu item")
    private String imageUrl;

    @NotNull
    @Column(name = "price", nullable = false)
    @Schema(description = "Price of menu item")
    private Double price;

    @Column(name = "details", nullable = true)
    @Schema(description = "Additional description for menu item")
    private String details;

    @JsonIgnore
    @OneToMany(mappedBy = "menuItem")
    Set<BillDetail> billitems;
}
