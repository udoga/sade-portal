package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.auth.MockAuthorizer;
import com.sadesoft.portal.base.error.AuthorizationError;
import com.sadesoft.portal.base.error.InconsistencyError;
import com.sadesoft.portal.core.detailed.DetailedWorklog;
import com.sadesoft.portal.core.mock.*;
import com.sadesoft.portal.core.model.*;
import com.sadesoft.portal.core.query.WorklogQuery;

import com.sadesoft.portal.core.order.WorklogOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.List;

import static com.sadesoft.portal.base.util.TestUtil.assertInvalidInput;
import static com.sadesoft.portal.base.util.TestUtil.assertMissingField;
import static org.junit.jupiter.api.Assertions.*;

public class WorklogServiceTest {
    private String callerId;
    private MockProjectRepo projectRepo;
    private MockEmployeeRepo employeeRepo;
    private MockActivityRepo activityRepo;
    private MockEngagementRepo engagementRepo;
    private MockWorklogRepo worklogRepo;
    private MockAuthorizer authorizer;
    private WorklogService service;
    private Worklog validWorklog;

    @BeforeEach
    public void setup() {
        callerId = "0";
        projectRepo = new MockProjectRepo(null);
        employeeRepo = new MockEmployeeRepo(null);
        activityRepo = new MockActivityRepo();
        engagementRepo = new MockEngagementRepo(null, null, null);
        worklogRepo = new MockWorklogRepo(projectRepo, employeeRepo, activityRepo);
        authorizer = new MockAuthorizer();
        service = new WorklogService(worklogRepo, employeeRepo, engagementRepo, authorizer);
        validWorklog = new Worklog(
                null,
                "0",
                "0",
                "0",
                LocalDate.of(2024, 1, 1),
                2,
                "Sample Worklog",
                null);
    }

    private void assertUnauthorized(String permissionName, Executable executable) {
        AuthorizationError e = assertThrows(AuthorizationError.class, executable);
        assertEquals(permissionName, e.permissionName);
    }

    @Nested
    public class GivenServiceWithOneWorklogAndNoAuthorization {
        @BeforeEach
        public void setup() {
            authorizer.enabled = false;
            worklogRepo.create(validWorklog);
            employeeRepo.create(new Employee().withUserId(callerId));
            engagementRepo.create(new Engagement().withProjectId("0").withEmployeeId("0"));
        }

        @Test
        public void whenHasMissingFieldOnCreate_throwsError() {
            assertMissingField(() -> service.create(callerId, validWorklog.withProjectId(null)));
            assertMissingField(() -> service.create(callerId, validWorklog.withEmployeeId(null)));
            assertMissingField(() -> service.create(callerId, validWorklog.withActivityId(null)));
            assertMissingField(() -> service.create(callerId, validWorklog.withWorkDate(null)));
            assertMissingField(() -> service.create(callerId, validWorklog.withDuration(null)));
            assertMissingField(() -> service.create(callerId, validWorklog.withDescription(null)));
        }

        @Test
        public void whenHasInvalidInputOnCreate_throwsError() {
            assertInvalidInput(() -> service.create(callerId, validWorklog.withEmployeeId("A")));
            assertInvalidInput(() -> service.create(callerId, validWorklog.withProjectId("")));
            assertDoesNotThrow(() -> service.create(callerId, validWorklog.withActivityId("11")));
            assertInvalidInput(() -> service.create(callerId, validWorklog.withApprovalStatus("1")));
            assertInvalidInput(() -> service.create(callerId, validWorklog.withApprovalStatus("")));
            assertInvalidInput(() -> service.create(callerId, validWorklog.withApprovalStatus("I")));
            assertDoesNotThrow(() -> service.create(callerId, validWorklog.withApprovalStatus(null)));
            assertDoesNotThrow(() -> service.create(callerId, validWorklog.withApprovalStatus("A")));
            assertInvalidInput(() -> service.create(callerId, validWorklog.withApprovalStatus("AD")));
        }

