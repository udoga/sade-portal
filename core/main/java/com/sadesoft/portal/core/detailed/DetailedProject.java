package com.sadesoft.portal.core.detailed;

import com.sadesoft.portal.core.model.Client;
import com.sadesoft.portal.core.model.Project;

public class DetailedProject extends Project {
    public Client client;

    public DetailedProject(Project p) {
        super(p);
    }

    public DetailedProject(Project p, Client c) {
        super(p);
        this.client = c;
    }

    public DetailedProject copy() {
        try {
            return (DetailedProject) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public DetailedProject withClient(Client client) {
        DetailedProject detailedProject = copy();
        detailedProject.client = client;
        return detailedProject;
    }
}
