package com.tungdadev.restaurant_management.service;

import com.tungdadev.restaurant_management.model.MenuItem;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 10:18
 */
public interface IMenuItemService {
    MenuItem findById(Long Id);

    MenuItem findByName(String name);

    List<MenuItem> findByNameIgnoreCaseOrDescriptionIgnoreCaseOrDetailsIgnoreCase(String name, Pageable pageable);

    void saveMenuItem(MenuItem item);

    void deleteMenuItemById(Long id);

    List<MenuItem> findAllMenuItems(Pageable pageable);

    boolean isMenuItemExist(MenuItem item);

    List<MenuItem> findMenuByInIds(Collection<Long> ids);

    List<MenuItem> findMenuByInNames(Collection<String> names);
}
