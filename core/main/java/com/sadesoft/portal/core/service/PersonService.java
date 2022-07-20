package com.sadesoft.portal.core.service;

import java.util.List;
import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.Person;

public class PersonService {
    private final Repo<Person> repo;

    public PersonService(Repo<Person> repo) {
        this.repo = repo;
    }

    public List<Person> list() {
        return repo.list();
    }

    public Person read(int id) {
        return repo.read(id);
    }

    public Person create(Person person) {
        return repo.create(person);
    }

    public Person update(int id, Person person) {
        return repo.update(id, person);
    }

    public Person delete(int id) {
        return repo.delete(id);
    }
}
