package com.sadesoft.portal.repo.opm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Receipt;
import com.sadesoft.portal.core.repo.ReceiptRepo;

public class OpmReceiptRepo extends JdbcRepo<Receipt> implements ReceiptRepo {
    public OpmReceiptRepo(Connection connection) {
        super(connection, "opm_acc_expense_item", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("expense_id"),
                new JdbcColumn("expense_item_date"),
                new JdbcColumn("expense_item_type_id"),
                new JdbcColumn("amount"),
                new JdbcColumn("currency"),
                new JdbcColumn("vat"),
                new JdbcColumn("description")));
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
    protected void fillStatement(PreparedStatement s, Receipt o) throws SQLException {
        int i = 1;
        setId(s, i++, o.expenseId);
        setLocalDateTime(s, i++, o.receiptDate.atTime(0, 0, 0));
        setId(s, i++, o.categoryId);
        s.setBigDecimal(i++, o.amount);
        s.setString(i++, o.currency);
        s.setShort(i++, o.vatRate);
        s.setString(i++, o.description);
        if (o.id != null) setId(s, i, o.id);
    }

    @Override
    public List<Receipt> listByExpenseId(String expenseId) {
        return call(() -> query(createStatement(createQuerySql("", "", "expense_id = " + expenseId))));
    }
}
