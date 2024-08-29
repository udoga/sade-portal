package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.detailed.DetailedWorklog;
import com.sadesoft.portal.core.query.WorklogQuery;
import com.sadesoft.portal.core.mock.MockWorklogRepo;
import com.sadesoft.portal.core.mock.MockProjectRepo;
import com.sadesoft.portal.core.mock.MockEmployeeRepo;
import com.sadesoft.portal.core.mock.MockActivityRepo;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Activity;
import com.sadesoft.portal.core.model.Worklog;

import com.sadesoft.portal.core.order.WorklogOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

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
            assertEquals(1, service.listBy(new WorklogQuery()).size());
            assertEquals(validWorklog.withId("0"), service.listBy(new WorklogQuery()).get(0));
        }

        @Test
        public void listsByOffset() {
            worklogRepo.create(validWorklog.copy());
            assertEquals(2, service.listBy(new WorklogQuery().withOffset(0)).size());
            assertEquals(1, service.listBy(new WorklogQuery().withOffset(1)).size());
            assertEquals(0, service.listBy(new WorklogQuery().withOffset(2)).size());
        }

        @Test
        public void listsByEmployeeId() {
            assertEquals(1, service.listBy(new WorklogQuery().withEmployeeId("0")).size());
            assertEquals(0, service.listBy(new WorklogQuery().withEmployeeId("1")).size());
        }

        @Test
        public void listsOrderedByNewest() {
            worklogRepo.create(validWorklog.withWorkDate(LocalDate.of(2024, 2, 1)));
            List<Worklog> worklogs = service.listBy(new WorklogQuery().withOrder(WorklogOrder.NEWEST));
            assertEquals(2, worklogs.size());
            assertEquals("1", worklogs.get(0).id);
            assertEquals("0", worklogs.get(1).id);
        }

        @Test
        public void listsOrderedByOldest() {
            worklogRepo.create(validWorklog.withWorkDate(LocalDate.of(2024, 2, 1)));
            List<Worklog> worklogs = service.listBy(new WorklogQuery().withOrder(WorklogOrder.OLDEST));
            assertEquals(2, worklogs.size());
            assertEquals("0", worklogs.get(0).id);
            assertEquals("1", worklogs.get(1).id);
        }

        @Test
        public void listsDetailedWorklogs() {
            projectRepo.create(new Project());
            employeeRepo.create(new Employee());
            activityRepo.create(new Activity());
            List<DetailedWorklog> detailedWorklogs = service.listDetailedBy(new WorklogQuery());
            assertEquals(1, detailedWorklogs.size());
            assertEquals(projectRepo.read("0"), detailedWorklogs.get(0).project);
        }

        @Test
        public void listsDetailedOrderedByNewest() {
            projectRepo.create(new Project());
            employeeRepo.create(new Employee());
            activityRepo.create(new Activity());
            worklogRepo.create(validWorklog.copy().withWorkDate(LocalDate.of(2024, 2, 1)));
            worklogRepo.create(validWorklog.copy().withWorkDate(LocalDate.of(2024, 3, 1)));
            var worklogs = service.listDetailedBy(new WorklogQuery().withOffset(1).withOrder(WorklogOrder.NEWEST));
            assertEquals(2, worklogs.size());
            assertEquals("1", worklogs.get(0).id);
            assertEquals("0", worklogs.get(1).id);
        }

        @Test
        public void readsWorklogs() {
            assertEquals(validWorklog.withId("0"), service.read("0"));
        }

        @Test
        public void readsDetailedWorklog() {
            projectRepo.create(new Project(null, null, "Name", "Description"));
            employeeRepo.create(new Employee(null, null, "first", "last"));
            activityRepo.create(new Activity(null, "Sample Activity"));
            assertEquals(worklogRepo.readDetailed("0"), service.readDetailed("0"));
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
            assertEquals(0, worklogRepo.list().size());
        }
    }
}
