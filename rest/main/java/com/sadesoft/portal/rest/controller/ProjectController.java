package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.query.ProjectQuery;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.service.ProjectService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class ProjectController extends RestController {
    public ProjectController(ProjectService service) {
        super("/projects");
        add(HandlerType.GET, "", c -> send(c, service.listBy(getQuery(c))));
        add(HandlerType.GET, "/detailed", c -> send(c, service.listDetailedBy(getQuery(c))));
        add(HandlerType.GET, "/summarized", c -> send(c, service.listSummarized()));
        add(HandlerType.POST, "", c -> send(c, service.create(getProject(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
        add(HandlerType.GET, "/detailed/{id}", c -> send(c, service.readDetailed(getId(c))));
        add(HandlerType.PUT, "/{id}", c -> send(c, service.update(getId(c), getProject(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private Project getProject(Context c) {
        return c.bodyAsClass(Project.class);
    }

    private ProjectQuery getQuery(Context c) {
        return new ProjectQuery()
                .withClientId(c.queryParam("clientId"))
                .withOffset(c.queryParamAsClass("offset", Integer.class).getOrDefault(0));
    }
}
