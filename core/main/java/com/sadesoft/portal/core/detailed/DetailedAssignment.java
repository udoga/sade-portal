package com.sadesoft.portal.core.detailed;

import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.model.User;

public class DetailedAssignment extends Assignment {
    public User user;
    public Role role;

    public DetailedAssignment(Assignment a) {
        super(a);
    }

    public DetailedAssignment(Assignment a, User u, Role r) {
        super(a);
        this.user = u;
        this.role = r;
    }

    @Override
    public DetailedAssignment copy() {
        try {
            return (DetailedAssignment) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public DetailedAssignment withUser(User user) {
        DetailedAssignment copy = copy();
        copy.user = user;
        return copy;
    }

    public DetailedAssignment withRole(Role role) {
        DetailedAssignment copy = copy();
        copy.role = role;
        return copy;
    }
}
