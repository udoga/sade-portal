package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.auth.MockAuthorizer;
import com.sadesoft.portal.base.error.AuthorizationError;
import com.sadesoft.portal.base.error.ClientError;
import com.sadesoft.portal.core.detailed.DetailedWorklog;
import com.sadesoft.portal.core.mock.*;
import com.sadesoft.portal.core.query.WorklogQuery;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Activity;
import com.sadesoft.portal.core.model.Worklog;

import com.sadesoft.portal.core.order.WorklogOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.List;
import java.lang.reflect.Field;

import static com.sadesoft.portal.base.util.TestUtil.assertInvalidInput;
import static com.sadesoft.portal.base.util.TestUtil.assertMissingField;
import static org.junit.jupiter.api.Assertions.*;

public class WorklogServiceTest {
    private String callerId;
    private MockProjectRepo projectRepo;
    private MockEmployeeRepo employeeRepo;
    private MockActivityRepo activityRepo;
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
        worklogRepo = new MockWorklogRepo(projectRepo, employeeRepo, activityRepo);
        authorizer = new MockAuthorizer();
        service = new WorklogService(worklogRepo, employeeRepo, authorizer);
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

    private void assertUnauthorized(String requestName, Executable executable) {
        AuthorizationError e = assertThrows(AuthorizationError.class, executable);
        assertEquals(requestName, e.requestName);
    }

    @Nested
    public class GivenServiceWithOneWorklogAndFullPermissions {
        @BeforeEach
        public void setup() {
            authorizer.callerIds.add(callerId);
            authorizer.permissionNames.addAll(List.of(
                    "worklog_list",
                    "worklog_list_detailed",
                    "worklog_read",
                    "worklog_read_detailed",
                    "worklog_create",
                    "worklog_update",
                    "worklog_delete"));
            worklogRepo.create(validWorklog);
            employeeRepo.create(new Employee().withUserId(callerId));
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
        public void whenHasInvalidInputOnCreate_throwsError() throws Exception{
            assertIdField("projectId");
            assertIdField("employeeId");
            assertIdField("activityId");
            assertThrows(ClientError.class, () -> service.create(callerId, validWorklog.withApprovalStatus("A")));
        }

        private void assertIdField(String fieldName) throws Exception {
            assertInvalidInput(() -> service.create(callerId, withField(validWorklog, fieldName, "A")));
            assertInvalidInput(() -> service.create(callerId, withField(validWorklog, fieldName, "")));
            assertDoesNotThrow(() -> service.create(callerId, withField(validWorklog, fieldName, "11")));
        }

        private Worklog withField(Worklog worklog, String fieldName, String value) throws Exception {
            Field field = worklog.getClass().getDeclaredField(fieldName);
            field.set(worklog, value);
            return worklog;
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
            assertEquals(worklogRepo.readDetailed("0"), service.readDetailed(callerId, "0"));
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
        public void whenHasInvalidInputOnUpdate_throwsError() throws Exception{
            assertIdField("projectId");
            assertIdField("employeeId");
            assertIdField("activityId");
            assertInvalidInput(() -> service.update(callerId, "0", validWorklog.withApprovalStatus("1")));
            assertInvalidInput(() -> service.update(callerId, "0", validWorklog.withApprovalStatus("")));
            assertInvalidInput(() -> service.update(callerId, "0", validWorklog.withApprovalStatus("I")));
            assertDoesNotThrow(() -> service.update(callerId, "0", validWorklog.withApprovalStatus(null)));
        }
    }

    @Nested
    public class GivenServiceWithTwoWorklogsAndEmployees {
        private final String callerOne = "5";
        private final String callerTwo = "6";

        @BeforeEach
        public void setup() {
            authorizer.callerIds.addAll(List.of(callerOne, callerTwo));
            authorizer.permissionNames.addAll(List.of(
                    "worklog_list:own",
                    "worklog_list_detailed:own",
                    "worklog_read:own",
                    "worklog_read_detailed:own",
                    "worklog_create:own",
                    "worklog_update:own",
                    "worklog_delete:own"));
            employeeRepo.create(new Employee().withUserId(callerOne));
            employeeRepo.create(new Employee().withUserId(callerTwo));
            worklogRepo.create(validWorklog.withEmployeeId("0"));
            worklogRepo.create(validWorklog.withEmployeeId("1"));
            projectRepo.create(new Project());
            activityRepo.create(new Activity());
        }

        @Test
        public void whenListRequestIsUnauthorized_throwsError() {
            WorklogQuery employeeOneQuery = new WorklogQuery().withEmployeeId("0");
            WorklogQuery employeeTwoQuery = new WorklogQuery().withEmployeeId("1");
            assertUnauthorized("worklog_list", () -> service.listBy(callerOne, employeeTwoQuery));
            assertUnauthorized("worklog_list", () -> service.listBy(callerTwo, employeeOneQuery));
            assertUnauthorized("worklog_list_detailed", () -> service.listDetailedBy(callerOne, employeeTwoQuery));
            assertDoesNotThrow(() -> service.listBy(callerOne, employeeOneQuery));
            assertDoesNotThrow(() -> service.listBy(callerTwo, employeeTwoQuery));
            assertDoesNotThrow(() -> service.listDetailedBy(callerOne, employeeOneQuery));
        }

        @Test
        public void whenReadRequestIsUnauthorized_throwsError() {
            assertUnauthorized("worklog_read", () -> service.read(callerOne, "1"));
            assertUnauthorized("worklog_read", () -> service.read(callerTwo, "0"));
            assertUnauthorized("worklog_read_detailed", () -> service.readDetailed(callerOne, "1"));
            assertDoesNotThrow(() -> service.read(callerOne, "0"));
            assertDoesNotThrow(() -> service.read(callerTwo, "1"));
            assertDoesNotThrow(() -> service.readDetailed(callerOne, "0"));
        }

        @Test
        public void whenCreateRequestIsUnauthorized_throwsError() {
            assertUnauthorized("worklog_create", () -> service.create(callerOne, validWorklog.withEmployeeId("1")));
            assertUnauthorized("worklog_create", () -> service.create(callerTwo, validWorklog.withEmployeeId("0")));
            assertDoesNotThrow(() -> service.create(callerOne, validWorklog.withEmployeeId("0")));
            assertDoesNotThrow(() -> service.create(callerTwo, validWorklog.withEmployeeId("1")));
        }

        @Test
        public void whenUpdateRequestIsUnauthorized_throwsError() {
            assertUnauthorized("worklog_update", () -> service.update(callerOne, "1", validWorklog));
            assertUnauthorized("worklog_update", () -> service.update(callerTwo, "0", validWorklog));
            assertDoesNotThrow(() -> service.update(callerOne, "0", validWorklog));
            assertDoesNotThrow(() -> service.update(callerTwo, "1", validWorklog));
        }

        @Test
        public void whenDeleteRequestIsUnauthorized_throwsError() {
            assertUnauthorized("worklog_delete", () -> service.delete(callerOne, "1"));
            assertUnauthorized("worklog_delete", () -> service.delete(callerTwo, "0"));
            assertDoesNotThrow(() -> service.delete(callerOne, "0"));
            assertDoesNotThrow(() -> service.delete(callerTwo, "1"));
        }
    }
}
