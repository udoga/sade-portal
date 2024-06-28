package com.sadesoft.portal.core.compound;

import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.model.Role;

public class AssignmentWithRole extends Assignment {
    public Role role;

    public AssignmentWithRole(Assignment a, Role r) {
        super(a);
        this.role = r;
    }
}
