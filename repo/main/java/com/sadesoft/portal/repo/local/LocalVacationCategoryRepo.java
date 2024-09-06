package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.VacationCategory;
import com.sadesoft.portal.core.repo.VacationCategoryRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocalVacationCategoryRepo extends JdbcRepo<VacationCategory> implements VacationCategoryRepo {
    public LocalVacationCategoryRepo(Connection connection) {
        super(connection, "vacation_categories", "c", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("name", true)));
    }

    @Override
    protected VacationCategory toObject(ResultSet s) throws SQLException {
        return new VacationCategory(
                getId(s, "id"),
                s.getString("name"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, VacationCategory o) throws SQLException {
        int i = 1;
        s.setString(i++, o.name);
        if (o.id != null) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<VacationCategory> listOptions() {
        String sql = getQuerySql("id, name") + getOrderSql("name");
        return call(() -> queryFor(createStatement(sql), this::toVacationCategoryOption));
    }

    private VacationCategory toVacationCategoryOption(ResultSet s) throws SQLException {
        return new VacationCategory().withId(getId(s, "id")).withName(s.getString("name"));
    }
}
