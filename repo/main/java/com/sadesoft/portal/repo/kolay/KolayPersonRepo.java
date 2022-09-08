package com.sadesoft.portal.repo.kolay;

import com.sadesoft.portal.base.rest.JsonMapper;
import com.sadesoft.portal.base.rest.HttpRestResponder;
import com.sadesoft.portal.base.error.ClientError;
import com.sadesoft.portal.core.model.*;
import com.sadesoft.portal.core.repo.PersonRepo;

import java.util.List;
import java.util.stream.Collectors;

public class KolayPersonRepo implements PersonRepo {
    private final JsonMapper mapper;
    private final HttpRestResponder responder;
    private final KolayPersonClient client;

    public KolayPersonRepo(KolayConfig config) {
        this.mapper = new JsonMapper();
        this.responder = new HttpRestResponder(config.url, mapper);
        this.client = new KolayPersonClient(responder, mapper);
    }

    @Override
    public List<Person> list() {
        return toPeople(client.listPeople());
    }

    @Override
    public List<Person> listPage(int pageNo, int pageSize) {
        return list();
    }

    @Override
    public Person create(Person person) {
        return client.createPerson(KolayPerson.fromPerson(person)).toPerson();
    }

    @Override
    public Person read(String id) {
        return client.viewPerson(id).toPerson();
    }

    @Override
    public Person update(String id, Person person) {
        return client.updatePerson(KolayPerson.fromPerson(person).withId(id)).toPerson();
    }

    @Override
    public Person delete(String id)  {
        throw new ClientError("KolayIK API doesn't support delete operation.");
    }

    private List<Person> toPeople(List<KolayPerson> kolayPeople) {
        return kolayPeople.stream().map(KolayPerson::toPerson).collect(Collectors.toList());
    }
}
