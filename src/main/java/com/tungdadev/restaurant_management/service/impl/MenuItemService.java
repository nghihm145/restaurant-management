package com.tungdadev.restaurant_management.service.impl;

import com.tungdadev.restaurant_management.model.MenuItem;
import com.tungdadev.restaurant_management.repository.MenuItemRepository;
import com.tungdadev.restaurant_management.service.IMenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 10:18
 */
@Service("menuItemService")
@Transactional
@RequiredArgsConstructor
public class MenuItemService implements IMenuItemService {
    private MenuItemRepository repository;

    @Override
    public MenuItem findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public MenuItem findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<MenuItem> findByNameIgnoreCaseOrDescriptionIgnoreCaseOrDetailsIgnoreCase(String name,
                                                                                         Pageable pageable) {
        return repository.findByNameIgnoreCaseOrDescriptionIgnoreCaseOrDetailsIgnoreCase(name.toLowerCase(), pageable);
    }

    @Override
    public void saveMenuItem(MenuItem item) {
        repository.save(item);
    }

    @Override
    public void deleteMenuItemById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<MenuItem> findAllMenuItems(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    @Override
    public boolean isMenuItemExist(MenuItem item) {
        return repository.countByName(item.getName()) != 0;
    }

    @Override
    public List<MenuItem> findMenuByInIds(Collection<Long> ids) {
        return repository.findMenuByInIds(ids);
    }

    @Override
    public List<MenuItem> findMenuByInNames(Collection<String> names) {
        return repository.findMenuByInNames(names);
    }
}
