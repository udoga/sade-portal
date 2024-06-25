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
import com.sadesoft.portal.core.repo.ReceiptRepo;

public class LocalReceiptRepo extends JdbcRepo<Receipt> implements ReceiptRepo {
    public LocalReceiptRepo(Connection connection) {
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
        return new Receipt(
                getId(s, "id"),
                getId(s, "expense_id"),
                getLocalDate(s, "receipt_date"),
                ReceiptCategory.valueOf(s.getString("category")),
                s.getBigDecimal("amount"),
                s.getShort("vat_rate"),
                s.getString("description")
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
