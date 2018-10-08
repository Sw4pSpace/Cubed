package net.glowstone.io.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseStatement {

    private PreparedStatement statement;
    private Connection connection;

    public DatabaseStatement(PreparedStatement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;
    }

    public PreparedStatement getStatement() {
        return statement;
    }

    public void close() throws SQLException {
        connection.setAutoCommit(true);
        statement.close();
        connection.close();
    }

}
