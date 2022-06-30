package com.sadesoft.portal.rest;

import com.sadesoft.portal.base.DatabaseRepo;
import com.sadesoft.portal.core.Item;
import com.sadesoft.portal.core.ItemEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemRepo extends DatabaseRepo<Item> {
    public ItemRepo(Connection connection) {
        super(connection, "items", List.of("id", "byte_field", "short_field", "int_field", "long_field", "double_field",
                "float_field", "decimal_field", "boolean_field", "char_field", "string_field", "date_field",
                "enum_field"));
    }

    @Override
    public Item toObject(ResultSet s) throws SQLException {
        int i = 0;
        return new Item(
                s.getInt(columnNames.get(i++)),
                s.getByte(columnNames.get(i++)),
                s.getShort(columnNames.get(i++)),
                s.getInt(columnNames.get(i++)),
                s.getLong(columnNames.get(i++)),
                s.getDouble(columnNames.get(i++)),
                s.getFloat(columnNames.get(i++)),
                s.getBigDecimal(columnNames.get(i++)),
                s.getBoolean(columnNames.get(i++)),
                getChar(s, columnNames.get(i++)),
                s.getString(columnNames.get(i++)),
                getLocalDate(s, columnNames.get(i++)),
                ItemEnum.valueOf(s.getString(columnNames.get(i))));
    }

    @Override
    protected void fillStatement(PreparedStatement s, Item o) throws SQLException {
        int i = 1;
        s.setByte(i++, o.byteField);
        s.setShort(i++, o.shortField);
        s.setInt(i++, o.intField);
        s.setLong(i++, o.longField);
        s.setDouble(i++, o.doubleField);
        s.setFloat(i++, o.floatField);
        s.setBigDecimal(i++, o.decimalField);
        s.setBoolean(i++, o.booleanField);
        setChar(s, i++, o.charField);
        s.setString(i++, o.stringField);
        setLocalDate(s, i++, o.dateField);
        s.setString(i++, o.enumField.name());
        if (o.id != null) s.setInt(i, o.id);
    }
}
