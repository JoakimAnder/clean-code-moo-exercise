package refactored.DB.jdbc;


import java.sql.SQLException;

public class PreparedStatement {
    private java.sql.PreparedStatement prepareStatement;
    public PreparedStatement(java.sql.PreparedStatement prepareStatement) {
        this.prepareStatement = prepareStatement;
    }


    public ResultSet executeQuery() {
        try {
            return new ResultSet(prepareStatement.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeUpdate() {
        try {
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PreparedStatement setString(int i, String s) {
        try {
            prepareStatement.setString(i,s);
            return this;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public PreparedStatement setInt(int i, int x) {
        try {
            prepareStatement.setInt(i,x);
            return this;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
