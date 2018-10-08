package net.glowstone.io.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseResult<T> {

    private final T resultSet;
    private final Statement statement;
    private final Connection connection;

    public DatabaseResult(T resultSet, Statement statement, Connection connection) {
        this.resultSet = resultSet;
        this.statement = statement;
        this.connection = connection;
    }

    public T getResults() {
        return resultSet;
    }

    public void close() throws SQLException {
        if(resultSet instanceof ResultSet){
            ((ResultSet) resultSet).close();
            statement.close();
        }else {
            statement.close();
            connection.commit();
        }
        connection.close();
    }

}
