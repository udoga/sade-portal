package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedProject;
import com.sadesoft.portal.core.model.Client;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.repo.ProjectRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OpmProjectRepo extends JdbcRepo<Project> implements ProjectRepo {
    public OpmProjectRepo(Connection connection) {
        super(connection, "opm_ps_project", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("client_id"),
                new JdbcColumn("client_sales_responsible"),
                new JdbcColumn("client_sponsor"),
                new JdbcColumn("code"),
                new JdbcColumn("commercial_product_expense"),
                new JdbcColumn("commercial_service_expense"),
                new JdbcColumn("contract_client_id"),
                new JdbcColumn("contract_date"),
                new JdbcColumn("contract_no"),
                new JdbcColumn("contract_status"),
                new JdbcColumn("contracted_amount"),
                new JdbcColumn("contracted_currency"),
                new JdbcColumn("contracted_end_date"),
                new JdbcColumn("contracted_start_date"),
                new JdbcColumn("created_by"),
                new JdbcColumn("created_time"),
                new JdbcColumn("department_id"),
                new JdbcColumn("description"),
                new JdbcColumn("due_date"),
                new JdbcColumn("estimated_amount"),
                new JdbcColumn("estimated_contract_exp_budget"),
                new JdbcColumn("estimated_currency"),
                new JdbcColumn("estimated_end_date"),
                new JdbcColumn("estimated_offer_exp_budget"),
                new JdbcColumn("estimated_opportunity_exp_budget"),
                new JdbcColumn("estimated_probability"),
                new JdbcColumn("estimated_start_date"),
                new JdbcColumn("expense"),
                new JdbcColumn("fiscal_year_amount"),
                new JdbcColumn("flag_activity"),
                new JdbcColumn("flag_chancellery"),
                new JdbcColumn("flag_contract"),
                new JdbcColumn("flag_internal"),
                new JdbcColumn("flag_issue"),
                new JdbcColumn("flag_main_project"),
                new JdbcColumn("flag_offer"),
                new JdbcColumn("flag_opportunity"),
                new JdbcColumn("guarentee_letter_amount"),
                new JdbcColumn("income_group_id"),
                new JdbcColumn("income_type"),
                new JdbcColumn("income_type_id"),
                new JdbcColumn("invoicing_period"),
                new JdbcColumn("last_updated_by"),
                new JdbcColumn("last_updated_time"),
                new JdbcColumn("manager_id"),
                new JdbcColumn("name"),
                new JdbcColumn("organization_id"),
                new JdbcColumn("organization_unit"),
                new JdbcColumn("overtime"),
                new JdbcColumn("payment_period"),
                new JdbcColumn("pre_sales_responsible_id"),
                new JdbcColumn("pre_sales_support"),
                new JdbcColumn("pricing_card_status"),
                new JdbcColumn("pricing_card_description"),
                new JdbcColumn("project_effort"),
                new JdbcColumn("project_initiator"),
                new JdbcColumn("project_parent_id"),
                new JdbcColumn("project_responsible"),
                new JdbcColumn("project_type"),
                new JdbcColumn("quarter"),
                new JdbcColumn("sales_channel_id"),
                new JdbcColumn("sales_responsible_id"),
                new JdbcColumn("sales_support"),
                new JdbcColumn("signed_date"),
                new JdbcColumn("sponsor_id"),
                new JdbcColumn("status"),
                new JdbcColumn("type"),
                new JdbcColumn("uuid"),
                new JdbcColumn("work_field"),
                new JdbcColumn("pricing_card_director")));
    }

    @Override
    protected Project toObject(ResultSet s) throws SQLException {
        return new Project(
                getId(s, "id"),
                getId(s, "client_id"),
                s.getString("name"),
                s.getString("description")
        );
    }

    @Override
    protected void fillStatement(PreparedStatement s, Project o) throws SQLException {
        int i = 1;
        boolean inserting = (o.id == null);
        setId(s, i++, o.clientId);
        s.setString(i++, ""); // client_sales_responsible
        s.setString(i++, ""); // client_sponsor
        s.setString(i++, ""); // code
        s.setBigDecimal(i++, null); // commercial_product_expense
        s.setBigDecimal(i++, null); // commercial_service_expense
        s.setInt(i++, 0); // contract_client_id
        setLocalDateTime(s, i++, LocalDateTime.of(2022, 1, 1, 0, 0, 0)); // contract_date
        s.setString(i++, ""); // contract_no
        s.setString(i++, ""); // contract_status
        s.setBigDecimal(i++, null); // contracted_amount
        s.setString(i++, "TRY"); // contracted_currency
        setLocalDateTime(s, i++, LocalDateTime.of(2022, 1, 1, 0, 0, 0)); // contracted_end_date
        setLocalDateTime(s, i++, LocalDateTime.of(2022, 1, 1, 0, 0, 0)); // contracted_start_date
        if (inserting) s.setInt(i++, 1); // created_by
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now()); // created_time
        s.setInt(i++, 0); // department_id
        s.setString(i++, o.description); // description
        setLocalDateTime(s, i++, LocalDateTime.of(2022, 1, 1, 0, 0, 0)); // due_date
        s.setBigDecimal(i++, null); // estimated_amount
        s.setBigDecimal(i++, null); // estimated_contract_exp_budget
        s.setString(i++, "TRY"); // estimated_currency
        setLocalDateTime(s, i++, LocalDateTime.of(2022, 1, 1, 0, 0, 0)); // estimated_end_date
        s.setBigDecimal(i++, null); // estimated_offer_exp_budget
        s.setBigDecimal(i++, null); // estimated_opportunity_exp_budget
        s.setBigDecimal(i++, null); // estimated_probability
        setLocalDateTime(s, i++, LocalDateTime.of(2022, 1, 1, 0, 0, 0)); // estimated_start_date
        s.setString(i++, ""); // expense
        s.setBigDecimal(i++, null); // fiscal_year_amount
        s.setBoolean(i++, false); // flag_activity
        s.setBoolean(i++, false); // flag_chancellery
        s.setBoolean(i++, false); // flag_contract
        s.setBoolean(i++, false); // flag_internal
        s.setBoolean(i++, false); // flag_issue
        s.setBoolean(i++, false); // flag_main_project
        s.setBoolean(i++, false); // flag_offer
        s.setBoolean(i++, false); // flag_opportunity
        s.setBigDecimal(i++, null); // guarentee_letter_amount
        s.setInt(i++, 0); // income_group_id
        s.setString(i++, ""); // income_type
        s.setInt(i++, 0); // income_type_id
        s.setString(i++, ""); // invoicing_period
        s.setInt(i++, 1); // last_updated_by
        setLocalDateTime(s, i++, LocalDateTime.now()); // last_updated_time
        s.setInt(i++, 0); // manager_id
        s.setString(i++, o.name); // name
        s.setInt(i++, 0); // organization_id
        s.setString(i++, ""); // organization_unit
        s.setString(i++, ""); // overtime
        s.setString(i++, ""); // payment_period
        s.setInt(i++, 0); // pre_sales_responsible_id
        s.setInt(i++,0); // pre_sales_support
        s.setString(i++, ""); // pricing_card_status
        s.setString(i++, ""); // pricing_card_description
        s.setInt(i++, 0); // project_effort
        s.setInt(i++, 0); // project_initiator
        s.setInt(i++, 0); // project_parent_id
        s.setInt(i++, 0); // project_responsible
        s.setString(i++, ""); // project_type
        s.setString(i++, ""); // quarter
        s.setInt(i++, 0); // sales_channel_id
        s.setInt(i++, 0); // sales_responsible_id
        s.setInt(i++, 0); // sales_support
        setLocalDateTime(s, i++, LocalDateTime.of(2022, 1, 1, 0, 0, 0)); // signed_date
        s.setInt(i++, 0); // sponsor_id
        s.setString(i++, ""); // status
        s.setInt(i++, 0); // type
        s.setObject(i++, UUID.randomUUID()); // uuid
        s.setString(i++, ""); // work_field
        s.setInt(i++, 0); // pricing_card_director
        if (!inserting) setId(s, i, o.id);
    }

    @Override
    public List<DetailedProject> listDetailed() {
        String sql = createDetailedQuerySql();
        return call(() -> queryFor(createStatement(sql), this::toDetailedProject));
    }

    @Override
    public DetailedProject readDetailed(String id) {
        String sql = createDetailedQuerySqlWithId(id);
        return call(() -> queryFor(createStatement(sql), this::toDetailedProject)).get(0);
    }

    public DetailedProject toDetailedProject(ResultSet s) throws SQLException {
        return new DetailedProject(
            toObject(s),
            new Client(getId(s, "client_id"), null, s.getString("client_name")));
    }

    private String createDetailedQuerySql() {
        return createDetailedQuerySqlWithId("");
    }

    private String createDetailedQuerySqlWithId(String id) {
        return createQuerySql(
            "p.*, c.name client_name",
            "opm_ps_project p JOIN opm_ps_client c ON p.client_id = c.id",
            id.isEmpty()? "" : (" p.id = " + id));
    }
}
