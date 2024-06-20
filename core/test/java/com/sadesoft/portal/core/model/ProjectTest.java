package com.sadesoft.portal.core.model;

import com.sadesoft.portal.core.sample.ProjectSamples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProjectTest {
    private Project validProject;

    @BeforeEach
    public void setup() {
        validProject = ProjectSamples.validProject;
    }

    @Test
    public void initializesWithoutFields() {
        Project project = new Project();
        assertNull(project.id);
        assertNull(project.clientId);
        assertNull(project.name);
        assertNull(project.description);
    }

    @Test
    public void printsFields() {
        assertEquals("Project[id=null" +
                " clientId=1" +
                " name=Sample Project" +
                " description=Sample Description" +
                "]", validProject.toString());
    }

    @Test
    public void clonesWithFields() {
        assertNull(validProject.withId(null).id);
        assertNull(validProject.withClientId(null).clientId);
        assertNull(validProject.withName(null).name);
        assertNull(validProject.withDescription(null).description);
    }

    @Test
    public void checksEquality() {
        assertEquals(validProject, validProject.copy());
        assertNotEquals(validProject, validProject.withId("1"));
        assertNotEquals(validProject, validProject.withClientId("2"));
        assertNotEquals(validProject, validProject.withName(null));
        assertNotEquals(validProject, validProject.withDescription(null));
    }
}

