package com.sadesoft.portal.rest.repo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.*;

public class ReceiptRepo extends JdbcRepo<Receipt>{
    public ReceiptRepo(Connection connection) {
        super(connection, "people", List.of(
                new JdbcColumn("id"),
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
                s.getInt(getColumnName(i++)),
                getLocalDate(s, getColumnName(i++)),
                ReceiptCategory.valueOf(s.getString(getColumnName(i++))),
                BigDecimal.valueOf(Long.parseLong(s.getString(i++))),
                Short.valueOf(s.getString(i++)),
                s.getString(getColumnName(i))
        );
    }

    @Override
    protected void fillStatement(PreparedStatement s, Receipt o) throws SQLException {
        int i = 1;
        setLocalDate(s, i++, o.receiptDate);
        s.setString(i++, o.category.name());
        s.setBigDecimal(i++, o.amount);
        s.setShort(i++, o.vatRate);
        s.setString(i++, o.description);
        if (o.id != null) s.setInt(i, o.id);
    }
}
