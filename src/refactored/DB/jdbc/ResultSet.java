package refactored.DB.jdbc;

import java.sql.Array;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultSet {
    java.sql.ResultSet result;

    public ResultSet(java.sql.ResultSet result) {
        this.result = result;
    }

    public <T> List<T> getList(String columnLabel) {
        try {
            Array arr = result.getArray(columnLabel);
            T[] array = (T[]) arr.getArray();
            return Arrays.asList(array);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean next() {
        try {
            return result.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getInt(String columnLabel) {
        try {
            return result.getInt(columnLabel);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getString(String columnLabel) {
        try {
            return result.getString(columnLabel);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        try {
            ResultSetMetaData metadata = result.getMetaData();
            List<String> columns = new ArrayList<>();

        for (int i = 1; i < metadata.getColumnCount()+1; i++) {
            columns.add(metadata.getColumnLabel(i));
        }
        return "ResultSet fields: "+columns.toString();
        } catch (SQLException e) {
            return "Empty ResultSet";
        }
    }
}
