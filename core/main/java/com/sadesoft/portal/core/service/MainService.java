package com.sadesoft.portal.core.service;

import java.util.List;
import com.sadesoft.portal.core.model.Item;
import com.sadesoft.portal.core.model.User;

public class MainService {
    private final UserService userService;
    private final ItemService itemService;

    public MainService(UserService userService, ItemService itemService) {
        this.userService = userService;
        this.itemService = itemService;
    }

    public List<User> listUsers() {
        return userService.list();
    }

    public User readUser(int id) {
        return userService.read(id);
    }

    public User createUser(User user) {
        return userService.create(user);
    }

    public User updateUser(int id, User user) {
        return userService.update(id, user);
    }

    public User deleteUser(int id) {
        return userService.delete(id);
    }

    public List<Item> listItems() {
        return itemService.list();
    }

    public Item readItem(int id) {
        return itemService.read(id);
    }

    public Item createItem(Item item) {
        return itemService.create(item);
    }

    public Item updateItem(int id, Item item) {
        return itemService.update(id, item);
    }

    public Item deleteItem(int id) {
        return itemService.delete(id);
    }
}
