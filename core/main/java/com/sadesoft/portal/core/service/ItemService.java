package com.sadesoft.portal.core.service;

import java.util.List;
import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.Item;

public class ItemService {
    private final Repo<Item> repo;

    public ItemService(Repo<Item> repo) {
        this.repo = repo;
    }

    public List<Item> list() {
        return repo.list();
    }

    public Item read(int id) {
        return repo.read(id);
    }

    public Item create(Item item) {
        return repo.create(item);
    }

    public Item update(int id, Item item) {
        return repo.update(id, item);
    }

    public Item delete(int id) {
        return repo.delete(id);
    }
}
