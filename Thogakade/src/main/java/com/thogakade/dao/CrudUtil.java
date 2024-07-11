package com.thogakade.dao;

import com.thogakade.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CrudUtil {
    public static <T> T execute(String sql, Object... params) throws Exception {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        if (sql.trim().startsWith("SELECT")) {
            return (T) statement.executeQuery();
        } else {
            return (T) (Integer) statement.executeUpdate();
        }
    }
}
