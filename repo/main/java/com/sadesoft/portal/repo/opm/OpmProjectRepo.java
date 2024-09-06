package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedProject;
import com.sadesoft.portal.core.query.ProjectQuery;
import com.sadesoft.portal.core.model.Client;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.repo.ProjectRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Random;

public class OpmProjectRepo extends JdbcRepo<Project> implements ProjectRepo {
    public OpmProjectRepo(Connection connection) {
        super(connection, "opm_ps_project", "p", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("client_id", true),
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
                new JdbcColumn("description", true),
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
                new JdbcColumn("name", true),
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
    protected PreparedStatement fillStatement(PreparedStatement s, Project o) throws SQLException {
        int i = 1;
        boolean inserting = (o.id == null);
        setId(s, i++, o.clientId);
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); // client_sales_responsible
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); // client_sponsor
        if (inserting) s.setString(i++, "P" + getRandomCode()); //code
        if (inserting) s.setNull(i++, java.sql.Types.NUMERIC); // commercial_product_expense
        if (inserting) s.setNull(i++, java.sql.Types.NUMERIC); // commercial_service_expense
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); // contract_client_id
        if (inserting) s.setNull(i++, java.sql.Types.TIMESTAMP); // contract_date
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); // contract_no
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); // contract_status
        if (inserting) s.setNull(i++, java.sql.Types.NUMERIC); // contracted_amount
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); // contracted_currency
        if (inserting) s.setNull(i++, java.sql.Types.TIMESTAMP); // contracted_end_date
        if (inserting) s.setNull(i++, java.sql.Types.TIMESTAMP); // contracted_start_date
        if (inserting) s.setInt(i++, 0); // created_by
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now()); // created_time
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); // department_id
        s.setString(i++, o.description); // description
        if (inserting) s.setNull(i++, java.sql.Types.TIMESTAMP); // due_date
        if (inserting) s.setNull(i++, java.sql.Types.NUMERIC); // estimated_amount
        if (inserting) s.setNull(i++, java.sql.Types.NUMERIC); // estimated_contract_exp_budget
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); // estimated_currency
        if (inserting) s.setNull(i++, java.sql.Types.TIMESTAMP); // estimated_end_date
        if (inserting) s.setNull(i++, java.sql.Types.NUMERIC); // estimated_offer_exp_budget
        if (inserting) s.setNull(i++, java.sql.Types.NUMERIC); // estimated_opportunity_exp_budget
        if (inserting) s.setNull(i++, java.sql.Types.NUMERIC); // estimated_probability
        if (inserting) s.setNull(i++, java.sql.Types.TIMESTAMP); // estimated_start_date
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); // expense
        if (inserting) s.setNull(i++, java.sql.Types.NUMERIC); // fiscal_year_amount
        if (inserting) s.setBoolean(i++, false); // flag_activity
        if (inserting) s.setBoolean(i++, false); // flag_chancellery
        if (inserting) s.setBoolean(i++, false); // flag_contract
        if (inserting) s.setBoolean(i++, false); // flag_internal
        if (inserting) s.setBoolean(i++, false); // flag_issue
        if (inserting) s.setBoolean(i++, false); // flag_main_project
        if (inserting) s.setBoolean(i++, false); // flag_offer
        if (inserting) s.setBoolean(i++, false); // flag_opportunity
        if (inserting) s.setNull(i++, java.sql.Types.NUMERIC); // guarentee_letter_amount
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); // income_group_id
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); // income_type
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); // income_type_id
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); // invoicing_period
        if (inserting) s.setInt(i++, 0); // last_updated_by
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now()); // last_updated_time
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); // manager_id
        s.setString(i++, o.name); // name
        if (inserting) s.setInt(i++, 0); // organization_id
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); // organization_unit
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); // overtime
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); // payment_period
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); // pre_sales_responsible_id
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); // pre_sales_support
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); // pricing_card_status
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); // pricing_card_description
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); // project_effort
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); // project_initiator
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); // project_parent_id
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); // project_responsible
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); // project_type
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); // quarter
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); // sales_channel_id
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); // sales_responsible_id
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); // sales_support
        if (inserting) s.setNull(i++, java.sql.Types.TIMESTAMP); // signed_date
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); // sponsor_id
        if (inserting) s.setString(i++, ""); // status
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); // type
        if (inserting) s.setObject(i++, UUID.randomUUID()); // uuid
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); // work_field
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); // pricing_card_director
        if (!inserting) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Project> listBy(ProjectQuery q) {
        String sql = getQuerySql() + getWhereSql(q) + getOrderSql("id") + getLimitSql() + getOffsetSql(q.offset);
        return call(() -> query(createStatement(sql)));
    }

    @Override
    public List<DetailedProject> listDetailedBy(ProjectQuery q) {
        String sql = getDetailedQuerySql() + getWhereSql(q) + getOrderSql("id") + getLimitSql()
                + getOffsetSql(q.offset);
        return call(() -> queryFor(createStatement(sql), this::toDetailedProject));
    }

    @Override
    public List<Project> listOptions() {
        String sql = getQuerySql("id, name") + getOrderSql("name");
        return call(() -> queryFor(createStatement(sql), this::toProjectOption));
    }

    @Override
    public DetailedProject readDetailed(String id) {
        String sql = getDetailedQuerySql() + getWhereSql("p.id = " + id);
        return call(() -> queryFor(createStatement(sql), this::toDetailedProject)).get(0);
    }

    private Project toProjectOption(ResultSet s) throws SQLException {
        return new Project().withId(getId(s, "id")).withName(s.getString("name"));
    }

    private DetailedProject toDetailedProject(ResultSet s) throws SQLException {
        return new DetailedProject(
            toObject(s),
            new Client(getId(s, "client_id"), null, s.getString("client_name")));
    }

    private String getDetailedQuerySql() {
        return getQuerySql(
            "p.*, c.name client_name",
            tableName + " " + tableAlias +
                " left join opm_ps_client c on p.client_id = c.id");
    }

    private String getWhereSql(ProjectQuery q) {
        List<String> conditions = new ArrayList<>();
        if (q.clientId != null) conditions.add("p.client_id = " + q.clientId);
        return getWhereSql(String.join(" and ", conditions));
    }

    private String getRandomCode() {
        return (LocalDate.now().getYear() % 100) + String.format("%05d", new Random().nextInt(100000));
    }
}
