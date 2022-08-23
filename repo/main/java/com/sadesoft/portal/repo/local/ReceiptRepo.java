package com.sadesoft.portal.repo.local;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Receipt;
import com.sadesoft.portal.core.model.ReceiptCategory;

public class ReceiptRepo extends JdbcRepo<Receipt>{
    public ReceiptRepo(Connection connection) {
        super(connection, "receipts", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("expense_id"),
                new JdbcColumn("receipt_date"),
                new JdbcColumn("category", "receipt_category"),
                new JdbcColumn("amount"),
                new JdbcColumn("vat_rate"),
                new JdbcColumn("description")));
    }

    @Override
    public Receipt toObject(ResultSet s) throws SQLException {
        int i = 0;
        return new Receipt(
                getId(s, getColumnName(i++)),
                getId(s, getColumnName(i++)),
                getLocalDate(s, getColumnName(i++)),
                ReceiptCategory.valueOf(s.getString(getColumnName(i++))),
                s.getBigDecimal(getColumnName(i++)),
                s.getShort(getColumnName(i++)),
                s.getString(getColumnName(i))
        );
    }

    @Override
    protected void fillStatement(PreparedStatement s, Receipt o) throws SQLException {
        int i = 1;
        setId(s, i++, o.expenseId);
        setLocalDate(s, i++, o.receiptDate);
        s.setString(i++, o.category.name());
        s.setBigDecimal(i++, o.amount);
        s.setShort(i++, o.vatRate);
        s.setString(i++, o.description);
        if (o.id != null) setId(s, i, o.id);
    }
}
