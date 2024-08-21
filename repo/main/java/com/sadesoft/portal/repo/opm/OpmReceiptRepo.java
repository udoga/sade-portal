package com.sadesoft.portal.repo.opm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Receipt;
import com.sadesoft.portal.core.repo.ReceiptRepo;

public class OpmReceiptRepo extends JdbcRepo<Receipt> implements ReceiptRepo {
    public OpmReceiptRepo(Connection connection) {
        super(connection, "opm_acc_expense_item", "r", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("amount", true),
                new JdbcColumn("created_by"),
                new JdbcColumn("created_time"),
                new JdbcColumn("currency", true),
                new JdbcColumn("description", true),
                new JdbcColumn("expense_id", true),
                new JdbcColumn("expense_item_date", true),
                new JdbcColumn("expense_item_type_id", true),
                new JdbcColumn("gl_account_no_id"),
                new JdbcColumn("invoice_no"),
                new JdbcColumn("last_updated_by"),
                new JdbcColumn("last_updated_time"),
                new JdbcColumn("organization_id"),
                new JdbcColumn("uuid"),
                new JdbcColumn("vat", true),
                new JdbcColumn("image")));
    }

    @Override
    public Receipt toObject(ResultSet s) throws SQLException {
        return new Receipt(
                getId(s, "id"),
                getId(s, "expense_id"),
                getLocalDateTime(s, "expense_item_date").toLocalDate(),
                getId(s, "expense_item_type_id"),
                s.getBigDecimal("amount"),
                s.getString("currency"),
                s.getShort("vat"),
                s.getString("description")
        );
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, Receipt o) throws SQLException {
        int i = 1;
        boolean inserting = (o.id == null);
        s.setBigDecimal(i++, o.amount);
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        s.setString(i++, o.currency);
        s.setString(i++, o.description);
        setId(s, i++, o.expenseId);
        setLocalDateTime(s, i++, o.receiptDate.atTime(0, 0, 0));
        setId(s, i++, o.categoryId);
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT);
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR);
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        if (inserting) s.setInt(i++, 0);
        if (inserting) s.setObject(i++, UUID.randomUUID());
        s.setShort(i++, o.vatRate);
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR);
        if (!inserting) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Receipt> listBy(String expenseId) {
        String sql = getQuerySql() + createWhereSql(expenseId) + getOrderSql("id") + getLimitSql();
        return call(() -> query(createStatement(sql)));
    }

    private String createWhereSql(String expenseId) {
        return (expenseId == null)? "" : getWhereSql("r.expense_id = " + expenseId);
    }
}
