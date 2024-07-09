package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.VacationCategory;
import com.sadesoft.portal.core.repo.VacationCategoryRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OpmVacationCategoryRepo extends JdbcRepo<VacationCategory> implements VacationCategoryRepo {
    public OpmVacationCategoryRepo(Connection connection) {
        super(connection, "opm_hrm_vacation_type", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("type_name")));
    }

    @Override
    protected VacationCategory toObject(ResultSet s) throws SQLException {
        return new VacationCategory(
                getId(s, "id"),
                s.getString("type_name"));
    }

    @Override
    protected void fillStatement(PreparedStatement s, VacationCategory o) throws SQLException {
        int i = 1;
        s.setString(i++, o.name);
        if (o.id != null) setId(s, i, o.id);
    }
}