        @Test
        public void whenWorklogProjectIdAndEmployeeIdAreUnengaged_throwsError() {
            assertThrows(InconsistencyError.class, () -> service.create(callerId, validWorklog.withProjectId("1")));
        }

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError(){
            assertMissingField(() -> service.update(callerId, "0", validWorklog.withProjectId(null)));
            assertMissingField(() -> service.update(callerId, "0", validWorklog.withEmployeeId(null)));
            assertMissingField(() -> service.update(callerId, "0", validWorklog.withActivityId(null)));
            assertMissingField(() -> service.update(callerId, "0", validWorklog.withWorkDate(null)));
            assertMissingField(() -> service.update(callerId, "0", validWorklog.withDuration(null)));
            assertMissingField(() -> service.update(callerId, "0", validWorklog.withDescription(null)));
        }

        @Test
        public void listsWorklogs() {
            assertEquals(1, service.listBy(callerId, new WorklogQuery()).size());
            assertEquals(validWorklog.withId("0"), service.listBy(callerId, new WorklogQuery()).get(0));
        }

        @Test
        public void listsByOffset() {
            worklogRepo.create(validWorklog.copy());
            assertEquals(2, service.listBy(callerId, new WorklogQuery().withOffset(0)).size());
            assertEquals(1, service.listBy(callerId, new WorklogQuery().withOffset(1)).size());
            assertEquals(0, service.listBy(callerId, new WorklogQuery().withOffset(2)).size());
        }

        @Test
        public void listsByEmployeeId() {
            assertEquals(1, service.listBy(callerId, new WorklogQuery().withEmployeeId("0")).size());
            assertEquals(0, service.listBy(callerId, new WorklogQuery().withEmployeeId("1")).size());
        }

        @Test
        public void listsOrderedByNewest() {
            worklogRepo.create(validWorklog.withWorkDate(LocalDate.of(2024, 2, 1)));
            List<Worklog> worklogs = service.listBy(callerId, new WorklogQuery().withOrder(WorklogOrder.NEWEST));
            assertEquals(2, worklogs.size());
            assertEquals("1", worklogs.get(0).id);
            assertEquals("0", worklogs.get(1).id);
        }

        @Test
        public void listsOrderedByOldest() {
            worklogRepo.create(validWorklog.withWorkDate(LocalDate.of(2024, 2, 1)));
            List<Worklog> worklogs = service.listBy(callerId, new WorklogQuery().withOrder(WorklogOrder.OLDEST));
            assertEquals(2, worklogs.size());
            assertEquals("0", worklogs.get(0).id);
            assertEquals("1", worklogs.get(1).id);
        }

        @Test
        public void listsDetailedWorklogs() {
            projectRepo.create(new Project());
            employeeRepo.create(new Employee());
            activityRepo.create(new Activity());
            List<DetailedWorklog> detailedWorklogs = service.listDetailedBy(callerId, new WorklogQuery());
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
            WorklogQuery query = new WorklogQuery().withOffset(1).withOrder(WorklogOrder.NEWEST);
            List<DetailedWorklog> worklogs = service.listDetailedBy(callerId, query);
            assertEquals(2, worklogs.size());
            assertEquals("1", worklogs.get(0).id);
            assertEquals("0", worklogs.get(1).id);
        }

        @Test
        public void readsWorklogs() {
            assertEquals(validWorklog.withId("0"), service.read(callerId, "0"));
        }

        @Test
        public void readsDetailedWorklog() {
            projectRepo.create(new Project(null, null, "Name", "Description"));
            employeeRepo.create(new Employee(null, null, "first", "last"));
            activityRepo.create(new Activity(null, "Sample Activity"));
            Worklog w1 = worklogRepo.readDetailed("0");
            Worklog w2 = service.readDetailed(callerId, "0");
            assertEquals(w1, w2);
        }

