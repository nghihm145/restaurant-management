package com.tungdadev.restaurant_management.controller;

import com.tungdadev.restaurant_management.exception.ObjectAlreadyExistException;
import com.tungdadev.restaurant_management.exception.ObjectNotFoundException;
import com.tungdadev.restaurant_management.model.MenuItem;
import com.tungdadev.restaurant_management.model.dto.MenuItemDTO;
import com.tungdadev.restaurant_management.service.IMenuItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 10:10
 */
@RestController
@RequestMapping("/api/menu-items")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Menu Item Management", description = "Menu Item Management")
public class MenuItemController {

    private final IMenuItemService menuItemService;

    private final ModelMapper modelMapper;

    @Operation(summary = "Retrieve All Menu Items")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItemDTO> listAllMenuItems(Pageable pageable) {
        return menuItemService.findAllMenuItems(pageable).stream().map(item -> convertToDTO(item))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Retrieve Single Menu Item")
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MenuItemDTO getMenuItem(
            @Parameter(description = "Menu id from which Menu object will retrieve", required = true) @PathVariable("id") long id) {

        log.info("Fetching MenuItem with id {}", id);
        MenuItem item = menuItemService.findById(id);
        if (item == null) {
            log.error("MenuItem with id {} not found", id);
            throw new ObjectNotFoundException(String.valueOf(id));
        }
        return convertToDTO(item);
    }

    @Operation(summary = "Retrieve Menu Item(s) by Name, Description, and Details")
    @GetMapping(value = "/search")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItemDTO> searchMenuByNameOrDescriptinOrDetails(
            @Parameter(description = "Search keyword (title, description, or detail) of Menu object will retrieve", required = true) @RequestParam(value = "name") String name,
            Pageable pageable) {
        List<MenuItem> menuItems = menuItemService.findByNameIgnoreCaseOrDescriptionIgnoreCaseOrDetailsIgnoreCase(name,
                pageable);
        return menuItems.stream().map(item -> convertToDTO(item)).collect(Collectors.toList());
    }

    @Operation(summary = "Create a Menu Item")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MenuItemDTO createMenuItem(
            @Parameter(description = "A menu object, include: title, imageUrl, description, price and detail to create a new menu", required = true) @RequestBody MenuItem item) {

        log.info("Create menu item: {}", item);
        if (menuItemService.isMenuItemExist(item)) {
            log.error("Unable to create. A MenuItem with name {} already exist", item.getName());
            throw new ObjectAlreadyExistException(item.getName());
        }
        menuItemService.saveMenuItem(item);
        return convertToDTO(item);
    }

    @Operation(summary = "Update a MenuItem")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MenuItemDTO updateMenuItem(
            @Parameter(description = "Menu id from which Menu object will retrieve", required = true) @PathVariable("id") long id,
            @Parameter(description = "A menu object, include: title, imageUrl, description, price and detail to update a new menu", required = true) @RequestBody MenuItem item) {

        log.info("Update menu item wit id {}", id);
        MenuItem currentItem = menuItemService.findById(id);
        if (currentItem == null) {
            log.error("Unable to update. A Menu Item with id {} not found", id);
            throw new ObjectNotFoundException(String.valueOf(id));
        }

        currentItem.setName(item.getName());
        currentItem.setDescription(item.getDescription());
        currentItem.setImageUrl(item.getImageUrl());
        currentItem.setPrice(item.getPrice());
        currentItem.setDetails(item.getDetails());
        menuItemService.saveMenuItem(currentItem);
        return convertToDTO(currentItem);
    }

    @Operation(summary = "Delete a Menu Item")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMenuItem(
            @Parameter(description = "Menu id from which Menu object will be deleted", required = true) @PathVariable("id") long id) {

        log.info("Delete menu item with id {}", id);
        MenuItem item = menuItemService.findById(id);
        if (item == null) {
            log.error("Unable to delete. A menu item with id {} not found", id);
            throw new ObjectNotFoundException(String.valueOf(id));
        }
        menuItemService.deleteMenuItemById(id);
    }

    private MenuItemDTO convertToDTO(MenuItem menuItem) {
        return modelMapper.map(menuItem, MenuItemDTO.class);
    }
}
