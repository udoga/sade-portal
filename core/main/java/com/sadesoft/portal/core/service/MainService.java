package com.sadesoft.portal.core.service;

import java.util.List;
import com.sadesoft.portal.core.model.Item;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.model.User;

public class MainService {
    private final UserService userService;
    private final ItemService itemService;
    private final ProjectService projectService;

    public MainService(UserService userService, ItemService itemService, ProjectService projectService) {
        this.userService = userService;
        this.itemService = itemService;
        this.projectService = projectService;
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

    public List<Project> listProjects() { return projectService.list();}

    public Project readProject(int id) {
        return projectService.read(id);
    }

    public Project createProject(Project project) {
        return projectService.create(project);
    }

    public Project updateProject(int id, Project project) {
        return projectService.update(id, project);
    }

    public Project deleteProject(int id) {
        return projectService.delete(id);
    }
}
