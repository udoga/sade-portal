package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.ReceiptCategory;
import com.sadesoft.portal.core.repo.ReceiptCategoryRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OpmReceiptCategoryRepo extends JdbcRepo<ReceiptCategory> implements ReceiptCategoryRepo {
    public OpmReceiptCategoryRepo(Connection connection) {
        super(connection, "opm_acc_expense_item_type", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("created_by"),
                new JdbcColumn("created_time"),
                new JdbcColumn("last_updated_by"),
                new JdbcColumn("last_updated_time"),
                new JdbcColumn("migration_desc"),
                new JdbcColumn("organization_id"),
                new JdbcColumn("type_code"),
                new JdbcColumn("type_description"),
                new JdbcColumn("type_name"),
                new JdbcColumn("uuid")));
    }

    @Override
    protected ReceiptCategory toObject(ResultSet s) throws SQLException {
        return new ReceiptCategory(
                getId(s, "id"),
                s.getString("type_description"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, ReceiptCategory o) throws SQLException {
        int i = 1;
        boolean inserting = (o.id == null);
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        s.setInt(i++, 0);
        setLocalDateTime(s, i++, LocalDateTime.now());
        s.setNull(i++, java.sql.Types.VARCHAR);
        s.setInt(i++, 0);
        s.setNull(i++, java.sql.Types.VARCHAR);
        s.setString(i++, o.name);
        s.setString(i++, "");
        s.setObject(i++, UUID.randomUUID());
        if (!inserting) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<ReceiptCategory> listSummarized() {
        String sql = createQuerySql("id, type_description", "", "", "type_description", "");
        return call(() -> queryFor(createStatement(sql), this::toSummarizedReceiptCategory));
    }

    private ReceiptCategory toSummarizedReceiptCategory(ResultSet s) throws SQLException {
        return new ReceiptCategory().withId(getId(s, "id")).withName(s.getString("type_description"));
    }
}
