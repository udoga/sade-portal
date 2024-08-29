package com.sadesoft.portal.repo.local;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.query.ReceiptQuery;
import com.sadesoft.portal.core.model.Receipt;
import com.sadesoft.portal.core.repo.ReceiptRepo;

public class LocalReceiptRepo extends JdbcRepo<Receipt> implements ReceiptRepo {
    public LocalReceiptRepo(Connection connection) {
        super(connection, "receipts", "r", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("expense_id", true),
                new JdbcColumn("receipt_date", true),
                new JdbcColumn("category_id", true),
                new JdbcColumn("amount", true),
                new JdbcColumn("currency", true),
                new JdbcColumn("vat_rate", true),
                new JdbcColumn("description", true)));
    }

    @Override
    public Receipt toObject(ResultSet s) throws SQLException {
        return new Receipt(
                getId(s, "id"),
                getId(s, "expense_id"),
                getLocalDate(s, "receipt_date"),
                getId(s, "category_id"),
                s.getBigDecimal("amount"),
                s.getString("currency"),
                s.getShort("vat_rate"),
                s.getString("description")
        );
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, Receipt o) throws SQLException {
        int i = 1;
        setId(s, i++, o.expenseId);
        setLocalDate(s, i++, o.receiptDate);
        setId(s, i++, o.categoryId);
        s.setBigDecimal(i++, o.amount);
        s.setString(i++, o.currency);
        s.setShort(i++, o.vatRate);
        s.setString(i++, o.description);
        if (o.id != null) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Receipt> listBy(ReceiptQuery query) {
        String sql = getQuerySql() + createWhereSql(query) + getOrderSql("id") + getLimitSql();
        return call(() -> query(createStatement(sql)));
    }

    private String createWhereSql(ReceiptQuery q) {
        List<String> conditions = new ArrayList<>();
        if (q.expenseId != null) conditions.add("expense_id = " + q.expenseId);
        return getWhereSql(String.join(" and ", conditions));
    }
}
