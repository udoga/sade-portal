package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Person;
import com.sadesoft.portal.core.repo.PersonRepo;

public class MockPersonRepo extends MockRepo<Person> implements PersonRepo {
    public MockPersonRepo() {
        super("Person");
    }
}
