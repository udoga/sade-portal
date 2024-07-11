package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.mock.MockWorklogRepo;
import com.sadesoft.portal.core.mock.MockProjectRepo;
import com.sadesoft.portal.core.mock.MockEmployeeRepo;
import com.sadesoft.portal.core.mock.MockActivityRepo;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Activity;
import com.sadesoft.portal.core.model.Worklog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorklogServiceTest {
    private MockProjectRepo projectRepo;
    private MockEmployeeRepo employeeRepo;
    private MockActivityRepo activityRepo;
    private MockWorklogRepo worklogRepo;
    private WorklogService service;
    private Worklog validWorklog;

    @BeforeEach
    public void setup() {
        projectRepo = new MockProjectRepo(null);
        employeeRepo = new MockEmployeeRepo(null);
        activityRepo = new MockActivityRepo();
        worklogRepo = new MockWorklogRepo(projectRepo, employeeRepo, activityRepo);
        service = new WorklogService(worklogRepo);
        validWorklog = new Worklog(
                null,
                "0",
                "0",
                "0",
                LocalDate.of(2024, 1, 1),
                2,
                "Approved",
                "Sample Worklog");
    }

    @Nested
    public class GivenServiceWithOneWorklog {
        @BeforeEach
        public void setup() {
            worklogRepo.create(validWorklog);
        }

        @Test
        public void listsWorklogs() {
            assertEquals(1, service.list().size());
            assertEquals(validWorklog.withId("0"), service.list().get(0));
        }

        @Test
        public void listsDetailedWorklogs() {
            projectRepo.create(new Project());
            employeeRepo.create(new Employee());
            activityRepo.create(new Activity());
            assertEquals(1, service.listDetailed().size());
            assertEquals(worklogRepo.listDetailed(), service.listDetailed());
        }

        @Test
        public void readsWorklogs() {
            assertEquals(validWorklog.withId("0"), service.read("0"));
        }

        @Test
        public void updatesWorklogById() {
            Worklog client = service.update("0", validWorklog.withDescription("updated"));
            assertEquals(validWorklog.withId("0").withDescription("updated"), client);
            assertEquals(client, service.read("0"));
        }

        @Test
        public void deletesWorklogById() {
            Worklog client = service.delete("0");
            assertEquals(validWorklog.withId("0"), client);
            assertEquals(0, service.list().size());
        }
    }
}
