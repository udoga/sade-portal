package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.ReceiptCategory;
import com.sadesoft.portal.core.repo.ReceiptCategoryRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocalReceiptCategoryRepo extends JdbcRepo<ReceiptCategory> implements ReceiptCategoryRepo {
    public LocalReceiptCategoryRepo(Connection connection) {
        super(connection, "receipt_categories", "c", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("name", true)));
    }

    @Override
    protected ReceiptCategory toObject(ResultSet s) throws SQLException {
        return new ReceiptCategory(
                getId(s, "id"),
                s.getString("name"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, ReceiptCategory o) throws SQLException {
        int i = 1;
        s.setString(i++, o.name);
        if (o.id != null) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<ReceiptCategory> listSummarized() {
        String sql = getQuerySql("id, name") + getOrderSql("name");
        return call(() -> queryFor(createStatement(sql), this::toSummarizedReceiptCategory));
    }

    private ReceiptCategory toSummarizedReceiptCategory(ResultSet s) throws SQLException {
        return new ReceiptCategory().withId(getId(s, "id")).withName(s.getString("name"));
    }
}
