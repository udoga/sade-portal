package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.ListRepo;
import com.sadesoft.portal.core.model.Person;
import com.sadesoft.portal.core.repo.PersonRepo;

public class MockPersonRepo extends ListRepo<Person> implements PersonRepo {
    public MockPersonRepo() {
        super("Person");
    }
}
