package vip.three.advanced;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    public abstract T getRow(ResultSet rs) throws SQLException;
}