        @Test
        public void updatesWorklogById() {
            Worklog client = service.update(callerId, "0", validWorklog.withDescription("updated"));
            assertEquals(validWorklog.withId("0").withDescription("updated"), client);
            assertEquals(client, service.read(callerId, "0"));
        }

        @Test
        public void deletesWorklogById() {
            Worklog client = service.delete(callerId, "0");
            assertEquals(validWorklog.withId("0"), client);
            assertEquals(0, worklogRepo.list().size());
        }
    }

    @Nested
    public class GivenServiceWithTwoWorklogsAndEmployees {
        private final String caller0 = "5";
        private final String caller1 = "6";

        @BeforeEach
        public void setup() {
            authorizer.callerIds.addAll(List.of(caller0, caller1));
            authorizer.permissionNames.addAll(List.of(
                    "worklog_list",
                    "worklog_list_detailed",
                    "worklog_read",
                    "worklog_read_detailed",
                    "worklog_create",
                    "worklog_update",
                    "worklog_delete"));
            employeeRepo.create(new Employee().withUserId(caller0));
            employeeRepo.create(new Employee().withUserId(caller1));
            engagementRepo.create(new Engagement().withProjectId("0").withEmployeeId("0"));
            worklogRepo.create(validWorklog.withEmployeeId("0"));
            worklogRepo.create(validWorklog.withEmployeeId("1"));
            projectRepo.create(new Project());
            activityRepo.create(new Activity());
        }

        @Test
        public void whenQueryEmployeeIdDoesNotExist_throwsError() {
            WorklogQuery query = new WorklogQuery().withEmployeeId("10");
            assertUnauthorized("worklog_list-employee:other", () -> service.listBy(caller0, query));
        }

        @Test
        public void whenReceivesListRequest_authorizesEmployeeIsAnother() {
            WorklogQuery query0 = new WorklogQuery().withEmployeeId("0");
            WorklogQuery query1 = new WorklogQuery().withEmployeeId("1");
            assertUnauthorized("worklog_list-employee:other", () -> service.listBy(caller0, query1));
            assertUnauthorized("worklog_list-employee:other", () -> service.listBy(caller1, query0));
            assertUnauthorized("worklog_list_detailed-employee:other", () -> service.listDetailedBy(caller0, query1));
            assertDoesNotThrow(() -> service.listBy(caller0, query0));
            assertDoesNotThrow(() -> service.listBy(caller1, query1));
            assertDoesNotThrow(() -> service.listDetailedBy(caller0, query0));
        }

        @Test
        public void whenReceivesReadRequest_authorizesEmployeeIsAnother() {
            assertUnauthorized("worklog_read-employee:other", () -> service.read(caller0, "1"));
            assertUnauthorized("worklog_read-employee:other", () -> service.read(caller1, "0"));
            assertUnauthorized("worklog_read_detailed-employee:other", () -> service.readDetailed(caller0, "1"));
            assertDoesNotThrow(() -> service.read(caller0, "0"));
            assertDoesNotThrow(() -> service.read(caller1, "1"));
            assertDoesNotThrow(() -> service.readDetailed(caller0, "0"));
        }

        @Test
        public void whenReceivesCreateRequest_authorizesEmployeeIsAnother() {
            String permissionName = "worklog_create-employee:other";
            assertUnauthorized(permissionName, () -> service.create(caller0, validWorklog.withEmployeeId("1")));
            assertUnauthorized(permissionName, () -> service.create(caller1, validWorklog.withEmployeeId("0")));
        }

        @Test
        public void whenReceivesCreateRequest_authorizesApprovalIsResponded() {
            String permissionName = "worklog_create-approval:responded";
            assertUnauthorized(permissionName, () -> service.create(caller0, validWorklog.withApprovalStatus("A")));
            assertUnauthorized(permissionName, () -> service.create(caller0, validWorklog.withApprovalStatus("D")));
        }

