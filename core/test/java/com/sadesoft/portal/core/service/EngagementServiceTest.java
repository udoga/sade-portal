package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.filter.EngagementFilter;
import com.sadesoft.portal.core.mock.MockEmployeeRepo;
import com.sadesoft.portal.core.mock.MockEngagementRepo;
import com.sadesoft.portal.core.mock.MockPositionRepo;
import com.sadesoft.portal.core.mock.MockProjectRepo;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Engagement;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.model.Position;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EngagementServiceTest {
    private MockProjectRepo projectRepo;
    private MockEmployeeRepo employeeRepo;
    private MockPositionRepo positionRepo;
    private MockEngagementRepo engagementRepo;
    private EngagementService service;
    private Engagement validEngagement;

    @BeforeEach
    public void setup() {
        projectRepo = new MockProjectRepo(null);
        employeeRepo = new MockEmployeeRepo(null);
        positionRepo = new MockPositionRepo();
        engagementRepo = new MockEngagementRepo(projectRepo, employeeRepo, positionRepo);
        service = new EngagementService(engagementRepo);
        validEngagement = new Engagement(null, "0", "0", "0", null, null, "description", "status");
    }

    @Nested
    public class GivenServiceWithOneEngagement {
        @BeforeEach
        public void setup() {
            engagementRepo.create(validEngagement);
        }

        @Test
        public void listsEngagements() {
            assertEquals(1, service.listBy(new EngagementFilter()).size());
            assertEquals(validEngagement.withId("0"), service.list().get(0));
        }

        @Test
        public void listsByProjectId() {
            assertEquals(1, service.listBy(new EngagementFilter()).size());
            assertEquals(1, service.listBy(new EngagementFilter().withProjectId("0")).size());
            assertEquals(0, service.listBy(new EngagementFilter().withProjectId("1")).size());
        }

        @Test
        public void listsDetailedEngagements() {
            projectRepo.create(new Project());
            employeeRepo.create(new Employee());
            positionRepo.create(new Position());
            assertEquals(engagementRepo.listDetailedBy(new EngagementFilter()), service.listDetailedBy(new EngagementFilter()));
        }

        @Test
        public void readsEngagement() {
            assertEquals(validEngagement.withId("0"), service.read("0"));
        }

        @Test
        public void updatesEngagementById() {
            Engagement engagement = service.update("0", validEngagement.withDescription("updated"));
            assertEquals(validEngagement.withId("0").withDescription("updated"), engagement);
            assertEquals(engagement, service.read("0"));
        }

        @Test
        public void deletesEngagementById() {
            Engagement engagement = service.delete("0");
            assertEquals(validEngagement.withId("0"), engagement);
            assertEquals(0, service.list().size());
        }
    }
}