        @Test
        public void whenReceivesCreateRequest_authorizesProjectIsUnengaged() {
            String permissionName = "worklog_create-project:unengaged";
            Worklog worklog0 = validWorklog.withEmployeeId("0");
            Worklog worklog1 = validWorklog.withEmployeeId("1");
            assertDoesNotThrow(() -> service.create(caller0, worklog0.withProjectId("0")));
            assertUnauthorized(permissionName, () -> service.create(caller0, worklog0.withProjectId("1")));
            assertUnauthorized(permissionName, () -> service.create(caller1, worklog1.withProjectId("0")));
            assertUnauthorized(permissionName, () -> service.create(caller1, worklog1.withProjectId("1")));
        }

        @Test
        void whenReceivesCreateRequest_authorizesProjectIsUnengagedWithCaller() {
            String permissionName = "worklog_create-project:unengaged";
            authorizer.permissionNames.add("worklog_create-employee:other");
            Worklog worklog1 = validWorklog.withEmployeeId("1");
            assertThrows(InconsistencyError.class, () -> service.create(caller0, worklog1.withProjectId("0")));
            assertUnauthorized(permissionName, () -> service.create(caller1, worklog1.withProjectId("1")));
        }

        @Test
        public void whenReceivesUpdateRequest_authorizesEmployeeIsAnother() {
            assertUnauthorized("worklog_update-employee:other", () -> service.update(caller0, "1", validWorklog));
            assertUnauthorized("worklog_update-employee:other", () -> service.update(caller1, "0", validWorklog));
        }

        @Test
        public void whenReceivesUpdateRequest_authorizesApprovalIsResponded() {
            String permissionName = "worklog_update-approval:responded";
            worklogRepo.update("0", worklogRepo.read("0").withApprovalStatus("A"));
            assertUnauthorized(permissionName, () -> service.update(caller0, "0", validWorklog));
        }

        @Test
        public void whenReceivesUpdateRequest_authorizesProjectIsUnengaged() {
            String permissionName = "worklog_update-project:unengaged";
            assertUnauthorized(permissionName, () -> service.update(caller1, "1", validWorklog.withEmployeeId("1")));
            assertDoesNotThrow(() -> service.update(caller0, "0", validWorklog.withEmployeeId("0")));
        }

        @Test
        public void whenReceivesUpdateRequest_authorizesEmployeeChange() {
            String permissionName = "worklog_update-employee_change";
            assertUnauthorized(permissionName, () -> service.update(caller0, "0", validWorklog.withEmployeeId("1")));
        }

        @Test
        public void whenReceivesUpdateRequest_authorizesApprovalChange() {
            String permissionName = "worklog_update-approval_change";
            assertUnauthorized(permissionName, () -> service.update(caller0, "0", validWorklog.withApprovalStatus("A")));
        }

        @Test
        public void whenReceivesUpdateRequest_authorizesProjectChange() {
            String permissionName = "worklog_update-project_change";
            assertUnauthorized(permissionName, () -> service.update(caller0, "0", validWorklog.withProjectId("1")));
        }

        @Test
        public void whenReceivesDeleteRequest_authorizesEmployeeIsAnother() {
            assertUnauthorized("worklog_delete-employee:other", () -> service.delete(caller0, "1"));
            assertUnauthorized("worklog_delete-employee:other", () -> service.delete(caller1, "0"));
        }

        @Test
        public void whenReceivesDeleteRequest_authorizesApprovalIsResponded() {
            String permissionName = "worklog_delete-approval:responded";
            worklogRepo.update("0", worklogRepo.read("0").withApprovalStatus("A"));
            assertUnauthorized(permissionName, () -> service.delete(caller0, "0"));
        }

        @Test
        public void whenReceivesDeleteRequest_authorizesProjectIsUnengaged() {
            assertUnauthorized("worklog_delete-project:unengaged", () -> service.delete(caller1, "1"));
            assertDoesNotThrow(() -> service.delete(caller0, "0"));
        }
    }
}
